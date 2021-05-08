package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, senha;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.tbUsuario);
        senha = (EditText) findViewById(R.id.tbSenha);
    }

    public void clicarBotao (View view){
        if(usuario.getText().toString().equals("Team3") && senha.getText().toString().equals("1234"))
            startActivity(new Intent(LoginActivity.this, MainSQLiteActivity.class));
        else
            Toast.makeText(this,"Usuario ou senha invalidos",Toast.LENGTH_SHORT).show();
    }
}