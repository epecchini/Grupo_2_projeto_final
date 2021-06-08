package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.Exclude;

public class ClsAluno {
    @DocumentId
    private String id;

    private String idRealtime;
    private String idTurmaAtual;
    private String nomeAluno;
    private String sobrenome;
    private String dataNasc;
    private String cidade;
    private String UF;
    private int tentativasAcesso;
    private String nomeEmpresa;
    private String inicioEstagio;
    private String fimEstagio;
    private Boolean eProf;

    public ClsAluno() {
        super();
    }

    public ClsAluno(String id, String idRealtime, String idTurmaAtual, String nomeAluno, String sobrenome, String dataNasc, String cidade, String UF, int tentativasAcesso, String nomeEmpresa,
                    String inicioEstagio, String fimEstagio, Boolean eProf) {
        this.id = id;
        this.idRealtime = idRealtime;
        this.idTurmaAtual = idTurmaAtual;
        this.nomeAluno = nomeAluno;
        this.sobrenome = sobrenome;
        this.dataNasc = dataNasc;
        this.cidade = cidade;
        this.UF = UF;
        this.tentativasAcesso = tentativasAcesso;
        this.nomeEmpresa = nomeEmpresa;
        this.inicioEstagio = inicioEstagio;
        this.fimEstagio = fimEstagio;
        this.eProf = eProf;
    }

    public String getId() { return id; }
    public String getIdRealtime() { return idRealtime; }
    public String getIdTurmaAtual() { return idTurmaAtual; }
    public String getNomeAluno() { return nomeAluno; }
    public String getSobrenome() { return sobrenome; }
    public String getDataNasc() { return dataNasc; }
    public String getCidade() { return cidade; }
    public String getUF() { return UF; }
    public int getTentativasAcesso() { return tentativasAcesso; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public String getInicioEstagio() { return inicioEstagio; }
    public String getFimEstagio() { return fimEstagio; }
    public Boolean getEprof() { return eProf; }

    public void setId(String id) { this.id = id; }
    public void setIdRealtime(String idRealtime) { this.idRealtime = idRealtime; }
    public void setIdTurmaAtual(String idTurmaAtual) { this.idTurmaAtual = idTurmaAtual; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public void setDataNasc(String idade) { this.dataNasc = dataNasc; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setUF(String UF) { this.UF = UF; }
    public void setTentativasAcesso(int tentativasAcesso) { this.tentativasAcesso = tentativasAcesso; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public void setInicioEstagio(String inicioEstagio) { this.inicioEstagio = inicioEstagio; }
    public void setFimEstagio(String fimEstagio) { this.fimEstagio = fimEstagio; }
    public void setEprof(Boolean eProf) { this.eProf = eProf; }
}
