package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

import java.util.List;

public class ClsTurma {
    @DocumentId
    private String id;

    private String nomeTurma;
    private boolean liberada;
    private boolean encerrada;
    @Exclude
    private boolean cadastrado = false;
    private List<ClsEtapa> etapa;

    public ClsTurma() {
        super();
    }

    public ClsTurma(String id, String nomeTurma, boolean liberada, boolean encerrada, List<ClsEtapa> etapa) {
        this.id = id;
        this.nomeTurma = nomeTurma;
        this.liberada = liberada;
        this.encerrada = encerrada;
        this.etapa = etapa;
    }

    public String getId() {
        return id;
    }
    public boolean isCadastrado() { return cadastrado; }
    public String getNomeTurma() {
        return nomeTurma;
    }
    public boolean isLiberada() {
        return liberada;
    }
    public boolean isEncerrada() {
        return encerrada;
    }
    public List<ClsEtapa> getEtapa() { return etapa; }

    public void setCadastrado(boolean cadastrado) { this.cadastrado = cadastrado; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }
    public void setLiberada(boolean liberada) {
        this.liberada = liberada;
    }
    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }
    public void setEtapa(List<ClsEtapa> etapa) { this.etapa = etapa; }
}
