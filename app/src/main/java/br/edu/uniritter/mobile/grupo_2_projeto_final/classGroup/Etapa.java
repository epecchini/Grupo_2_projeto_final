package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Etapa implements Parcelable {

    private int id;
    private int ordem;
    private String dataLimite;
    private int idTurma;
    private String nome;
    private String texto;

    public Etapa(int id, int ordem, String dataLimite, int idTurma, String nome, String texto) {
        this.id = id;
        this.ordem = ordem;
        this.dataLimite = dataLimite;
        this.idTurma = idTurma;
        this.nome = nome;
        this.texto = texto;
    }

    private Etapa(Parcel p) {
        this.id = p.readInt();
        this.ordem = p.readInt();
        this.dataLimite = p.readString();
        this.idTurma = p.readInt();
        this.nome = p.readString();
        this.texto = p.readString();
    }

    public static final Creator<Etapa> CREATOR = new Creator<Etapa>() {
        @Override
        public Etapa createFromParcel(Parcel in) {
            return new Etapa(in);
        }

        @Override
        public Etapa[] newArray(int size) {
            return new Etapa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.ordem);
        parcel.writeString(this.dataLimite);
        parcel.writeInt(this.idTurma);
        parcel.writeString(this.nome);
        parcel.writeString(this.texto);
    }

    public int getId() {
        return id;
    }
    public int getOrdem() {
        return ordem;
    }
    public String getDataLimite() {
        return dataLimite;
    }
    public int getIdTurma() {
        return idTurma;
    }
    public String getNome() { return nome; }
    public String getTexto() {
        return texto;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }
    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
