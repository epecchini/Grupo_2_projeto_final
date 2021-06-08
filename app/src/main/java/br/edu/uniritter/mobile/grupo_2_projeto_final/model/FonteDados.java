package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class FonteDados {
    private static Map<String, ClsAluno> alunos = new HashMap<>();
    private static Map<String, ClsTurma> turmas = new HashMap<>();
    private static Map<String, ClsEtapaAluno> etapasAluno = new HashMap<>();
    private static Map<String, ClsEtapaAluno> todasAsEtapasDosAluno = new HashMap<>();
    private static String IdAlunoAtual;
    private static String EmailEntrada;
    private static String SenhaEntrada;

    public static void putAluno(ClsAluno aluno){ alunos.put(aluno.getId(), aluno); }

    public static void putTurma(ClsTurma turma){ turmas.put(turma.getId(), turma); }

    public static void putEtapaAluno(ClsEtapaAluno etapaAluno){ etapasAluno.put(etapaAluno.getId(), etapaAluno); }

    public static void putTodasAsEtapasDosAluno(ClsEtapaAluno etapaAluno){ todasAsEtapasDosAluno.put(etapaAluno.getId(), etapaAluno); }

    public static void setIdAlunoAtual(String idAlunoAtual){ IdAlunoAtual = idAlunoAtual; }

    public static void setEmailEntrada(String emailEntrada){ EmailEntrada = emailEntrada; }

    public static void setSenhaEntrada(String senhaEntrada){ SenhaEntrada = senhaEntrada; }

    //---------------------------------------------------------------------------------------------------

    public static ClsAluno getAluno(String id){
       return alunos.get(id);
    }

    public static ClsTurma getTurma(String id){
        return turmas.get(id);
    }

    public static ClsEtapaAluno getEtapaAluno(String id) {
        return etapasAluno.get(id);
    }

    public static ClsEtapaAluno getTodasAsEtapasDosAluno(String id) { return todasAsEtapasDosAluno.get(id); }

    public static ClsAluno getAlunoAtual(){
        return alunos.get(IdAlunoAtual);
    }

    public static String getIdAlunoAtual(){
        return IdAlunoAtual;
    }

    public static String getEmailEntrada(){ return EmailEntrada; }

    public static String getSenhaEntrada(){ return SenhaEntrada; }

    //---------------------------------------------------------------------------------------------------

    public static ArrayList<ClsTurma> getTurma_list() { return new ArrayList<ClsTurma>(turmas.values()); }

    public static ArrayList<ClsEtapaAluno> getEtapaAluno_list() { return new ArrayList<ClsEtapaAluno>(etapasAluno.values()); }

    public static ArrayList<ClsEtapaAluno> getTodasAsEtapasDosAluno_list() { return new ArrayList<ClsEtapaAluno>(todasAsEtapasDosAluno.values()); }

    public static ArrayList<ClsAluno> getAluno_list() { return new ArrayList<ClsAluno>(alunos.values()); }

    public static String getIdEtapaAluno(String idTurma, Integer idEtapa){
        String res = "";

        for (ClsEtapaAluno obj : FonteDados.getEtapaAluno_list()) {
            if(obj.getIdTurma().equals(idTurma) && obj.getIdAluno().equals(FonteDados.getIdAlunoAtual()) && obj.getIdEtapa() == idEtapa){
                res = obj.getId();
                break;
            }
        }

        return res;
    }

    public static String getTextoEtapa(String idTurma, Integer idEtapa){
        String textoEtapa = "Texto não encontrado!";

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            if(obj.getIdEtapa() == idEtapa){
                textoEtapa = obj.getTextoEtapa();
                break;
            }
        }

        return textoEtapa;
    }

    public static String getDataLimite(String idTurma, Integer idEtapa){
        String dataLimite = "--/--/----";

        for (ClsEtapa obj : FonteDados.getTurma(idTurma).getEtapa()) {
            if(obj.getIdEtapa() == idEtapa){
                dataLimite = obj.getDataLimite();
                break;
            }
        }

        return dataLimite;
    }
}
