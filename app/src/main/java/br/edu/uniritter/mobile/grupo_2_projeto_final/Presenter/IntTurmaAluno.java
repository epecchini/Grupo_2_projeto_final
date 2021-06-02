package br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter;

import android.content.Context;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;

public interface IntTurmaAluno {
    interface intTurmAlunoPresView {
        public void bindTurmaAluno(List<ClsTurma> list);
        public void mostraToast(String msg);
        public Context getContexto();
    }

    interface intTurmAlunoPres {
        public void start();
        public void encerrar();
    }
}
