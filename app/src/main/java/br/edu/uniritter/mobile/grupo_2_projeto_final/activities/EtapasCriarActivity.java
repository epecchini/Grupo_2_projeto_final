package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapa;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class EtapasCriarActivity extends AppCompatActivity {

    private String idTurma;
    private Integer idEtapa;

    TextView tvTitleCriarProf;
    TextView tvSubTitleCriarProf;
    EditText etTextoEtapaProf;
    EditText etdDataLimite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapas_criar);

        Intent it = getIntent();
        idTurma = it.getStringExtra("idTurma");
        idEtapa = it.getIntExtra("idEtapa",-1);

        tvTitleCriarProf = (TextView) findViewById(R.id.tvTitleCadastroAluno);
        tvSubTitleCriarProf = (TextView) findViewById(R.id.tvSubTitleCriarProf);
        etTextoEtapaProf = (EditText) findViewById(R.id.etTextoEtapaProf);
        etdDataLimite = (EditText) findViewById(R.id.etdDataLimite);

        tvTitleCriarProf.setText(it.getStringExtra("tvTitleCriar"));
        tvSubTitleCriarProf.setText(it.getStringExtra("tvSubTitleCriar"));
        etTextoEtapaProf.setText(FonteDados.getTextoEtapa(idTurma, idEtapa));
        etdDataLimite.setText(FonteDados.getDataLimite(idTurma, idEtapa));
    }

    public void onClickAccept(View view){
        FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

        String dataLimite = "--/--/----";
        if(etdDataLimite.getText().toString() != "" && !TextUtils.isEmpty(etdDataLimite.getText().toString())) dataLimite = etdDataLimite.getText().toString();

        Map<String, List<ClsEtapa>> data = new HashMap<>();

        List<ClsEtapa> etapa = new ArrayList<ClsEtapa>();

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            ClsEtapa objEtapa = new ClsEtapa();

            objEtapa.setIdEtapa(obj.getIdEtapa());

            if(obj.getIdEtapa() == idEtapa){
                objEtapa.setNomeEtapa(tvSubTitleCriarProf.getText().toString());
                objEtapa.setTextoEtapa(etTextoEtapaProf.getText().toString());
                objEtapa.setDataLimite(dataLimite);
            }else{
                objEtapa.setNomeEtapa(obj.getNomeEtapa());
                objEtapa.setTextoEtapa(obj.getTextoEtapa());
                objEtapa.setDataLimite(obj.getDataLimite());
            }

            etapa.add(objEtapa);
        }

        DocumentReference etapaAluno = store.collection("turma").document(idTurma);

        data.put("etapa",etapa);

        etapaAluno
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mostraToast( "Erro no gravar a modificação!");
                    }
                });
    }

    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    public void onClickibBackCriar(View view){
        finish();
    }
}