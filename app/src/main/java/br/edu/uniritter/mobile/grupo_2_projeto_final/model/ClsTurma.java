package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.firestore.DocumentId;

public class ClsTurma {
    @DocumentId
    private String id;

    private String nomeTurma;
    private boolean liberada;
    private boolean encerrada;
    private boolean cadastrado = false;

    public ClsTurma() {
        super();
    }

    public ClsTurma(String id, String nomeTurma, boolean liberada, boolean encerrada) {
        this.id = id;
        this.nomeTurma = nomeTurma;
        this.liberada = liberada;
        this.encerrada = encerrada;
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

    public void setCadastrado(boolean cadastrado) { this.cadastrado = cadastrado; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }
    public void setLiberada(boolean liberada) {
        this.liberada = liberada;
    }
    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }
}
