package br.edu.uniritter.mobile.grupo_2_projeto_final.services;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

public class FirebaseServices {
    private static DatabaseReference database;
    private static FirebaseAuth fbAuth;
    private static FirebaseUser fbUser;
    private static FirebaseFirestore fbStore;
    private static FirebaseAuth mAuth;

    public static FirebaseAuth getFirebaseAuthInstance() {
        if (fbAuth == null) {
            fbAuth = FirebaseAuth.getInstance();
        }
        return fbAuth;
    }

    public static FirebaseFirestore getFirebaseFirestoreInstance() {
        if (fbStore == null) {
            fbStore = FirebaseFirestore.getInstance();
        }
        return fbStore;
    }

    public static void setFirebaseUser(FirebaseUser fbu) {
        fbUser = fbu;
    }
    public static FirebaseUser getFirebaseUser() {
        return fbUser;
    }

    public static DatabaseReference getFirebaseDatabase(){
        if(database==null){
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }
}
