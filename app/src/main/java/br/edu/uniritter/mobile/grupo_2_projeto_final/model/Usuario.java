package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.database.DatabaseReference;

import br.edu.uniritter.mobile.grupo_2_projeto_final.services.ConfiguracaoFirebaseDB;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private boolean professor;
    private String turma;
    private String etapa;

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public boolean isProfessor() {return professor;}

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    public void salvarDados() {
        //Aqui será usado para salvar os dados no DataBase

        DatabaseReference firebase = ConfiguracaoFirebaseDB.getFirebaseDatabase();
        firebase.child("Usuarios").child(this.id).setValue(this);

    }
}
