package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

public class Turma implements Parcelable {

    private int id;
    private String nome;
    private boolean liberada;
    private boolean encerrada;

    public Turma(int id, String nome, boolean liberada, boolean encerrada) {
        this.id = id;
        this.nome = nome;
        this.liberada = liberada;
        this.encerrada = encerrada;
    }

    private Turma(Parcel p) {
        this.id = p.readInt();
        this.nome = p.readString();
        this.liberada = p.readBoolean();
        this.encerrada = p.readBoolean();
    }

    public static final Creator<Turma> CREATOR = new Creator<Turma>() {
        @Override
        public Turma createFromParcel(Parcel in) {
            return new Turma(in);
        }

        @Override
        public Turma[] newArray(int size) {
            return new Turma[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.nome);
        parcel.writeBoolean(this.liberada);
        parcel.writeBoolean(this.encerrada);
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public boolean getLiberada() { return liberada; }
    public boolean getEncerrada() { return encerrada; }

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setLiberada(boolean liberada) {
        this.liberada = liberada;
    }
    public void setEncerrada(boolean encerrada) {
        this.liberada = encerrada;
    }
}
