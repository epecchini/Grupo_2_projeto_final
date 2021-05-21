package br.edu.uniritter.mobile.grupo_2_projeto_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btLogar;
    private Button btCadastrar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira);

        btLogar = findViewById(R.id.btCadastrar);
        btCadastrar = findViewById(R.id.btCadastro);
        
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telalogar();
            }
        });
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaCadastrar();
            }
        });

    }

    public void telaCadastrar() {
        Intent Intent = new Intent(this, CadastroActivity.class);
        startActivity(Intent);

    }

    public void telalogar() {
        Intent Intent = new Intent(this, LoginActivity.class);
        startActivity(Intent);
    }

}