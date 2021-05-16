package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

public class Pendencias implements Parcelable {

    private int id;
    private String nomeEtapa;
    private String nomeAluno;

    public Pendencias(int id, String nomeEtapa, String nomeAluno) {
        this.id = id;
        this.nomeEtapa = nomeEtapa;
        this.nomeAluno = nomeAluno;
    }

    private Pendencias(Parcel p) {
        this.id = p.readInt();
        this.nomeEtapa = p.readString();
        this.nomeAluno = p.readString();
    }

    public static final Creator<Pendencias> CREATOR = new Creator<Pendencias>() {
        @Override
        public Pendencias createFromParcel(Parcel in) {
            return new Pendencias(in);
        }

        @Override
        public Pendencias[] newArray(int size) {
            return new Pendencias[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.nomeEtapa);
        parcel.writeString(this.nomeAluno);
    }

    public int getId() {
        return id;
    }
    public String getNomeEtapa() {
        return nomeEtapa;
    }
    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNomeEtapa(String nomeEtapa) {
        this.nomeEtapa = nomeEtapa;
    }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }
}
