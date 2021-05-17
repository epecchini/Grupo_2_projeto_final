package br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {

    private int id;
    private String nome;
    private String sobrenome;
    private int idade;
    private String cidade;
    private String UF;
    private String email;
    private int tentativasAcesso;
    private String nomeEmpresa;
    private String inicioEstagio;
    private String fimEstagio;

    public Aluno(int id, String nome, String sobrenome, int idade, String cidade, String UF, String email, int tentativasAcesso, String nomeEmpresa, String inicioEstagio, String fimEstagio) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.cidade = cidade;
        this.UF = UF;
        this.email = email;
        this.tentativasAcesso = tentativasAcesso;
        this.nomeEmpresa = nomeEmpresa;
        this.inicioEstagio = inicioEstagio;
        this.fimEstagio = fimEstagio;
    }

    private Aluno(Parcel p) {
        this.id = p.readInt();
        this.nome = p.readString();
        this.sobrenome = p.readString();
        this.idade = p.readInt();
        this.cidade = p.readString();
        this.UF = p.readString();
        this.email = p.readString();
        this.tentativasAcesso = p.readInt();
        this.nomeEmpresa = p.readString();
        this.inicioEstagio = p.readString();
        this.fimEstagio = p.readString();
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
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
        parcel.writeString(this.sobrenome);
        parcel.writeInt(this.idade);
        parcel.writeString(this.cidade);
        parcel.writeString(this.UF);
        parcel.writeString(this.email);
        parcel.writeInt(this.tentativasAcesso);
        parcel.writeString(this.nomeEmpresa);
        parcel.writeString(this.inicioEstagio);
        parcel.writeString(this.fimEstagio);
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getSobrenome() { return sobrenome; }
    public int getIdade() {
        return idade;
    }
    public String getCidade() { return cidade; }
    public String getUF() {
        return UF;
    }
    public String getEmail() { return email; }
    public int getTentativasAcesso() { return tentativasAcesso; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public String getInicioEstagio() { return inicioEstagio; }
    public String getFimEstagio() { return fimEstagio; }

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setUF(String UF) {
        this.UF = UF;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTentativasAcesso(int tentativasAcesso) { this.tentativasAcesso = tentativasAcesso; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public void setInicioEstagio(String inicioEstagio) {
        this.inicioEstagio = inicioEstagio;
    }
    public void setFimEstagio(String fimEstagio) {
        this.fimEstagio = fimEstagio;
    }
}
