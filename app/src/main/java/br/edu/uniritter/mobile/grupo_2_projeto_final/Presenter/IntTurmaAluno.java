package br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter;

import android.content.Context;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurmaAluno;

public interface IntTurmaAluno {
    interface intTurmAlunoPresView {
        public void bindTurmaAluno(List<ClsTurmaAluno> list);
        public void mostraToast(String msg);
        public Context getContexto();
    }

    interface intTurmAlunoPres {
        public void start();
        public void encerrar();
    }
}
