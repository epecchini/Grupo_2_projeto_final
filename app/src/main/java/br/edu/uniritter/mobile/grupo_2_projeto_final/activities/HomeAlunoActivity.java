package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class HomeAlunoActivity extends AppCompatActivity
        implements IntTurmaAluno.intTurmAlunoPresView {

    private IntTurmaAluno.intTurmAlunoPres presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aluno);

        Log.i("Eliseo_onCreate_HomeAlunoActivity", "ok");
    }

    @Override
    public void onResume(){
        super.onResume();

        List<ClsTurma> list = new ArrayList<>();

        for (ClsTurma obj : FonteDados.getTurma_list()) {
            if(obj.isLiberada() && !obj.isEncerrada()) list.add(obj);
        }

        bindTurmaAluno(list);
    }

    public void onClick(View view) {
        if(TextUtils.isEmpty(FonteDados.getAlunoAtual().getIdTurmaAtual())) return;

        Intent intent = new Intent(view.getContext(), EtapasAlunoActivity.class);
        intent.putExtra("idTurma", FonteDados.getAlunoAtual().getIdTurmaAtual());
        view.getContext().startActivity(intent);
    }

    public void onClickDadosPessoais(View view) {
        Intent intent = new Intent(view.getContext(), CadastroActivity.class);
        intent.putExtra("modificacaoDados", true);
        view.getContext().startActivity(intent);
    }

    @Override
    public void bindTurmaAluno(List<ClsTurma> list) {
        RecyclerView rv = findViewById(R.id.rvHomeAluno);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //
        rv.setLayoutManager(llm);
        AdaptTurmaAluno cls = new AdaptTurmaAluno(list);
        rv.setAdapter(cls);
    }

    @Override
    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    @Override
    public Context getContexto() { return this.getApplicationContext(); }
}
