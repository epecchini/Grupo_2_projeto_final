package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class AceiteEtapaActivity extends AppCompatActivity {

    private String idAluno;
    private String idEtapaAluno;
    private Boolean lastEtapa;

    FirebaseFirestore store;

    TextView tvTitleAceiteEtapa;
    TextView tvSubTitleAceiteEtapa;
    EditText etObsProfAceiteEtapa;
    Button btAceiteEtapa;
    Button btRecusarEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceite_etapa);

        tvTitleAceiteEtapa = (TextView) findViewById(R.id.tvTitleAceiteEtapa);
        tvSubTitleAceiteEtapa = (TextView) findViewById(R.id.tvSubTitleAceiteEtapa);
        etObsProfAceiteEtapa = (EditText) findViewById(R.id.etObsProfAceiteEtapa);
        btAceiteEtapa = (Button) findViewById(R.id.btAceiteEtapa);
        btRecusarEtapa = (Button) findViewById(R.id.btRecusarEtapa);

        store = FirebaseServices.getFirebaseFirestoreInstance();

        Intent it = getIntent();

        idAluno = it.getStringExtra("idAluno");
        idEtapaAluno = it.getStringExtra("idEtapaAluno");
        lastEtapa = it.getBooleanExtra("lastEtapa", false);

        tvTitleAceiteEtapa.setText(it.getStringExtra("tvTitle"));
        tvSubTitleAceiteEtapa.setText(it.getStringExtra("tvSubTitle"));

        try{
            etObsProfAceiteEtapa.setText(FonteDados.getEtapaAluno(idEtapaAluno).getObsProf());
        }catch (NullPointerException ex) {
        }

        btAceiteEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Eliseo_AceiteEtapaActivity_idAluno", idAluno);
                Log.i("Eliseo_AceiteEtapaActivity_idEtapaAluno", idEtapaAluno);
                Log.i("Eliseo_AceiteEtapaActivity_lastEtapa", lastEtapa.toString());

                DocumentReference aluno = store.collection("alunos").document(idAluno);
                DocumentReference etapaAluno = store.collection("etapaAluno").document(idEtapaAluno);

                if(lastEtapa) {
                    aluno
                            .update("idTurmaAtual", null)
                            .addOnSuccessListener(aVoid -> etapaAluno
                                    .update("status", 2)
                                    .addOnSuccessListener(aVoid1 -> finish())
                                    .addOnFailureListener(e -> mostraToast("Erro ao registrar a liberação!")))
                            .addOnFailureListener(e -> mostraToast("Erro ao registrar a liberação!"));
                }else{
                    etapaAluno
                            .update("status", 2)
                            .addOnSuccessListener(aVoid -> finish())
                            .addOnFailureListener(e -> mostraToast( "Erro ao registrar a liberação!"));
                }
            }
        });

        btRecusarEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etObsProfAceiteEtapa.getText())){
                    mostraToast( "O campo observação não pode ser nulo!");
                    return;
                }
                Log.i("Eliseo_AceiteEtapaActivity_idEtapaAluno", idEtapaAluno);

                DocumentReference etapaAluno = store.collection("etapaAluno").document(idEtapaAluno);

                etapaAluno
                        .update("status", 0, "obsProf", etObsProfAceiteEtapa.getText())
                        .addOnSuccessListener(aVoid -> finish())
                        .addOnFailureListener(e -> mostraToast( "Erro ao registrar o recuso!"));
            }
        });
    }

    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    public void onClickIbBack(View view){ finish(); }
}