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
    private static String IdAlunoAtual;

    public static void putAluno(ClsAluno aluno){ alunos.put(aluno.getId(), aluno); }

    public static void putTurma(ClsTurma turma){ turmas.put(turma.getId(), turma); }

    public static void putEtapaAluno(ClsEtapaAluno etapaAluno){ etapasAluno.put(etapaAluno.getId(), etapaAluno); }

    public static void setIdAlunoAtual(String idAlunoAtual){ IdAlunoAtual = idAlunoAtual; }

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

    public static ClsAluno getAlunoAtual(){
        return alunos.get(IdAlunoAtual);
    }

    public static String getIdAlunoAtual(){
        return IdAlunoAtual;
    }

    //---------------------------------------------------------------------------------------------------

    public static ArrayList<ClsTurma> getTurma_list() { return new ArrayList<ClsTurma>(turmas.values()); }

    public static ArrayList<ClsEtapaAluno> getEtapaAluno_list() { return new ArrayList<ClsEtapaAluno>(etapasAluno.values()); }

    public static ArrayList<ClsAluno> getAluno_list() { return new ArrayList<ClsAluno>(alunos.values()); }

    public static String getIdEtapaAluno(String idTurma, Integer idEtapa){
        String res = "";
        String IdAluno = "";



        if (idEtapa == 1) {
            Log.i("Eliseo_getIdEtapaAluno_FonteDados_idTurma", idTurma);
            Log.i("Eliseo_getIdEtapaAluno_FonteDados_FonteDados.getIdAlunoAtual", FonteDados.getIdAlunoAtual());
            Log.i("Eliseo_getIdEtapaAluno_FonteDados_FonteDados.getEtapaAluno_list", String.valueOf(getEtapaAluno_list().isEmpty()));
        }

        for (ClsEtapaAluno obj : FonteDados.getEtapaAluno_list()) {

           /*if (idEtapa == 1) {
                Log.i("Eliseo_getIdEtapaAluno_FonteDados_obj.getIdTurma()", obj.getIdTurma());
                Log.i("Eliseo_getIdEtapaAluno_FonteDados_obj.getIdAluno()", obj.getIdAluno());
                Log.i("Eliseo_getIdEtapaAluno_FonteDados_obj.getIdEtapa()", String.valueOf(obj.getIdEtapa()));
            }*/

            if(obj.getIdTurma().equals(idTurma) && obj.getIdAluno().equals(FonteDados.getIdAlunoAtual()) && obj.getIdEtapa() == idEtapa){
                res = obj.getId();
                break;
            }
        }

        /*if (idEtapa == 1) {
            Log.i("Eliseo_getIdEtapaAluno_FonteDados_res", res);
        }*/

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
