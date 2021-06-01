package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

public class ClsTurmaAluno {

    private String id;
    private String idTurma;
    private String idAluno;
    private String nomeTurma;
    private boolean cadastrado;

    public ClsTurmaAluno(String id, String idTurma, String idAluno) {
        this.id = id;
        this.idTurma = idTurma;
        this.idAluno = idAluno;
    }

    public ClsTurmaAluno(String id, String idTurma, String idAluno, String nomeTurma, boolean cadastrado) {
        this.id = id;
        this.idTurma = idTurma;
        this.idAluno = idAluno;
        this.nomeTurma = nomeTurma;
        this.cadastrado = cadastrado;
    }

    public String getId() {
        return id;
    }
    public String getSobrenome() {
        return idTurma;
    }
    public String getIdade() {
        return idAluno;
    }
    public String getNomeTurma() {
        return nomeTurma;
    }
    public boolean getCadastrado() {
        return cadastrado;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setIdTurma(String idTurma) {
        this.idTurma = idTurma;
    }
    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }
    public void setNomeTurma(String nomeTurma) {
        this.idAluno = nomeTurma;
    }
    public void setCadastrado(boolean cadastrado) {
        this.cadastrado = cadastrado;
    }
}
