package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FonteDados {
    private static Map<String, Usuario> usuarios = new HashMap<>();
    private static Map<String, ClsTurma> turmas = new HashMap<>();
    private static Map<String, ClsTurmaAluno> turmasAlunos = new HashMap<>();

    public static void putUsuario(Usuario usuario){ usuarios.put(usuario.getId(), usuario); }

    public static void putTurma(ClsTurma turma){ turmas.put(turma.getId(), turma); }

    public static void putTurmaAluno(ClsTurmaAluno turmaAluno){ turmasAlunos.put(turmaAluno.getIdTurma(), turmaAluno); }

    public static Usuario getUsuario(String id){
       return usuarios.get(id);
    }

    public static ClsTurma getTurma(String id){
        return turmas.get(id);
    }

    public static Map<String, ClsTurma> getTurma_map(){
        return turmas;
    }

    public static ClsTurmaAluno getTurmaAluno(String id) { return turmasAlunos.get(id); }

    public static ArrayList getTurma_list() { return new ArrayList(turmas.values()); }
}
