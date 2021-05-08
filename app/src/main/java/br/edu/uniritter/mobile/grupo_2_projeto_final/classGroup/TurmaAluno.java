package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

public class TurmaAluno implements Parcelable {

    private int id;
    private int idTurma;
    private int idAluno;

    public TurmaAluno(int id, int idTurma, int idAluno) {
        this.id = id;
        this.idTurma = idTurma;
        this.idAluno = idAluno;
    }

    private TurmaAluno(Parcel p) {
        this.id = p.readInt();
        this.idTurma = p.readInt();
        this.idAluno = p.readInt();
    }

    public static final Creator<TurmaAluno> CREATOR = new Creator<TurmaAluno>() {
        @Override
        public TurmaAluno createFromParcel(Parcel in) {
            return new TurmaAluno(in);
        }

        @Override
        public TurmaAluno[] newArray(int size) {
            return new TurmaAluno[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.idTurma);
        parcel.writeInt(this.idAluno);
    }

    public int getId() {
        return id;
    }
    public int getSobrenome() { return idTurma; }
    public int getIdade() {
        return idAluno;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
}
