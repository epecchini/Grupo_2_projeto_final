package br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter;

import android.content.Context;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;

public interface IntListaAlunos {
    interface intListaAlunosPresView {
        public void bindListaAlunos(List<ClsAluno> list);
        public void mostraToast(String msg);
        public Context getContexto();
    }

    interface intListaAlunosPres {
        public void start();
        public void encerrar();
    }
}
