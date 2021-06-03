package br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.CadastroActivity;
import br.edu.uniritter.mobile.grupo_2_projeto_final.databinding.LayoutTurmasBinding;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;

public class AdaptHomeProf extends RecyclerView.Adapter<AdaptHomeProf.ViewHolder> {
    private List<ClsTurma> list;

    public AdaptHomeProf(List<ClsTurma> list) { this.list = list; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LayoutTurmasBinding bindind;
        public ViewHolder(@NonNull LayoutTurmasBinding bindind){
            super(bindind.getRoot());
            this.bindind = bindind;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutTurmasBinding lub;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        lub = LayoutTurmasBinding.inflate(inflator, parent, false);
        return new ViewHolder(lub);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindind.setClsTurma(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), CadastroActivity.class);
                view.getContext().startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() { return this.list.size(); }
}
