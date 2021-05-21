package br.edu.uniritter.mobile.grupo_2_projeto_final;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebaseDB {

    private  static DatabaseReference database;
    private static FirebaseAuth mAuth;

    public static DatabaseReference getFirebaseDatabase(){
        if(database==null){
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }
    public static FirebaseAuth getFirebaseAuth(){
        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

}
