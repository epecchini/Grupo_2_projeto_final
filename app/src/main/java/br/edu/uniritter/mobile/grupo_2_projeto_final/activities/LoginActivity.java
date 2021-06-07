package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;
import android.content.Intent;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;
    private FirebaseAuth mAuth;

    private String idProf;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseServices.getFirebaseAuthInstance();
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btLogar = findViewById(R.id.btCadastrar);

        FirebaseFirestore firebaseFirestore = FirebaseServices.getFirebaseFirestoreInstance();

        Query query = firebaseFirestore.collection("alunos").whereEqualTo("eprof", true).limit(1);
        query.addSnapshotListener((value, error) -> {
            List<ClsAluno> list = value.toObjects(ClsAluno.class);
            if(!list.isEmpty()) idProf = list.get(0).getIdRealtime();
        });

        Query queryTurma = firebaseFirestore.collection("turma").limit(100);
        queryTurma.addSnapshotListener((value, error) -> {
            List<ClsTurma> list = value.toObjects(ClsTurma.class);
            for(ClsTurma obj: list){
                FonteDados.putTurma(obj);
            }
        });

        Query queryAlunos = firebaseFirestore.collection("alunos").limit(100);
        queryAlunos.addSnapshotListener((value, error) -> {
            List<ClsAluno> list = value.toObjects(ClsAluno.class);
            for(ClsAluno obj: list){
                FonteDados.putAluno(obj);
            }
        });

        Query queryEtapaAluno = firebaseFirestore.collection("etapaAluno").limit(500);
        queryEtapaAluno.addSnapshotListener((value, error) -> {
            List<ClsEtapaAluno> list = value.toObjects(ClsEtapaAluno.class);
            for(ClsEtapaAluno obj: list){
                FonteDados.putTodasAsEtapasDosAluno(obj);
            }
        });

        btLogar.setOnClickListener(v -> {
            if(TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etSenha.getText().toString())) return;

            mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etSenha.getText().toString())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                try {
                                    Log.i("Eliseo_idProf", idProf);
                                } catch (Exception ex) {
                                    Log.e("Eliseo_idProf", ex.getMessage());
                                }

                                Boolean eProf = !TextUtils.isEmpty(idProf) && idProf.equals(mAuth.getCurrentUser().getUid());

                                if(!eProf) getInfoAlunoAtual();

                                startActivity(new Intent(LoginActivity.this, eProf ? HomeProfessorActivity.class : HomeAlunoActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.-> peguei esse codigo da doc do FireBase

                                Toast.makeText(LoginActivity.this, "Autenticação Falhou.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }

    private void getInfoAlunoAtual(){
        FirebaseFirestore firebaseFirestore = FirebaseServices.getFirebaseFirestoreInstance();

        for (ClsAluno obj : FonteDados.getAluno_list()) {
            if(obj.getIdRealtime().equals(mAuth.getCurrentUser().getUid())) {
                FonteDados.setIdAlunoAtual(obj.getId());
                break;
            }
        }

        Log.i("Eliseo_getAllInfo_getCurrentUser", mAuth.getCurrentUser().getUid());
        Log.i("Eliseo_getAllInfo_getIdAlunoAtual", FonteDados.getIdAlunoAtual());
        try{
            Log.i("Eliseo_getAllInfo_getIdAlunoAtual_getIdTurmaAtual", FonteDados.getAlunoAtual().getIdTurmaAtual());
        }catch(Exception ex){}


        Query queryAlunos = firebaseFirestore.collection("alunos").whereEqualTo("idRealtime", mAuth.getCurrentUser().getUid()).limit(1);
        queryAlunos.addSnapshotListener((value, error) -> {
            for (ClsTurma obj : FonteDados.getTurma_list()) {
                try{
                    Log.i("Eliseo_getAllInfo_obj.getId", obj.getId());
                    obj.setCadastrado(FonteDados.getAlunoAtual().getIdTurmaAtual().equals(obj.getId()));
                }catch (Exception ex){
                    Log.i("Eliseo_getAllInfo_obj.getId_erro", ex.getMessage());
                }
            }

            for (ClsEtapaAluno obj : FonteDados.getTodasAsEtapasDosAluno_list()) {
                if(obj.getIdAluno().equals(FonteDados.getIdAlunoAtual())) {
                    FonteDados.putEtapaAluno(obj);
                }
            }
        });

        Log.i("Eliseo_getAllInfo_queryEtapaAluno", FonteDados.getIdAlunoAtual());

        Query queryEtapaAluno = firebaseFirestore.collection("etapaAluno").whereEqualTo("idAluno", FonteDados.getIdAlunoAtual()).limit(100);
        queryEtapaAluno.addSnapshotListener((value, error) -> {
            List<ClsEtapaAluno> list = value.toObjects(ClsEtapaAluno.class);
            for(ClsEtapaAluno obj: list){
                FonteDados.putEtapaAluno(obj);
            }
        });

        Log.i("Eliseo_getInfoAlunoAtual_termino", "termino");
    }
}
