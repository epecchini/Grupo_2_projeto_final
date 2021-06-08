package br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.activities.EstadoAlunoActivity;
import br.edu.uniritter.mobile.grupo_2_projeto_final.databinding.LayoutAlunoBinding;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;

public class AdaptListaAlunos extends RecyclerView.Adapter<AdaptListaAlunos.ViewHolder> {
    private List<ClsAluno> list;

    public AdaptListaAlunos(List<ClsAluno> list) { this.list = list; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LayoutAlunoBinding bindind;
        public ViewHolder(@NonNull LayoutAlunoBinding bindind){
            super(bindind.getRoot());
            this.bindind = bindind;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutAlunoBinding lub;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        lub = LayoutAlunoBinding.inflate(inflator, parent, false);
        return new ViewHolder(lub);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptListaAlunos.ViewHolder holder, int position) {
        holder.bindind.setClsAluno(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EstadoAlunoActivity.class);
                intent.putExtra("idAluno", holder.bindind.getClsAluno().getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return this.list.size(); }
}
