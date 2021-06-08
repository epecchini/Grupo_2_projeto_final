package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptListaAlunos;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntListaAlunos;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

public class ListaAlunosActivity extends AppCompatActivity
        implements IntListaAlunos.intListaAlunosPresView {

    private IntListaAlunos.intListaAlunosPres presenter;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        radioGroup = (RadioGroup) findViewById(R.id.rgListaAlunosGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onResume();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.rbListaAlunosAll:
                bindListaAlunos(FonteDados.getAluno_list());
                break;
            case R.id.rbListaAlunosPendente:
                List<ClsAluno> list = new ArrayList<>();

                for(ClsEtapaAluno obj: Stream.of(FonteDados.getTodasAsEtapasDosAluno_list()).filter(item-> item.getStatus() == 1).collect(Collectors.toList())){
                    list.add(FonteDados.getAluno(obj.getIdAluno()));
                }

                bindListaAlunos(list);
                break;
            case R.id.rbListaAlunosBloqueado:
                bindListaAlunos(Stream.of(FonteDados.getAluno_list()).filter(item-> item.getTentativasAcesso() >= 3).collect(Collectors.toList()));
                break;
        }
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