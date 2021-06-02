package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

public class HomeAlunoActivity extends AppCompatActivity
        implements IntTurmaAluno.intTurmAlunoPresView {

    private IntTurmaAluno.intTurmAlunoPres presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aluno);

        List<ClsTurma> list = new ArrayList<>();

        for (ClsTurma obj : FonteDados.getTurma_list()) {
            if(obj.isLiberada() && !obj.isEncerrada()) list.add(obj);
        }

        bindTurmaAluno(list);
    }

    @Override
    public void bindTurmaAluno(List<ClsTurma> list) {
        RecyclerView rv = findViewById(R.id.rvInfoGeral);
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
