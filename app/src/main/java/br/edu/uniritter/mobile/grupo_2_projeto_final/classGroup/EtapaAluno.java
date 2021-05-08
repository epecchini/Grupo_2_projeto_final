package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

public class EtapaAluno implements Parcelable {

    private int id;
    private int idEtapa;
    private int idAluno;
    private boolean liberada;
    private boolean entregada;
    private String dataEntrega;
    private int status;
    private String dataModificacaoStatus;
    private String ObsProf;
    private int lembreteDias;
    private int enviraLembrete;     // 0 = não enviar, 1 = enviar, 2 = enviado

    public EtapaAluno(int id, int idEtapa, int idAluno, boolean liberada, boolean entregada, String dataEntrega, int status, String dataModificacaoStatus, String ObsProf, int lembreteDias,
                      int enviraLembrete) {
        this.id = id;
        this.idEtapa = idEtapa;
        this.idAluno = idAluno;
        this.liberada = liberada;
        this.entregada = entregada;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.dataModificacaoStatus = dataModificacaoStatus;
        this.ObsProf = ObsProf;
        this.lembreteDias = lembreteDias;
        this.enviraLembrete = enviraLembrete;
    }

    private EtapaAluno(Parcel p) {
        this.id = p.readInt();
        this.idEtapa = p.readInt();
        this.idAluno = p.readInt();
        this.liberada = p.readBoolean();
        this.entregada = p.readBoolean();
        this.dataEntrega = p.readString();
        this.status = p.readInt();
        this.dataModificacaoStatus = p.readString();
        this.ObsProf = p.readString();
        this.lembreteDias = p.readInt();
        this.enviraLembrete = p.readInt();
    }

    public static final Creator<EtapaAluno> CREATOR = new Creator<EtapaAluno>() {
        @Override
        public EtapaAluno createFromParcel(Parcel in) {
            return new EtapaAluno(in);
        }

        @Override
        public EtapaAluno[] newArray(int size) {
            return new EtapaAluno[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.idEtapa);
        parcel.writeInt(this.idAluno);
        parcel.writeBoolean(this.liberada);
        parcel.writeBoolean(this.entregada);
        parcel.writeString(this.dataEntrega);
        parcel.writeInt(this.status);
        parcel.writeString(this.dataModificacaoStatus);
        parcel.writeString(this.ObsProf);
        parcel.writeInt(this.lembreteDias);
        parcel.writeInt(this.enviraLembrete);
    }

    public int getId() {
        return id;
    }
    public int getIdEtapa() {
        return idEtapa;
    }
    public int getIdAluno() { return idAluno; }
    public boolean getLiberada() {
        return liberada;
    }
    public boolean getEntregada() { return entregada; }
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
    public int getEnviraLembrete() { return enviraLembrete; }

    public void setId(int id) {
        this.id = id;
    }
    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
    public void setLiberada(boolean liberada) {
        this.liberada = liberada;
    }
    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }
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
    public void setEnviraLembrete(int enviraLembrete) {
        this.enviraLembrete = enviraLembrete;
    }
}
