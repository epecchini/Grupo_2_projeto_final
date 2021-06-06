package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptListaAlunos;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntListaAlunos;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

public class ListaAlunosActivity extends AppCompatActivity
        implements IntListaAlunos.intListaAlunosPresView {

    private IntListaAlunos.intListaAlunosPres presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgListaAlunosGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                List<ClsAluno> list = new ArrayList<>();

                switch (checkedId){
                    case R.id.rbListaAlunosAll:
                        bindListaAlunos(FonteDados.getAluno_list());
                        break;
                    case R.id.rbListaAlunosPendente:
                        for (ClsAluno obj : FonteDados.getAluno_list()) {
                            list.add(obj);
                        }

                        bindListaAlunos(list);
                        break;
                    case R.id.rbListaAlunosBloqueado:
                        for (ClsAluno obj : FonteDados.getAluno_list()) {
                            if(obj.getTentativasAcesso() < 3) list.add(obj);
                        }

                        bindListaAlunos(list);
                        break;
                }
            }
        });

        bindListaAlunos(FonteDados.getAluno_list());
    }

    @Override
    public void bindListaAlunos(List<ClsAluno> list) {
        RecyclerView rv = findViewById(R.id.rvListaAlunos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //
        rv.setLayoutManager(llm);
        AdaptListaAlunos cls = new AdaptListaAlunos(list);
        rv.setAdapter(cls);
    }

    @Override
    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    @Override
    public Context getContexto() { return this.getApplicationContext(); }

    public void onClickIbBackListAluno(View view){
        finish();
    }
}