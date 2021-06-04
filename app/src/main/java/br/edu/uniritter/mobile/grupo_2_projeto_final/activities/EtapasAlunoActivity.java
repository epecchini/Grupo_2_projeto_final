package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapa;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

public class EtapasAlunoActivity extends AppCompatActivity {

    private String idTurma;

    private CheckBox cbTermo;
    private CheckBox cbProjeto;
    private CheckBox cbEtapa1;
    private CheckBox cbEtapa2;
    private CheckBox cbEtapa3;
    private CheckBox cbFinal;

    private Switch swEtapa1;
    private Switch swEtapa2;
    private Switch swEtapa3;
    private Switch swFinal;

    private TextView tvTermo;
    private TextView tvProjeto;
    private TextView tvEtapa1;
    private TextView tvEtapa2;
    private TextView tvEtapa3;
    private TextView tvFinal;
    private TextView tvInfo;

    private ImageButton btAceiteEtapa1;
    private ImageButton btAceiteEtapa2;
    private ImageButton btAceiteEtapa3;
    private ImageButton btAceiteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapas_aluno);

        Intent it = getIntent();
        idTurma = it.getStringExtra("idTurma");

        cbTermo = (CheckBox) findViewById(R.id.cbTermo);
        cbProjeto = (CheckBox) findViewById(R.id.cbProjeto);
        cbEtapa1 = (CheckBox) findViewById(R.id.cbEtapa1);
        cbEtapa2 = (CheckBox) findViewById(R.id.cbEtapa2);
        cbEtapa3 = (CheckBox) findViewById(R.id.cbEtapa3);
        cbFinal = (CheckBox) findViewById(R.id.cbFinal);

        swEtapa1 = (Switch) findViewById(R.id.swEtapa1);
        swEtapa2 = (Switch) findViewById(R.id.swEtapa2);
        swEtapa3 = (Switch) findViewById(R.id.swEtapa3);
        swFinal = (Switch) findViewById(R.id.swFinal);

        tvTermo = (TextView) findViewById(R.id.tvTermo);
        tvProjeto = (TextView) findViewById(R.id.tvProjeto);
        tvEtapa1 = (TextView) findViewById(R.id.tvEtapa1);
        tvEtapa2 = (TextView) findViewById(R.id.tvEtapa2);
        tvEtapa3 = (TextView) findViewById(R.id.tvEtapa3);
        tvFinal = (TextView) findViewById(R.id.tvFinal);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        btAceiteEtapa1 = (ImageButton) findViewById(R.id.btAceiteEtapa1);
        btAceiteEtapa2 = (ImageButton) findViewById(R.id.btAceiteEtapa2);
        btAceiteEtapa3 = (ImageButton) findViewById(R.id.btAceiteEtapa3);
        btAceiteFinal = (ImageButton) findViewById(R.id.btAceiteFinal);

        getGeralValues();
    }

    @Override
    public void onResume(){
        super.onResume();
        getGeralValues();
    }

    private void getGeralValues(){
        Integer orderEtapa;

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            try { orderEtapa = obj.getIdEtapa(); } catch (NullPointerException ex) { orderEtapa = -1; }
            switch (orderEtapa){
                case 1:
                    tvTermo.setTag(FonteDados.getIdEtapaAluno(idTurma, orderEtapa));
                    try { tvTermo.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvTermo.setText("Erro!");}
                    try { cbTermo.setChecked(etapaConcluida(tvTermo.getTag().toString())); } catch (NullPointerException ex) { }
                    break;
                case 2:
                    tvProjeto.setTag(FonteDados.getIdEtapaAluno(idTurma, orderEtapa));
                    try { tvProjeto.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvProjeto.setText("Erro!");}
                    try { cbProjeto.setChecked(etapaConcluida(tvProjeto.getTag().toString())); } catch (NullPointerException ex) { }
                    break;
                case 3:
                    tvEtapa1.setTag(FonteDados.getIdEtapaAluno(idTurma, orderEtapa));
                    try { tvEtapa1.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvEtapa1.setText("Erro!");}
                    try { cbEtapa1.setChecked(etapaConcluida(tvEtapa1.getTag().toString())); } catch (NullPointerException ex) { }
                    try { swEtapa1.setChecked(etapaAceite(tvEtapa1.getTag().toString())); } catch (NullPointerException ex) { }
                    break;
                case 4:
                    tvEtapa2.setTag(FonteDados.getIdEtapaAluno(idTurma, orderEtapa));
                    try { tvEtapa2.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvEtapa2.setText("Erro!");}
                    try { cbEtapa2.setChecked(etapaConcluida(tvEtapa2.getTag().toString())); } catch (NullPointerException ex) { }
                    try { swEtapa2.setChecked(etapaAceite(tvEtapa2.getTag().toString())); } catch (NullPointerException ex) { }
                    break;
                case 5:
                    tvEtapa3.setTag(FonteDados.getIdEtapaAluno(idTurma, orderEtapa));
                    try { tvEtapa3.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvEtapa3.setText("Erro!");}
                    try { cbEtapa3.setChecked(etapaConcluida(tvEtapa3.getTag().toString())); } catch (NullPointerException ex) { }
                    try { swEtapa3.setChecked(etapaAceite(tvEtapa3.getTag().toString())); } catch (NullPointerException ex) { }
                    break;
                case 6:
                    tvFinal.setTag(FonteDados.getIdEtapaAluno(idTurma, orderEtapa));
                    try { tvFinal.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { tvFinal.setText("Erro!");}
                    try { cbFinal.setChecked(etapaConcluida(tvFinal.getTag().toString())); } catch (NullPointerException ex) { }
                    try { swFinal.setChecked(etapaAceite(tvFinal.getTag().toString())); } catch (NullPointerException ex) { }
                    break;
            }
        }

        String obs = getObsProf(tvEtapa1.getTag().toString());
        if (obs == "" || TextUtils.isEmpty(obs)) btAceiteEtapa1.setVisibility(Button.INVISIBLE);
        else btAceiteEtapa1.setVisibility(Button.VISIBLE);

        obs = getObsProf(tvEtapa2.getTag().toString());
        if (obs == "" || TextUtils.isEmpty(obs)) btAceiteEtapa2.setVisibility(Button.INVISIBLE);
        else btAceiteEtapa2.setVisibility(Button.VISIBLE);

        obs = getObsProf(tvEtapa3.getTag().toString());
        if (obs == "" || TextUtils.isEmpty(obs)) btAceiteEtapa3.setVisibility(Button.INVISIBLE);
        else btAceiteEtapa3.setVisibility(Button.VISIBLE);

        obs = getObsProf(tvFinal.getTag().toString());
        if (obs == "" || TextUtils.isEmpty(obs)) btAceiteFinal.setVisibility(Button.INVISIBLE);
        else btAceiteFinal.setVisibility(Button.VISIBLE);
    }

    public void onClickObsProf(View view){
        switch(view.getId())
        {
            case R.id.btAceiteEtapa1:
                tvInfo.setText(getObsProf(tvEtapa1.getTag().toString()));
                break;
            case R.id.btAceiteEtapa2:
                tvInfo.setText(getObsProf(tvEtapa2.getTag().toString()));
                break;
            case R.id.btAceiteEtapa3:
                tvInfo.setText(getObsProf(tvEtapa3.getTag().toString()));
                break;
            case R.id.btAceiteFinal:
                tvInfo.setText(getObsProf(tvFinal.getTag().toString()));
                break;
        }
    }

    public void onClickEtapaTitle(View view){
        Intent intent = new Intent(view.getContext(), EtapasActivity.class);

        Integer et = 0;
        Integer vis = Button.VISIBLE;

        switch(view.getId())
        {
            case R.id.tvTermo:
                et = 1;
                if(cbTermo.isChecked()) intent.putExtra("showBtAceite", Button.GONE);
                else intent.putExtra("showBtAceite", Button.VISIBLE);
                intent.putExtra("tvTitle", "Termo");
                intent.putExtra("tvSubTitle", tvTermo.getText());
                intent.putExtra("btAceitarEtapa", "Aceitar termos do projeto");
                intent.putExtra("idEtapaAluno", tvTermo.getTag().toString());
                intent.putExtra("showLembrete", Button.GONE);
                intent.putExtra("etnLembreteValue", "0");
                intent.putExtra("swLembrete", false);
                tvInfo.setText("");
                break;
            case R.id.tvProjeto:
                et = 2;
                if(cbProjeto.isChecked()) intent.putExtra("showBtAceite", Button.GONE);
                else intent.putExtra("showBtAceite", Button.VISIBLE);
                intent.putExtra("tvTitle", "Projeto");
                intent.putExtra("tvSubTitle", tvProjeto.getText());
                intent.putExtra("btAceitarEtapa", "Aceitar o projeto");
                intent.putExtra("idEtapaAluno", tvProjeto.getTag().toString());
                intent.putExtra("showLembrete", Button.GONE);
                intent.putExtra("etnLembreteValue", "0");
                intent.putExtra("swLembrete", false);
                tvInfo.setText("");
                break;
            case R.id.tvEtapa1:
                et = 3;
                vis = liberaAceite(et);
                if(cbEtapa1.isChecked()) intent.putExtra("showBtAceite", Button.GONE);
                else intent.putExtra("showBtAceite", vis);
                intent.putExtra("tvTitle", "Etapa 1");
                intent.putExtra("tvSubTitle", tvEtapa1.getText());
                intent.putExtra("btAceitarEtapa", "Etapa 1 concluída");
                intent.putExtra("idEtapaAluno", tvEtapa1.getTag().toString());
                intent.putExtra("showLembrete", Button.VISIBLE);
                intent.putExtra("etnLembreteValue", lembreteDias(tvEtapa1.getTag().toString()));
                intent.putExtra("swLembrete", enviarLembrete(tvEtapa1.getTag().toString()));
                break;
            case R.id.tvEtapa2:
                et = 4;
                vis = liberaAceite(et);
                if(cbEtapa2.isChecked()) intent.putExtra("showBtAceite", Button.GONE);
                else intent.putExtra("showBtAceite", vis);
                intent.putExtra("tvTitle", "Etapa 2");
                intent.putExtra("tvSubTitle", tvEtapa2.getText());
                intent.putExtra("btAceitarEtapa", "Etapa 2 concluída");
                intent.putExtra("idEtapaAluno", tvEtapa2.getTag().toString());
                intent.putExtra("showLembrete", Button.VISIBLE);
                intent.putExtra("etnLembreteValue", lembreteDias(tvEtapa2.getTag().toString()));
                intent.putExtra("swLembrete", enviarLembrete(tvEtapa2.getTag().toString()));
                break;
            case R.id.tvEtapa3:
                et= 5;
                vis = liberaAceite(et);
                if(cbEtapa3.isChecked()) intent.putExtra("showBtAceite", Button.GONE);
                else intent.putExtra("showBtAceite", vis);
                intent.putExtra("tvTitle", "Etapa 3");
                intent.putExtra("tvSubTitle", tvEtapa3.getText());
                intent.putExtra("btAceitarEtapa", "Etapa 3 concluída");
                intent.putExtra("idEtapaAluno", tvEtapa3.getTag().toString());
                intent.putExtra("showLembrete", Button.VISIBLE);
                intent.putExtra("etnLembreteValue", lembreteDias(tvEtapa3.getTag().toString()));
                intent.putExtra("swLembrete", enviarLembrete(tvEtapa3.getTag().toString()));
                break;
            case R.id.tvFinal:
                et = 6;
                vis = liberaAceite(et);
                if(cbFinal.isChecked()) intent.putExtra("showBtAceite", Button.GONE);
                else intent.putExtra("showBtAceite", vis);
                intent.putExtra("tvTitle", "Etapa final");
                intent.putExtra("tvSubTitle", tvFinal.getText());
                intent.putExtra("btAceitarEtapa", "Etapa final concluída");
                intent.putExtra("idEtapaAluno", tvFinal.getTag().toString());
                intent.putExtra("showLembrete", Button.VISIBLE);
                intent.putExtra("etnLembreteValue", lembreteDias(tvFinal.getTag().toString()));
                intent.putExtra("swLembrete", enviarLembrete(tvFinal.getTag().toString()));
                break;
        }

        if (vis == Button.GONE) return;

        intent.putExtra("idTurma", idTurma);
        intent.putExtra("idEtapa", et);
        intent.putExtra("tvTextoEtapa", getTextoEtapa(et));

        view.getContext().startActivity(intent);
    }

    private int liberaAceite(Integer idEtapa){
        Integer res = Button.GONE;

        switch (idEtapa){
            case 3:
                if(cbTermo.isChecked() && cbProjeto.isChecked()) res = Button.VISIBLE;
                break;
            case 4:
                if(cbTermo.isChecked() && cbProjeto.isChecked() && cbEtapa1.isChecked()) res = Button.VISIBLE;
                break;
            case 5:
                if(cbTermo.isChecked() && cbProjeto.isChecked() && cbEtapa1.isChecked() && cbEtapa2.isChecked()) res = Button.VISIBLE;
                break;
            case 6:
                if(cbTermo.isChecked() && cbProjeto.isChecked() && cbEtapa1.isChecked() && cbEtapa2.isChecked() && cbEtapa3.isChecked()) res = Button.VISIBLE;
                break;
        }

        if(res == Button.GONE) tvInfo.setText("Você precisa aceitar ou terminar as etapas anteriores antes de poder fazer essa etata");
        else tvInfo.setText("");

        return res;
    }

    private String getTextoEtapa(Integer idEtapa){
        String textoEtapa = "Texto não encontrado!";

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            if(obj.getIdEtapa() == idEtapa){
                textoEtapa = obj.getTextoEtapa();
                break;
            }
        }

        return textoEtapa;
    }

    private Boolean etapaConcluida(String idEtapaAluno){
        Boolean res = false;
        ClsEtapaAluno obj = FonteDados.getEtapaAluno(idEtapaAluno);
        switch (obj.getStatus()){
            case 0:
                break;
            default:
                res = true;
                break;
        }
        return res;
    }

    private Boolean etapaAceite(String idEtapaAluno){
        Boolean res = false;
        ClsEtapaAluno obj = FonteDados.getEtapaAluno(idEtapaAluno);
        switch (obj.getStatus()){
            case 0:
            case 1:
                break;
            default:
                res = true;
                break;
        }
        return res;
    }

    private String lembreteDias(String idEtapaAluno){
        String res = "0";
        ClsEtapaAluno obj = FonteDados.getEtapaAluno(idEtapaAluno);
        try { res = String.valueOf(obj.getLembreteDias()); } catch (Exception ex) { }
        return res;
    }

    private Boolean enviarLembrete(String idEtapaAluno){
        Boolean res = false;
        ClsEtapaAluno obj = FonteDados.getEtapaAluno(idEtapaAluno);
        try { res = obj.getEnviarLembrete() == 1; } catch (Exception ex) { }
        return res;
    }

    private String getObsProf(String idEtapaAluno){
        String res = "";
        ClsEtapaAluno obj = FonteDados.getEtapaAluno(idEtapaAluno);
        try { res = obj.getObsProf(); } catch (Exception ex) { }
        return res;
    }

    public void onClickIbBack(View view){
        finish();
    }
}