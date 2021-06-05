package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptHomeProf;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntHomeProf;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapa;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class HomeProfessorActivity extends AppCompatActivity
        implements IntHomeProf.intHomeProfPresView {

    private IntHomeProf.intHomeProfPres presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_professor);

        /*List<ClsTurma> list = new ArrayList<>();

        for (String key : FonteDados.getTurma_map().keySet()) {
            list.add(FonteDados.getTurma(key));
        }
        bindTurma(list);*/

        bindTurma(FonteDados.getTurma_list());
    }

    @Override
    public void onResume(){
        super.onResume();
        bindTurma(FonteDados.getTurma_list());
    }

    public void onClickListaAlunos(View view){
        Intent intent = new Intent(view.getContext(), ListaAlunosActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onClickPendencias(View view){

    }

    public void addNewTurma(View view){
        FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

        ClsTurma obj = new ClsTurma();

        obj.setEncerrada(false);
        obj.setLiberada(false);
        obj.setNomeTurma("Nova turma");

        List<ClsEtapa> list = new ArrayList<>();

        for(Integer i = 1; i <= 6; i++){
            ClsEtapa etapa = new ClsEtapa();

            Date c = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:mm");
            etapa.setDataLimite(df.format(c));

            etapa.setIdEtapa(i);

            switch (i){
                case 1:
                    etapa.setNomeEtapa("Termo do projeto");
                    etapa.setTextoEtapa("Texto explicativo do termo do projeto");
                    break;
                case 2:
                    etapa.setNomeEtapa("Sobre o projeto");
                    etapa.setTextoEtapa("Texto explicativo do projeto");
                    break;
                case 3:
                case 4:
                case 5:
                    etapa.setNomeEtapa("Etapa " + i);
                    etapa.setTextoEtapa("Texto explicativo da etapa " + i);
                    break;
                case 6:
                    etapa.setNomeEtapa("Etapa final");
                    etapa.setTextoEtapa("Texto explicativo da etapa final");
                    break;
            }

            list.add(etapa);
        }

        obj.setEtapa(list);

        store.collection("turma").add(obj)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        bindTurma(FonteDados.getTurma_list());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mostraToast("Erro no criar essa etapa!");
                    }
                });
    }

    @Override
    public void bindTurma(List<ClsTurma> list) {
        RecyclerView rv = findViewById(R.id.rvHomeProfessor);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //
        rv.setLayoutManager(llm);
        AdaptHomeProf cls = new AdaptHomeProf(list);
        rv.setAdapter(cls);
    }

    @Override
    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    @Override
    public Context getContexto() { return this.getApplicationContext(); }
}
