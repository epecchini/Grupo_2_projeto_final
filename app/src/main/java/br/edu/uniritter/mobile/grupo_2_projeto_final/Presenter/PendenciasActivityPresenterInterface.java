package br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter;

import android.content.Context;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup.Pendencias;

public interface PendenciasActivityPresenterInterface {
    interface PendenciasActivityPresenterView {
        public void bindPendencias(List<Pendencias> list);
        public void mostraToast(String msg);
        public Context getContexto();
    }

    interface PendenciasActivityPresenterPresenter {
        public void start();
        public void encerrar();
    }
}
