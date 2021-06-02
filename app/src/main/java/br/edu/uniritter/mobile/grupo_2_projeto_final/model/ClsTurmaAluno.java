package br.edu.uniritter.mobile.grupo_2_projeto_final.model;

import com.google.firebase.firestore.DocumentId;

public class ClsTurmaAluno {
    @DocumentId
    private String id;

    private String idTurma;
    private String idAluno;

    public ClsTurmaAluno () {
        super();
    }

    public ClsTurmaAluno(String id, String idTurma, String idAluno) {
        this.id = id;
        this.idTurma = idTurma;
        this.idAluno = idAluno;
    }

    public String getId() {
        return id;
    }

    public String getIdTurma() {
        return idTurma;
    }

    public String getIdAluno() {
        return idAluno;
    }
}
