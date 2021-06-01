package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurmaAluno;

public class HomeAlunoActivity extends AppCompatActivity
        implements IntTurmaAluno.intTurmAlunoPresView {

    private IntTurmaAluno.intTurmAlunoPres presenter;

    private List<ClsTurmaAluno> list = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aluno);
   // }

  //  public void carregaInfo(View view) {
        FirebaseApp.initializeApp(HomeAlunoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Query query;
        query = databaseReference.child("turmaAluno");

        list.clear();


        final int[] contador = {0};

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot objSanpshot:snapshot.getChildren()){

                    contador[0] = contador[0] + 1;

                   // mostraToast(String.valueOf(contador[0]));

                    ClsTurmaAluno cls = new ClsTurmaAluno(
                            "11",
                            "bb",
                            "ds.child(ordem).getValue().toString()",
                            String.valueOf(contador[0]),
                            true);


                 //  ClsTurmaAluno cls = objSanpshot.getValue(ClsTurmaAluno.class);
                    //
                 /*    DatabaseReference reff;
                    //
                    reff = FirebaseDatabase.getInstance().getReference().child("turma").child("0");
                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            String name = snapshot.child("nome").getValue().toString();
                            cls.setNomeTurma(name);
                        }
                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        }
                    });*/

                    list.add(cls);

                    bindTurmaAluno(list);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void bindTurmaAluno(List<ClsTurmaAluno> list) {
        RecyclerView rv = findViewById(R.id.rvInfoGeral);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //
        rv.setLayoutManager(llm);
        AdaptTurmaAluno cls = new AdaptTurmaAluno(list);
        rv.setAdapter(cls);
    }

    @Override
    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    @Override
    public Context getContexto() { return this.getApplicationContext(); }
}
