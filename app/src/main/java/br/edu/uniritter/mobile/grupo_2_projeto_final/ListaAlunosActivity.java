package br.edu.uniritter.mobile.grupo_2_projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.edu.uniritter.mobile.grupo_2_projeto_final.SQLite.BaseHelper;

public class ListaAlunosActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        listView=(ListView) findViewById(R.id.listView);

        CarregarLista();
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CarregarLista(){
        listaAlunos = lista();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAlunos);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> lista() {
        ArrayList<String>dados= new ArrayList<>();
        BaseHelper helper= new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db= helper.getReadableDatabase();
        String sql= "select Id,Nome from Alunos";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()) {
            do {
                String linea = c.getInt(0) + " " + c.getString(1);
                dados.add(linea);
            } while (c.moveToNext());
        }
        db.close();
        return dados;
    }
}