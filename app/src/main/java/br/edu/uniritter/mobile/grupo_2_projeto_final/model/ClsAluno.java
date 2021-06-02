package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class ClsAluno {
    @DocumentId
    private String id;

    private String nomeAluno;
    private String sobrenome;
    private Timestamp dataNasc;
    private String cidade;
    private String UF;
    private String email;
    private int tentativasAcesso;
    private String nomeEmpresa;
    private Timestamp inicioEstagio;
    private Timestamp fimEstagio;
    private String senha;

    public ClsAluno() {
        super();
    }

    public ClsAluno(String id, String nomeAluno, String sobrenome, Timestamp dataNasc, String cidade, String UF, String email, int tentativasAcesso, String nomeEmpresa, Timestamp inicioEstagio,
                    Timestamp fimEstagio, String senha) {
        this.id = id;
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
    }

    public String getId() { return id; }
    public String getNomeAluno() { return nomeAluno; }
    public String getSobrenome() { return sobrenome; }
    public Timestamp getDataNasc() { return dataNasc; }
    public String getCidade() { return cidade; }
    public String getUF() { return UF; }
    public String getEmail() { return email; }
    public int getTentativasAcesso() { return tentativasAcesso; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public Timestamp getInicioEstagio() { return inicioEstagio; }
    public Timestamp getFimEstagio() { return fimEstagio; }
    public String getSenha() { return senha; }

    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setDataNasc(Timestamp idade) { this.dataNasc = dataNasc; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setUF(String UF) { this.UF = UF; }
    public void setEmail(String email) { this.email = email; }
    public void setTentativasAcesso(int tentativasAcesso) { this.tentativasAcesso = tentativasAcesso; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public void setInicioEstagio(Timestamp inicioEstagio) { this.inicioEstagio = inicioEstagio; }
    public void setFimEstagio(Timestamp fimEstagio) { this.fimEstagio = fimEstagio; }
    public void setSenha(String senha) { this.senha = senha; }
}
