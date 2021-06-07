package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapa;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class EstadoAlunoActivity extends AppCompatActivity {

    private String idAluno;

    FirebaseFirestore store;

    TextView tvNomeEstadoAluno;
    TextView tvSobrenomeEstadoAluno;
    CheckBox cbTermoEstadoAluno;
    CheckBox cbProjetoEstadoAluno;
    CheckBox cbEtapa1EstadoAluno;
    CheckBox cbEtapa2EstadoAluno;
    CheckBox cbEtapa3EstadoAluno;
    CheckBox cbFinalEstadoAluno;
    Button btEtapa1EstadoAluno;
    Button btEtapa2EstadoAluno;
    Button btEtapa3EstadoAluno;
    Button btFinalEstadoAluno;
    Button btBloqueadoEstadoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_aluno);

        store = FirebaseServices.getFirebaseFirestoreInstance();

        tvNomeEstadoAluno = (TextView) findViewById(R.id.tvNomeEstadoAluno);
        tvSobrenomeEstadoAluno = (TextView) findViewById(R.id.tvSobrenomeEstadoAluno);
        cbTermoEstadoAluno = (CheckBox) findViewById(R.id.cbTermoEstadoAluno);
        cbProjetoEstadoAluno = (CheckBox) findViewById(R.id.cbProjetoEstadoAluno);
        cbEtapa1EstadoAluno = (CheckBox) findViewById(R.id.cbEtapa1EstadoAluno);
        cbEtapa2EstadoAluno = (CheckBox) findViewById(R.id.cbEtapa2EstadoAluno);
        cbEtapa3EstadoAluno = (CheckBox) findViewById(R.id.cbEtapa3EstadoAluno);
        cbFinalEstadoAluno = (CheckBox) findViewById(R.id.cbFinalEstadoAluno);
        btEtapa1EstadoAluno = (Button) findViewById(R.id.btEtapa1EstadoAluno);
        btEtapa2EstadoAluno = (Button) findViewById(R.id.btEtapa2EstadoAluno);
        btEtapa3EstadoAluno = (Button) findViewById(R.id.btEtapa3EstadoAluno);
        btFinalEstadoAluno = (Button) findViewById(R.id.btFinalEstadoAluno);
        btBloqueadoEstadoAluno = (Button) findViewById(R.id.btBloqueadoEstadoAluno);

        Intent it = getIntent();

        idAluno = it.getStringExtra("idAluno");

        Log.i("Eliseo_EstadoAlunoActivity", "onCreate");
        Log.i("Eliseo_EstadoAlunoActivity_onCreate_idAluno", idAluno);

        ClsAluno obj = FonteDados.getAluno(idAluno);

        tvNomeEstadoAluno.setText(obj.getNomeAluno());
        tvSobrenomeEstadoAluno.setText(obj.getSobrenome());

        Integer orderEtapa;

        try{
            for (ClsEtapa trm : FonteDados.getTurma(obj.getIdTurmaAtual()).getEtapa()) {
                try { orderEtapa = trm.getIdEtapa(); } catch (NullPointerException ex) { orderEtapa = -1; }
                switch (orderEtapa){
                    case 1: try { cbTermoEstadoAluno.setText(trm.getNomeEtapa()); } catch (NullPointerException ex) { } break;
                    case 2: try { cbProjetoEstadoAluno.setText(trm.getNomeEtapa()); } catch (NullPointerException ex) { } break;
                    case 3: try { cbEtapa1EstadoAluno.setText(trm.getNomeEtapa()); } catch (NullPointerException ex) { } break;
                    case 4: try { cbEtapa2EstadoAluno.setText(trm.getNomeEtapa()); } catch (NullPointerException ex) { } break;
                    case 5: try { cbEtapa3EstadoAluno.setText(trm.getNomeEtapa()); } catch (NullPointerException ex) { } break;
                    case 6: try { cbFinalEstadoAluno.setText(trm.getNomeEtapa()); } catch (NullPointerException ex) { } break;
                }
            }

            for (ClsEtapaAluno etapa : getEtapas(obj.getIdTurmaAtual())) {
                try { orderEtapa = etapa.getIdEtapa(); } catch (NullPointerException ex) { orderEtapa = -1; }
                switch (orderEtapa){
                    case 1:
                        try { cbTermoEstadoAluno.setChecked(etapaConcluida(etapa.getStatus())); } catch (NullPointerException ex) { }
                        break;
                    case 2:
                        try { cbProjetoEstadoAluno.setChecked(etapaConcluida(etapa.getStatus())); } catch (NullPointerException ex) { }
                        break;
                    case 3:
                        cbEtapa1EstadoAluno.setTag(etapa.getId());
                        try { cbEtapa1EstadoAluno.setChecked(etapaConcluida(etapa.getStatus())); } catch (NullPointerException ex) { }
                        break;
                    case 4:
                        cbEtapa2EstadoAluno.setTag(etapa.getId());
                        try { cbEtapa2EstadoAluno.setChecked(etapaConcluida(etapa.getStatus())); } catch (NullPointerException ex) { }
                        break;
                    case 5:
                        cbEtapa3EstadoAluno.setTag(etapa.getId());
                        try { cbEtapa3EstadoAluno.setChecked(etapaConcluida(etapa.getStatus())); } catch (NullPointerException ex) { }
                        break;
                    case 6:
                        cbFinalEstadoAluno.setTag(etapa.getId());
                        try { cbFinalEstadoAluno.setChecked(etapaConcluida(etapa.getStatus())); } catch (NullPointerException ex) { }
                        break;
                }
            }
        }catch (Exception ex){
        }

        btBloqueadoEstadoAluno.setVisibility(obj.getTentativasAcesso() >= 3 ? Button.VISIBLE : Button.INVISIBLE);

        btBloqueadoEstadoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference aluno = store.collection("alunos").document(idAluno);

                aluno
                        .update("tentativasAcesso", 0)
                        .addOnSuccessListener(aVoid -> btBloqueadoEstadoAluno.setVisibility(Button.INVISIBLE))
                        .addOnFailureListener(e -> mostraToast( "Erro no liberar o aluno!"));
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        Integer orderEtapa;

        try{
            for (ClsEtapaAluno etapa : getEtapas(FonteDados.getAluno(idAluno).getIdTurmaAtual())) {
                try { orderEtapa = etapa.getIdEtapa(); } catch (NullPointerException ex) { orderEtapa = -1; }
                switch (orderEtapa){
                    case 1:
                    case 2: break;
                    case 3: try { btEtapa1EstadoAluno.setVisibility(etapaAceite(etapa.getStatus())); } catch (NullPointerException ex) { } break;
                    case 4: try { btEtapa2EstadoAluno.setVisibility(etapaAceite(etapa.getStatus())); } catch (NullPointerException ex) { } break;
                    case 5: try { btEtapa3EstadoAluno.setVisibility(etapaAceite(etapa.getStatus())); } catch (NullPointerException ex) { } break;
                    case 6: try { btFinalEstadoAluno.setVisibility(etapaAceite(etapa.getStatus())); } catch (NullPointerException ex) { } break;
                }
            }
        }catch (Exception ex){
            btEtapa1EstadoAluno.setVisibility(Button.INVISIBLE);
            btEtapa2EstadoAluno.setVisibility(Button.INVISIBLE);
            btEtapa3EstadoAluno.setVisibility(Button.INVISIBLE);
            btFinalEstadoAluno.setVisibility(Button.INVISIBLE);
        }
    }

    public void onClickPendencia(View view){
        Intent intent = new Intent(view.getContext(), AceiteEtapaActivity.class);

        Boolean lastEtapa = false;

        switch(view.getId())
        {
            case R.id.btEtapa1EstadoAluno:
                intent.putExtra("tvTitle", cbEtapa1EstadoAluno.getText());
                intent.putExtra("idEtapaAluno", cbEtapa1EstadoAluno.getTag().toString());
                break;
            case R.id.btEtapa2EstadoAluno:
                intent.putExtra("tvTitle", cbEtapa2EstadoAluno.getText());
                intent.putExtra("idEtapaAluno", cbEtapa2EstadoAluno.getTag().toString());
                break;
            case R.id.btEtapa3EstadoAluno:
                intent.putExtra("tvTitle", cbEtapa3EstadoAluno.getText());
                intent.putExtra("idEtapaAluno", cbEtapa3EstadoAluno.getTag().toString());
                break;
            case R.id.btFinalEstadoAluno:
                lastEtapa = true;
                intent.putExtra("tvTitle", cbFinalEstadoAluno.getText());
                intent.putExtra("idEtapaAluno", cbFinalEstadoAluno.getTag().toString());
                break;
        }

        Log.i("Eliseo_EstadoAlunoActivity_onClickPendencia_idAluno", idAluno);

        intent.putExtra("idAluno", idAluno);
        intent.putExtra("tvSubTitle", FonteDados.getAluno(idAluno).getNomeAluno() + " " + FonteDados.getAluno(idAluno).getSobrenome());
        intent.putExtra("lastEtapa", lastEtapa);

        view.getContext().startActivity(intent);
    }

    private List<ClsEtapaAluno> getEtapas(String idTurmaAtual){
        List<ClsEtapaAluno> list = new ArrayList<>();

        for (ClsEtapaAluno obj : FonteDados.getTodasAsEtapasDosAluno_list()) {
            if(obj.getIdTurma().equals(idTurmaAtual) && obj.getIdAluno().equals(idAluno)) list.add(obj);
        }

        return list;
    }

    private Boolean etapaConcluida(Integer status){
        Boolean res = false;
        switch (status){
            case 0:
                break;
            default:
                res = true;
                break;
        }
        return res;
    }

    private Integer etapaAceite(Integer status){
        Integer res = Button.INVISIBLE;
        switch (status){
            case 1:
                res = Button.VISIBLE;
                break;
        }
        return res;
    }

    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    public void onClickIbBack(View view){ finish(); }
}