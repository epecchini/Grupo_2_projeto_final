package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

public class ClsAlunoAuth {
    private String id;
    private String email;
    private String senha;

    public ClsAlunoAuth() { };

    public ClsAlunoAuth(String id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
}
