package br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.databinding.LayoutTurmasAlunoBinding;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;

public class AdaptTurmaAluno extends RecyclerView.Adapter<AdaptTurmaAluno.ViewHolder> {
    private List<ClsTurma> list;

    public AdaptTurmaAluno(List<ClsTurma> list) { this.list = list; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LayoutTurmasAlunoBinding bindind;
        public ViewHolder(@NonNull LayoutTurmasAlunoBinding bindind){
            super(bindind.getRoot());
            this.bindind = bindind;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutTurmasAlunoBinding lub;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        lub = LayoutTurmasAlunoBinding.inflate(inflator, parent, false);
        return new ViewHolder(lub);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { holder.bindind.setClsTurma(list.get(position)); }

    @Override
    public int getItemCount() { return this.list.size(); }
}
