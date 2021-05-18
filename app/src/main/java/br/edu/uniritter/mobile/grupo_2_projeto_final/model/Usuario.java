package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import android.widget.CheckBox;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private CheckBox cbProfessor;

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
        //Aqui será usado para salvar os daddos no DataBase
    }
}
