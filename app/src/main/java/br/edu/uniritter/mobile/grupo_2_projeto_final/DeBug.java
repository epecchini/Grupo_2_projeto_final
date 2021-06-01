package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class DeBug extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }

    public void telaHomeAluno(View view) {

      /*  List<ClsTurmaAluno> list = new ArrayList<>();

        DatabaseReference reff;

        reff = FirebaseDatabase.getInstance().getReference().child("etapa");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                //String name =snapshot.child("texto").getValue().toString();

                //message(name + "       retornado");

                ClsTurmaAluno obj = new ClsTurmaAluno(
                        "name",
                        "bb",
                        "cc",
                        "nomeTurma",
                        true);
                list.add(obj);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });*/


     /*   ArrayList<ClsTurmaAluno> list = new ArrayList<>();

        ClsTurmaAluno obj = new ClsTurmaAluno(
                "name",
                "bb",
                "cc",
                "nomeTurma",
                true);
        list.add(obj);*/






        Intent intent = new Intent(this, HomeAlunoActivity.class);

        // intent.putExtra("TurmaAluno_list", list);


        startActivity(intent);
    }

    public void getData1(View view){

        TextView tv = findViewById(R.id.textView2);

        DatabaseReference reff;

        reff = FirebaseDatabase.getInstance().getReference().child("etapa").child("99");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String name =snapshot.child("texto").getValue().toString();

                //message(name + "       retornado");


                tv.setText(name);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    public void getData(View view){
        Toast.makeText(this,"ok1", Toast.LENGTH_LONG).show();

        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

/*
        ClsEtapa clsetapa = new ClsEtapa(12, 1, "xxx", 1, "nome1", "texto1");

        mDatabase.child("etapa").child(String.valueOf(clsetapa.getId())).setValue(clsetapa);

        ClsEtapa clsetapa1 = new ClsEtapa(13, 1, "yyy", 3, "nome2", "texto2");

        mDatabase.child("etapa").child(String.valueOf(clsetapa1.getId())).setValue(clsetapa1);

        clsetapa1.setTexto("texto_prova");

        mDatabase.child("etapa").child(String.valueOf(clsetapa1.getId())).setValue(clsetapa1);

        String key = mDatabase.child("etapa").push().getKey();

        TextView tv = findViewById(R.id.textView2);
        //  tv.setText(key);

        ClsEtapa clsetapa2 = new ClsEtapa();
        clsetapa2.setId(99);
        clsetapa2.setTexto(key);

        mDatabase.child("etapa").child(String.valueOf(clsetapa2.getId())).setValue(clsetapa2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                        message("Write was successful!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                        message("Write failed");
                    }
                });

        String a;
        DatabaseReference reff;

        reff = FirebaseDatabase.getInstance().getReference().child("etapa").child(String.valueOf(clsetapa2.getId()));
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String name =snapshot.child("texto").getValue().toString();

                message(name + "       retornado");


                tv.setText(name);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
*/



        Toast.makeText(this,"ok2",Toast.LENGTH_LONG).show();
    }

    private void message(String mess){
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }
}