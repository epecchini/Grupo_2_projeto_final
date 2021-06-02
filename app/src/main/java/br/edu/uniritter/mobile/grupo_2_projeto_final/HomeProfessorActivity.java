package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptHomeProf;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntHomeProf;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

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
    public void bindTurma(List<ClsTurma> list) {
        RecyclerView rv = findViewById(R.id.rvInfoGeral);
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
