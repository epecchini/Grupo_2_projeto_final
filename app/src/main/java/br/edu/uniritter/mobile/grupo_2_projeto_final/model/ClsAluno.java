package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class ClsAluno {
    @DocumentId
    private String id;

    private String idTurmaAtual;
    private String nomeAluno;
    private String sobrenome;
    private String dataNasc;
    private String cidade;
    private String UF;
    private String email;
    private int tentativasAcesso;
    private String nomeEmpresa;
    private String inicioEstagio;
    private String fimEstagio;
    private String senha;
    private Boolean eProf;

    public ClsAluno() {
        super();
    }

    public ClsAluno(String id, String idTurmaAtual, String nomeAluno, String sobrenome, String dataNasc, String cidade, String UF, String email, int tentativasAcesso, String nomeEmpresa,
                    String inicioEstagio, String fimEstagio, String senha, Boolean eProf) {
        this.id = id;
        this.idTurmaAtual = idTurmaAtual;
        this.nomeAluno = nomeAluno;
        this.sobrenome = sobrenome;
        this.dataNasc = dataNasc;
        this.cidade = cidade;
        this.UF = UF;
        this.email = email;
        this.tentativasAcesso = tentativasAcesso;
        this.nomeEmpresa = nomeEmpresa;
        this.inicioEstagio = inicioEstagio;
        this.fimEstagio = fimEstagio;
        this.senha = senha;
        this.eProf = eProf;
    }

    public String getId() { return id; }
    public String getIdTurmaAtual() { return idTurmaAtual; }
    public String getNomeAluno() { return nomeAluno; }
    public String getSobrenome() { return sobrenome; }
    public String getDataNasc() { return dataNasc; }
    public String getCidade() { return cidade; }
    public String getUF() { return UF; }
    public String getEmail() { return email; }
    public int getTentativasAcesso() { return tentativasAcesso; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public String getInicioEstagio() { return inicioEstagio; }
    public String getFimEstagio() { return fimEstagio; }
    public String getSenha() { return senha; }
    public Boolean getEprof() { return eProf; }

    public void setIdTurmaAtual(String idTurmaAtual) { this.idTurmaAtual = idTurmaAtual; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setDataNasc(String idade) { this.dataNasc = dataNasc; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setUF(String UF) { this.UF = UF; }
    public void setEmail(String email) { this.email = email; }
    public void setTentativasAcesso(int tentativasAcesso) { this.tentativasAcesso = tentativasAcesso; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public void setInicioEstagio(String inicioEstagio) { this.inicioEstagio = inicioEstagio; }
    public void setFimEstagio(String fimEstagio) { this.fimEstagio = fimEstagio; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setEprof(Boolean senha) { this.eProf = eProf; }
}
