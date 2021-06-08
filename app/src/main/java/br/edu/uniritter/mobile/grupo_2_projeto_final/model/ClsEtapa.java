package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

public class ClsEtapa {

    private int idEtapa;
    private String dataLimite;
    private String nomeEtapa;
    private String textoEtapa;

    public ClsEtapa() {
        super();
    }

    public ClsEtapa(int idEtapa, String dataLimite, String nomeEtapa, String textoEtapa) {
        this.idEtapa = idEtapa;
        this.dataLimite = dataLimite;
        this.nomeEtapa = nomeEtapa;
        this.textoEtapa = textoEtapa;
    }

    public int getIdEtapa() { return idEtapa; }
    public String getDataLimite() { return dataLimite; }
    public String getNomeEtapa() { return nomeEtapa; }
    public String getTextoEtapa() { return textoEtapa; }

    public void setIdEtapa(int idEtapa) { this.idEtapa = idEtapa; }
    public void setDataLimite(String dataLimite) { this.dataLimite = dataLimite; }
    public void setNomeEtapa(String nomeEtapa) { this.nomeEtapa = nomeEtapa; }
    public void setTextoEtapa(String textoEtapa) { this.textoEtapa = textoEtapa; }
}
