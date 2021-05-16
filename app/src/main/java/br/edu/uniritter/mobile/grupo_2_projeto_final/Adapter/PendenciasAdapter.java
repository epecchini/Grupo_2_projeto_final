package br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup.Pendencias;
import br.edu.uniritter.mobile.grupo_2_projeto_final.databinding.LayoutPendenciasBinding;

public class PendenciasAdapter extends RecyclerView.Adapter<PendenciasAdapter.ViewHolder> {
    private List<Pendencias> list;

    public PendenciasAdapter(List<Pendencias> list) { this.list = list; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LayoutPendenciasBinding bindind;
        public ViewHolder(@NonNull LayoutPendenciasBinding bindind){
            super(bindind.getRoot());
            this.bindind = bindind;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutPendenciasBinding lub;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        lub = LayoutPendenciasBinding.inflate(inflator, parent, false);
        return new ViewHolder(lub);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { holder.bindind.setPendencias(list.get(position)); }

    @Override
    public int getItemCount() { return this.list.size(); }
}
