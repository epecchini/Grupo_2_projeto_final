package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

public class Lembrete implements Parcelable {

    private int id;
    private String texto;
    private int dias;
    private boolean modificavel;

    public Lembrete(int id, String texto, int dias, boolean modificavel) {
        this.id = id;
        this.texto = texto;
        this.dias = dias;
        this.modificavel = modificavel;
    }

    private Lembrete(Parcel p) {
        this.id = p.readInt();
        this.texto = p.readString();
        this.dias = p.readInt();
      //  this.modificavel = p.readBoolean();
    }

    public static final Creator<Lembrete> CREATOR = new Creator<Lembrete>() {
        @Override
        public Lembrete createFromParcel(Parcel in) {
            return new Lembrete(in);
        }

        @Override
        public Lembrete[] newArray(int size) {
            return new Lembrete[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.texto);
        parcel.writeInt(this.dias);
      //  parcel.writeBoolean(this.modificavel);
    }

    public int getId() {
        return id;
    }
    public String getTexto() {
        return texto;
    }
    public int getDias() {
        return dias;
    }
    public boolean getModificavel() { return modificavel; }

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String texto) {
        this.texto = texto;
    }
    public void setDias(int dias) {
        this.dias = dias;
    }
    public void setModificavel(boolean modificavel) {
        this.modificavel = modificavel;
    }
}
