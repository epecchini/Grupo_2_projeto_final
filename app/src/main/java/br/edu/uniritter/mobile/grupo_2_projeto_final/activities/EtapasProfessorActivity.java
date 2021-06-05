package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapa;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class EtapasProfessorActivity extends AppCompatActivity {

    private String idTurma;

    EditText etTurmaTitle;

    private TextView tvTermoProf;
    private TextView tvProjetoProf;
    private TextView tvEtapa1Prof;
    private TextView tvEtapa2Prof;
    private TextView tvEtapa3Prof;
    private TextView tvFinalProf;

    private ImageButton btTermoProf;
    private ImageButton btProjetoProf;
    private ImageButton btEtapa1Prof;
    private ImageButton btEtapa2Prof;
    private ImageButton btEtapa3Prof;
    private ImageButton btFinalProf;

    private Switch swLiberadaProf;
    private Switch swEncerradaProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapas_professor);

        Intent it = getIntent();
        idTurma = it.getStringExtra("idTurma");

        etTurmaTitle = (EditText) findViewById(R.id.etTurmaTitle);

        tvTermoProf = (TextView) findViewById(R.id.tvTermoProf);
        tvProjetoProf = (TextView) findViewById(R.id.tvProjetoProf);
        tvEtapa1Prof = (TextView) findViewById(R.id.tvEtapa1Prof);
        tvEtapa2Prof = (TextView) findViewById(R.id.tvEtapa2Prof);
        tvEtapa3Prof = (TextView) findViewById(R.id.tvEtapa3Prof);
        tvFinalProf = (TextView) findViewById(R.id.tvFinalProf);

        btTermoProf = (ImageButton) findViewById(R.id.btTermoProf);
        btProjetoProf = (ImageButton) findViewById(R.id.btProjetoProf);
        btEtapa1Prof = (ImageButton) findViewById(R.id.btEtapa1Prof);
        btEtapa2Prof = (ImageButton) findViewById(R.id.btEtapa2Prof);
        btEtapa3Prof = (ImageButton) findViewById(R.id.btEtapa3Prof);
        btFinalProf = (ImageButton) findViewById(R.id.btFinalProf);

        swLiberadaProf = (Switch) findViewById(R.id.swLiberadaProf);
        swEncerradaProf = (Switch) findViewById(R.id.swEncerradaProf);

        getGeralValues();

        etTurmaTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

                DocumentReference turma = store.collection("turma").document(idTurma);

                turma
                        .update("nomeTurma", etTurmaTitle.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mostraToast( "Erro no gravar a modificação!");
                            }
                        });
            }
        });

        swLiberadaProf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

                DocumentReference turma = store.collection("turma").document(idTurma);

                turma
                        .update("liberada", swLiberadaProf.isChecked())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mostraToast( "Erro no gravar a modificação!");
                            }
                        });
            }
        });

        swEncerradaProf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

                DocumentReference turma = store.collection("turma").document(idTurma);

                turma
                        .update("encerrada", swEncerradaProf.isChecked())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                mostraToast( "Erro no gravar a modificação!");
                            }
                        });
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        getGeralValues();
    }

    private void getGeralValues(){
        Integer orderEtapa;

        etTurmaTitle.setText(FonteDados.getTurma(idTurma).getNomeTurma());

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            try { orderEtapa = obj.getIdEtapa(); } catch (NullPointerException ex) { orderEtapa = -1; }
            switch (orderEtapa){
                case 1:
                    try { tvTermoProf.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvTermoProf.setText("Erro!");}
                    break;
                case 2:
                    try { tvProjetoProf.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvProjetoProf.setText("Erro!");}
                    break;
                case 3:
                    try { tvEtapa1Prof.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvEtapa1Prof.setText("Erro!");}
                    break;
                case 4:
                    try { tvEtapa2Prof.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvEtapa2Prof.setText("Erro!");}
                    break;
                case 5:
                    try { tvEtapa3Prof.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvEtapa3Prof.setText("Erro!");}
                    break;
                case 6:
                    try { tvFinalProf.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvFinalProf.setText("Erro!");}
                    break;
            }
        }

        try { swLiberadaProf.setChecked(FonteDados.getTurma(idTurma).isLiberada()); } catch (NullPointerException ex) { }
        try { swEncerradaProf.setChecked(FonteDados.getTurma(idTurma).isEncerrada()); } catch (NullPointerException ex) { }
    }

    public void onClickBt(View view){
        Intent intent = new Intent(view.getContext(), EtapasCriarActivity.class);

        Integer et = 0;

        switch(view.getId())
        {
            case R.id.btTermoProf:
                et = 1;
                intent.putExtra("tvTitleCriar", "Termo");
                intent.putExtra("tvSubTitleCriar", tvTermoProf.getText());
                break;
            case R.id.btProjetoProf:
                et = 2;
                intent.putExtra("tvTitleCriar", "Projeto");
                intent.putExtra("tvSubTitleCriar", tvProjetoProf.getText());
                break;
            case R.id.btEtapa1Prof:
                et = 3;
                intent.putExtra("tvTitleCriar", "Etapa 1");
                intent.putExtra("tvSubTitleCriar", tvEtapa1Prof.getText());
                break;
            case R.id.btEtapa2Prof:
                et = 4;
                intent.putExtra("tvTitleCriar", "Etapa 2");
                intent.putExtra("tvSubTitleCriar", tvEtapa2Prof.getText());
                break;
            case R.id.btEtapa3Prof:
                et= 5;
                intent.putExtra("tvTitleCriar", "Etapa 3");
                intent.putExtra("tvSubTitleCriar", tvEtapa3Prof.getText());
                break;
            case R.id.btFinalProf:
                et = 6;
                intent.putExtra("tvTitleCriar", "Etapa final");
                intent.putExtra("tvSubTitleCriar", tvFinalProf.getText());
                break;
        }

        intent.putExtra("idTurma", idTurma);
        intent.putExtra("idEtapa", et);

        view.getContext().startActivity(intent);
    }

    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    public void onClickIbBack(View view){
        finish();
    }
}