package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import br.edu.uniritter.mobile.grupo_2_projeto_final.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private CheckBox cbProfessor;
    private Button btCadastro;
    private FirebaseAuth mAuth;
    private Usuario u;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);


        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.email);
        etSenha = findViewById(R.id.etSenha);
        cbProfessor = findViewById(R.id.cbProfessor);
        btCadastro = findViewById(R.id.btLogar);
        mAuth = FirebaseAuth.getInstance();

        btCadastro.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                recuperarDados();
                criarLogin();
            }
        });
    }

    private void criarLogin() {
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    u.setId(user.getUid());
                    u.salvarDados();
                    startActivity(new Intent(CadastroActivity.this,PrincipalActivity.class));
                }else{
                    Toast.makeText(CadastroActivity.this, "Erro ao criar o Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void recuperarDados() {
        if(etNome.getText().toString() == "" || etEmail.getText().toString() == "" || etSenha.getText().toString() == ""){
            Toast.makeText(this, "Você deve preencher todos os dados", Toast.LENGTH_LONG).show();
        }else{
            u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setSenha(etSenha.getText().toString());
            //u.setProfessor(cbProfessor.getShowSoftInputOnFocus());

        }

    }


}
