package br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.activities.EtapasProfessorActivity;
import br.edu.uniritter.mobile.grupo_2_projeto_final.databinding.LayoutTurmasBinding;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;

public class AdaptHomeProf extends RecyclerView.Adapter<AdaptHomeProf.ViewHolder> {
    private List<ClsTurma> list;

    public AdaptHomeProf(List<ClsTurma> list) { this.list = list; }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //private Switch swLiberada;
        //private Switch swEncerrada;

        LayoutTurmasBinding bindind;
        public ViewHolder(@NonNull LayoutTurmasBinding bindind){
            super(bindind.getRoot());

            //swLiberada = (Switch)itemView.findViewById(R.id.swLiberada);
            //swEncerrada = (Switch)itemView.findViewById(R.id.swEncerrada);

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
                Intent intent = new Intent(view.getContext(), EtapasProfessorActivity.class);
                intent.putExtra("idTurma", holder.bindind.getClsTurma().getId());
                view.getContext().startActivity(intent);
            }
        });

        /*holder.swLiberada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

                DocumentReference etapaAluno = store.collection("turma").document(holder.bindind.getClsTurma().getId());

                etapaAluno
                        .update("liberada", isChecked )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
            }
        });

        holder.swEncerrada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

                DocumentReference etapaAluno = store.collection("turma").document(holder.bindind.getClsTurma().getId());

                etapaAluno
                        .update("encerrada", isChecked )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
            }
        });*/
    }

    @Override
    public int getItemCount() { return this.list.size(); }
}
