package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class EtapasActivity extends AppCompatActivity {

    private String idTurma;
    private Integer idEtapa;
    private String idEtapaAluno;
    private Boolean continueReg = false;
    private Integer status;

    TextView tvTitle;
    TextView tvSubTitle;
    TextView tvTextoEtapa;
    TextView tvLembrete;
    EditText etnLembrete;
    Switch swLembrete;
    Button btAceitarEtapa;

    FirebaseFirestore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapas);

        Log.i("Eliseo_onCreate_etapasActivity", "ok");

        store = FirebaseServices.getFirebaseFirestoreInstance();

        Intent it = getIntent();

        Log.i("Eliseo_onCreate_etapasActivity", it.getStringExtra("idEtapaAluno"));

        idTurma = it.getStringExtra("idTurma");
        idEtapa = it.getIntExtra("idEtapa",-1);
        idEtapaAluno = it.getStringExtra("idEtapaAluno");
        status = it.getIntExtra("status", 1);

        tvTitle = (TextView) findViewById(R.id.tvTitleAceiteEtapa);
        tvSubTitle = (TextView) findViewById(R.id.tvSubTitleCriar);
        tvTextoEtapa = (TextView) findViewById(R.id.tvTextoEtapa);
        tvLembrete = (TextView) findViewById(R.id.tvLembrete);
        etnLembrete = (EditText) findViewById(R.id.etnLembrete);
        swLembrete = (Switch) findViewById(R.id.swLembrete);
        btAceitarEtapa = (Button) findViewById(R.id.btAceiteEtapa);

        btAceitarEtapa.setVisibility(it.getIntExtra("showBtAceite", Button.GONE));

        tvLembrete.setVisibility(it.getIntExtra("showLembrete", Button.GONE));
        etnLembrete.setVisibility(it.getIntExtra("showLembrete", Button.GONE));
        swLembrete.setVisibility(it.getIntExtra("showLembrete", Button.GONE));

        tvTitle.setText(it.getStringExtra("tvTitle"));
        tvSubTitle.setText(it.getStringExtra("tvSubTitle"));
        tvTextoEtapa.setText(FonteDados.getTextoEtapa(idTurma, idEtapa));
        etnLembrete.setText(it.getStringExtra("etnLembreteValue"));
        btAceitarEtapa.setText(it.getStringExtra("btAceitarEtapa"));
        swLembrete.setChecked(it.getBooleanExtra("swLembrete",false));

        swLembrete.setOnCheckedChangeListener((buttonView, isChecked) -> saveChanges());

        etnLembrete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                saveChanges();
            }
        });
    }

    private void saveChanges(){
        setIdTurmaAtual();

        if(!continueReg) return;

        Log.i("Eliseo_saveChanges", FonteDados.getIdAlunoAtual());

        Integer etnLembreteValue = 0;
        try { etnLembreteValue = Integer.parseInt(etnLembrete.getText().toString()); } catch (Exception ex) {}

        if(idEtapaAluno.equals("") || TextUtils.isEmpty(idEtapaAluno)){
            ClsEtapaAluno obj = new ClsEtapaAluno();

            obj.setIdAluno(FonteDados.getIdAlunoAtual());
            obj.setIdTurma(idTurma);
            obj.setIdEtapa(idEtapa);
            obj.setStatus(0);
            obj.setLembreteDias(etnLembreteValue);
            obj.setEnviarLembrete(swLembrete.isChecked() ? 1 : 0);

            Date c = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            obj.setDataEntrega(df.format(c));

            store.collection("etapaAluno").add(obj)
                    .addOnSuccessListener(documentReference -> idEtapaAluno = FonteDados.getIdEtapaAluno(idTurma, idEtapa))
                    .addOnFailureListener(e -> mostraToast("Erro no criar essa etapa!"));
        }
        else{
            DocumentReference etapaAluno = store.collection("etapaAluno").document(idEtapaAluno);

            etapaAluno
                    .update("lembreteDias", etnLembreteValue, "enviarLembrete", swLembrete.isChecked() ? 1 : 0)
                    .addOnSuccessListener(aVoid -> {
                    })
                    .addOnFailureListener(e -> mostraToast( "Erro no gravar a modificação!"));
        }
    }

    public void onClickAccept(View view){
        setIdTurmaAtual();

        if(!continueReg) return;

        Log.i("Eliseo_onClickAccept", FonteDados.getIdAlunoAtual());

        if(idEtapaAluno.equals("") || TextUtils.isEmpty(idEtapaAluno)){
            ClsEtapaAluno obj = new ClsEtapaAluno();

            obj.setIdAluno(FonteDados.getIdAlunoAtual());
            obj.setIdTurma(idTurma);
            obj.setLembreteDias(0);
            obj.setEnviarLembrete(0);

            Date c = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            obj.setDataEntrega(df.format(c));

            for(Integer i = 1; i <= 6; i++) {
                obj.setIdEtapa(i);
                obj.setStatus(i == idEtapa ? status : 0);

                Log.i("Eliseo_onClickAccept", "setIdEtapa " + i);

                Integer finalI = i;
                store.collection("etapaAluno").add(obj)
                        .addOnSuccessListener(documentReference -> finish())
                        .addOnFailureListener(e -> mostraToast("Erro no criar a " + finalI + "ª etapa!"));
            }
        }
        else{
            DocumentReference etapaAluno = store.collection("etapaAluno").document(idEtapaAluno);

            Integer etnLembreteValue = 0;
            try { etnLembreteValue = Integer.parseInt(etnLembrete.getText().toString()); } catch (Exception ex) {}

            Log.i("Eliseo_idEtapaAluno", idEtapaAluno);

            etapaAluno
                    .update("status", status, "lembreteDias", etnLembreteValue, "enviarLembrete", swLembrete.isChecked() ? 1 : 0)
                    .addOnSuccessListener(aVoid -> finish())
                    .addOnFailureListener(e -> mostraToast( "Erro no gravar a modificação!"));
        }
    }

    private void setIdTurmaAtual(){
        continueReg = true;

        Log.i("Eliseo_idTurma", idTurma);
        try {
            Log.i("Eliseo_idAlunoAtual", FonteDados.getIdAlunoAtual());
        }catch (Exception ex){
            Log.e("Eliseo_idAlunoAtual", ex.getMessage());
        }

        DocumentReference aluno = store.collection("alunos").document(FonteDados.getIdAlunoAtual());

        aluno
                .update("idTurmaAtual", idTurma)
                .addOnSuccessListener(aVoid -> Log.i("Eliseo_setIdTurmaAtual_onSuccess", continueReg.toString()))
                .addOnFailureListener(e -> {
                    continueReg = false;
                    Log.i("Eliseo_setIdTurmaAtual_addOnFailureListener", e.getMessage());
                    mostraToast( "Erro no gravar a modificação da ID da turma atual!");
                });

        Log.i("Eliseo_setIdTurmaAtual", continueReg.toString());
    }

    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    public void onClickIbBack(View view){
        finish();
    }
}