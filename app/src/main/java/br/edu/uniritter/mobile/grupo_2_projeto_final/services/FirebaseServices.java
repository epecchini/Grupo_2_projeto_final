package br.edu.uniritter.mobile.grupo_2_projeto_final.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

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
    public static FirebaseAuth getFirebaseAuth(){
        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }
}
