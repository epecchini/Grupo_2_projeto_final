package br.edu.uniritter.mobile.grupo_2_projeto_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.PendenciasAdapter;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.PendenciasActivityPresenterClass;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.PendenciasActivityPresenterInterface;
import br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup.Pendencias;

public class PendenciasActivity extends AppCompatActivity
        implements PendenciasActivityPresenterInterface.PendenciasActivityPresenterView {

    private PendenciasActivityPresenterInterface.PendenciasActivityPresenterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendencias);

        this.presenter =  new PendenciasActivityPresenterClass(this);
    }

    @Override
    public void bindPendencias(List<Pendencias> list) {
        RecyclerView rv = findViewById(R.id.rvInfoGeral);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //
        rv.setLayoutManager(llm);
        PendenciasAdapter cls = new PendenciasAdapter(list);
        rv.setAdapter(cls);

        mostraToast("xxx");
    }

    @Override
    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    @Override
    public Context getContexto() { return this.getApplicationContext(); }
}