package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.CheckBox;
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

        Integer orderEtapa;

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            try { orderEtapa = obj.getIdEtapa(); } catch (NullPointerException ex) { orderEtapa = -1; }
            switch (orderEtapa){
                case 1:
                    try { cbTermo.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { cbTermo.setText("Erro!");}
                    try { cbTermo.setChecked(etapaConcluida(orderEtapa)); } catch (NullPointerException ex) { }
                    break;
                case 2:
                    try { cbProjeto.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { cbProjeto.setText("Erro!");}
                    try { cbProjeto.setChecked(etapaConcluida(orderEtapa)); } catch (NullPointerException ex) { }
                    break;
                case 3:
                    try { cbEtapa1.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { cbEtapa1.setText("Erro!");}
                    try { cbEtapa1.setChecked(etapaConcluida(orderEtapa)); } catch (NullPointerException ex) { }
                    try { swEtapa1.setChecked(etapaAceite(orderEtapa)); } catch (NullPointerException ex) { }
                    break;
                case 4:
                    try { cbEtapa2.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { cbEtapa2.setText("Erro!");}
                    try { cbEtapa2.setChecked(etapaConcluida(orderEtapa)); } catch (NullPointerException ex) { }
                    try { swEtapa2.setChecked(etapaAceite(orderEtapa)); } catch (NullPointerException ex) { }
                    break;
                case 5:
                    try { cbEtapa3.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { cbEtapa3.setText("Erro!");}
                    try { cbEtapa3.setChecked(etapaConcluida(orderEtapa)); } catch (NullPointerException ex) { }
                    try { swEtapa3.setChecked(etapaAceite(orderEtapa)); } catch (NullPointerException ex) { }
                    break;
                case 6:
                    try { cbFinal.setText(obj.getNomeEtapa()); } catch (NullPointerException ex) { cbFinal.setText("Erro!");}
                    try { cbFinal.setChecked(etapaConcluida(orderEtapa)); } catch (NullPointerException ex) { }
                    try { swFinal.setChecked(etapaAceite(orderEtapa)); } catch (NullPointerException ex) { }
                    break;
            }
        }
    }

    private Boolean etapaConcluida(Integer idEtapa){
        Boolean res = false;
        for (ClsEtapaAluno obj : FonteDados.getEtapaAluno_list()) {
            if(obj.getIdTurma().equals(idTurma) && obj.getIdAluno().equals(FonteDados.iduser) && obj.getIdEtapa() == idEtapa){
                switch (obj.getStatus()){
                    case 0:
                        break;
                    default:
                        res = true;
                        break;
                }
                break;
            }
        }
        return res;
    }

    private Boolean etapaAceite(Integer idEtapa){
        Boolean res = false;
        for (ClsEtapaAluno obj : FonteDados.getEtapaAluno_list()) {
            if(obj.getIdTurma().equals(idTurma) && obj.getIdAluno().equals(FonteDados.iduser) && obj.getIdEtapa() == idEtapa){
                switch (obj.getStatus()){
                    case 0:
                    case 1:
                        break;
                    default:
                        res = true;
                        break;
                }
                break;
            }
        }
        return res;
    }
}