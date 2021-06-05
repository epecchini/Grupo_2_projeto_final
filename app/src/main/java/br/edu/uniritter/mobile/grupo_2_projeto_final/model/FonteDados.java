package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class FonteDados {
    private static Map<String, ClsAluno> alunos = new HashMap<>();
    private static Map<String, ClsTurma> turmas = new HashMap<>();
    private static Map<String, ClsTurmaAluno> turmasAlunos = new HashMap<>();
    private static Map<String, ClsEtapaAluno> etapasAluno = new HashMap<>();
    private static FirebaseAuth mAuth;

    public static void putAluno(ClsAluno aluno){ alunos.put(aluno.getId(), aluno); }

    public static void putTurma(ClsTurma turma){ turmas.put(turma.getId(), turma); }

    public static void putTurmaAluno(ClsTurmaAluno turmaAluno){ turmasAlunos.put(turmaAluno.getIdTurma(), turmaAluno); }

    public static void putEtapaAluno(ClsEtapaAluno etapaAluno){ etapasAluno.put(etapaAluno.getId(), etapaAluno); }

    //---------------------------------------------------------------------------------------------------

    public static ClsAluno getAluno(String id){
       return alunos.get(id);
    }

    public static ClsTurma getTurma(String id){
        return turmas.get(id);
    }

    public static ClsTurmaAluno getTurmaAluno(String id) { return turmasAlunos.get(id); }

    public static ClsEtapaAluno getEtapaAluno(String id) { return etapasAluno.get(id); }

    //---------------------------------------------------------------------------------------------------

    public static Map<String, ClsTurma> getTurma_map(){
        return turmas;
    }

    //---------------------------------------------------------------------------------------------------

    public static ArrayList<ClsTurma> getTurma_list() { return new ArrayList<ClsTurma>(turmas.values()); }

    public static ArrayList<ClsEtapaAluno> getEtapaAluno_list() { return new ArrayList<ClsEtapaAluno>(etapasAluno.values()); }

    public static ArrayList<ClsAluno> getAluno_list() { return new ArrayList<ClsAluno>(alunos.values()); }

    public static String getIdEtapaAluno(String idTurma, Integer idEtapa){
        mAuth = FirebaseServices.getFirebaseAuth();

        String res = "";

        for (ClsEtapaAluno obj : FonteDados.getEtapaAluno_list()) {
            if(obj.getIdTurma().equals(idTurma) && obj.getIdAluno().equals(mAuth.getCurrentUser().getUid()) && obj.getIdEtapa() == idEtapa){
                res= obj.getId();
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
