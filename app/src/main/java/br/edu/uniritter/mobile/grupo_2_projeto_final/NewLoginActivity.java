package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.uniritter.mobile.grupo_2_projeto_final.model.Usuario;

public class NewLoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btLogar;
    private FirebaseAuth mAuth;
    private Usuario u;


    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.email);
        etSenha = findViewById(R.id.etSenha);
        btLogar = findViewById(R.id.btCadastrar);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receberDados();
                logar();
            }
        });

    }

    private void logar() {
        mAuth.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information -> peguei esse codigo da doc do FireBase
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(NewLoginActivity.this, ListaAlunosSQLiteActivity.class);
                            //startActivity(new Intent(NewLoginActivity.this,PrincipalActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.-> peguei esse codigo da doc do FireBase

                            Toast.makeText(NewLoginActivity.this, "Autenticação Falhou.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void receberDados() {
        u.setEmail(etEmail.getText().toString());
        u.setSenha(etSenha.getText().toString());
    }
}
