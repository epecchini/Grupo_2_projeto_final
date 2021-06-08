package br.edu.uniritter.mobile.grupo_2_projeto_final.SQLite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.uniritter.mobile.grupo_2_projeto_final.PendenciasActivity;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;

public class MainSQLiteActivity extends AppCompatActivity {

    EditText editNome;
    Button buttonCadastrar;
    Button buttonListar;
    Button buttonApagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);

        editNome = (EditText) findViewById(R.id.editNome);
        buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        buttonListar = (Button) findViewById(R.id.buttonListar);
        buttonApagar = (Button) findViewById(R.id.buttonApagar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cadastrar(editNome.getText().toString());
            }
        });

        buttonListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainSQLiteActivity.this, ListaAlunosSQLiteActivity.class));
            }
        });
    }

    private  void Cadastrar(String Nome){
        BaseHelper helper= new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db= helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("Nome",Nome);

            db.insert("ALUNOS",null,c);

            db.close();
            Toast.makeText(this,"Alunos Inseridos",Toast.LENGTH_SHORT).show();

            editNome.setText(null);
            editNome.setText("");

            editNome.requestFocus();
        } catch (Exception e){
            Toast.makeText(this,"Erro" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirPendenciasActivity(View view){
        Intent intent = new Intent(this, PendenciasActivity.class);
        startActivity(intent);
    }
}