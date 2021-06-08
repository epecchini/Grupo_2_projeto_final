package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class ClsEtapaAluno {
    @DocumentId
    private String id;

    private int idEtapa;
    private String idAluno;
    private String idTurma;
    private String dataEntrega;
    private int status;             // 0 = não iniciada, 1 = aceitada / concluída, 2 = aprovada do prof
    private String dataModificacaoStatus;
    private String ObsProf;
    private int lembreteDias;
    private int enviarLembrete;     // 0 = não enviar, 1 = enviar, 2 = enviado

    public ClsEtapaAluno() {
        super();
    }

    public ClsEtapaAluno(String id, int idEtapa, String idAluno, String idTurma, String dataEntrega, int status, String dataModificacaoStatus, String ObsProf, int lembreteDias, int enviarLembrete) {
        this.id = id;
        this.idEtapa = idEtapa;
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.dataModificacaoStatus = dataModificacaoStatus;
        this.ObsProf = ObsProf;
        this.lembreteDias = lembreteDias;
        this.enviarLembrete = enviarLembrete;
    }

    public String getId() { return id; }
    public int getIdEtapa() {
        return idEtapa;
    }
    public String getIdAluno() { return idAluno; }
    public String getIdTurma() { return idTurma; }
    public String getDataEntrega() {
        return dataEntrega;
    }
    public int getStatus() { return status; }
    public String getDataModificacaoStatus() {
        return dataModificacaoStatus;
    }
    public String getObsProf() { return ObsProf; }
    public int getLembreteDias() {
        return lembreteDias;
    }
    public int getEnviarLembrete() { return enviarLembrete; }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }
    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }
    public void setIdTurma(String idTurma) { this.idTurma = idTurma; }
    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setDataModificacaoStatus(String dataModificacaoStatus) { this.dataModificacaoStatus = dataModificacaoStatus; }
    public void setObsProf(String ObsProf) {
        this.ObsProf = ObsProf;
    }
    public void setLembreteDias(int lembreteDias) {
        this.lembreteDias = lembreteDias;
    }
    public void setEnviarLembrete(int enviarLembrete) {
        this.enviarLembrete = enviarLembrete;
    }
}
