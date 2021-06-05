package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;
import android.content.Intent;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import br.edu.uniritter.mobile.grupo_2_projeto_final.DeBug;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseServices.getFirebaseAuth();
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btLogar = findViewById(R.id.btCadastrar);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etSenha.getText().toString())) return;

                mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etSenha.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    getAllInfo();
                                    // Sign in success, update UI with the signed-in user's information -> peguei esse codigo da doc do FireBase
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    startActivity(new Intent(LoginActivity.this, DeBug.class));
                                } else {
                                    // If sign in fails, display a message to the user.-> peguei esse codigo da doc do FireBase

                                    Toast.makeText(LoginActivity.this, "Autenticação Falhou.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void getAllInfo(){
        FirebaseFirestore firebaseFirestore = FirebaseServices.getFirebaseFirestoreInstance();

        Query query;
        query = firebaseFirestore.collection("turmaAluno").whereEqualTo("idAluno", mAuth.getCurrentUser().getUid()).limit(100);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<ClsTurmaAluno> list = value.toObjects(ClsTurmaAluno.class);
                for(ClsTurmaAluno obj: list){
                    FonteDados.putTurmaAluno(obj);
                }
            }
        });

        query = firebaseFirestore.collection("turma").limit(100);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<ClsTurma> list = value.toObjects(ClsTurma.class);
                for(ClsTurma obj: list){
                    try { obj.setCadastrado(FonteDados.getTurmaAluno(obj.getId()).getId() != null); } catch (NullPointerException ex) { }
                    FonteDados.putTurma(obj);
                }
            }
        });

        query = firebaseFirestore.collection("etapaAluno").whereEqualTo("idAluno", mAuth.getCurrentUser().getUid()).limit(100);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<ClsEtapaAluno> list = value.toObjects(ClsEtapaAluno.class);
                for(ClsEtapaAluno obj: list){
                    FonteDados.putEtapaAluno(obj);
                }
            }
        });

        DatabaseReference firebase = FirebaseServices.getFirebaseDatabase();
        firebase.child("Usuarios")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        etEmail.setText((int) snapshot.getChildrenCount());


                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            FonteDados.putAluno(postSnapshot.getValue(ClsAluno.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(LoginActivity.this, "Carregamento dos usuários falhou: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
        });
    }
}
