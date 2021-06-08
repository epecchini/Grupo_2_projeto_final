package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAlunoAuth;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsEtapa;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class CadastroActivity extends AppCompatActivity {

    private Boolean modificacaoDados = false;

    EditText etNomeAluno;
    EditText etSobrenomeAluno;
    EditText etEmailAluno;
    EditText etSenhaAluno;
    EditText etConfirmarSenhaAluno;
    EditText etDataNascimentoAluno;
    EditText etCidadeAluno;
    EditText etUFaluno;
    EditText etNomeEmpresaAluno;
    EditText etInicioEstagioAluno;
    EditText etFimEstagioAluno;

    TextView tvNomeTurmaAtual;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent it = getIntent();
        modificacaoDados = it.getBooleanExtra("modificacaoDados", false);

        etNomeAluno = (EditText) findViewById(R.id.etNomeAluno);
        etSobrenomeAluno = (EditText) findViewById(R.id.etSobrenomeAluno);
        etEmailAluno = (EditText) findViewById(R.id.etEmailAluno);
        etSenhaAluno = (EditText) findViewById(R.id.etSenhaAluno);
        etConfirmarSenhaAluno = (EditText) findViewById(R.id.etConfirmarSenhaAluno);
        etDataNascimentoAluno = (EditText) findViewById(R.id.etDataNascimentoAluno);
        etCidadeAluno = (EditText) findViewById(R.id.etCidadeAluno);
        etUFaluno = (EditText) findViewById(R.id.etUFaluno);
        etNomeEmpresaAluno = (EditText) findViewById(R.id.etNomeEmpresaAluno);
        etInicioEstagioAluno = (EditText) findViewById(R.id.etInicioEstagioAluno);
        etFimEstagioAluno = (EditText) findViewById(R.id.etFimEstagioAluno);

        tvNomeTurmaAtual = (TextView) findViewById(R.id.tvNomeTurmaAtual);

        if(modificacaoDados){
            etEmailAluno.setEnabled(false);
            etEmailAluno.setInputType(InputType.TYPE_NULL);
            etSenhaAluno.setEnabled(false);
            etSenhaAluno.setInputType(InputType.TYPE_NULL);
            etConfirmarSenhaAluno.setEnabled(false);
            etConfirmarSenhaAluno.setInputType(InputType.TYPE_NULL);

            etNomeAluno.setText(FonteDados.getAlunoAtual().getNomeAluno());
            etSobrenomeAluno.setText(FonteDados.getAlunoAtual().getSobrenome());
            etEmailAluno.setText(FonteDados.getEmailEntrada());
            etSenhaAluno.setText(FonteDados.getSenhaEntrada());
            etConfirmarSenhaAluno.setText(FonteDados.getSenhaEntrada());
            try{ etDataNascimentoAluno.setText(FonteDados.getAlunoAtual().getDataNasc()); } catch(NullPointerException ex){ }
            try{ etCidadeAluno.setText(FonteDados.getAlunoAtual().getCidade()); } catch(NullPointerException ex){ }
            try{ etUFaluno.setText(FonteDados.getAlunoAtual().getUF()); } catch(NullPointerException ex){ }
            try{ etNomeEmpresaAluno.setText(FonteDados.getAlunoAtual().getNomeEmpresa()); } catch(NullPointerException ex){ }
            try{ etInicioEstagioAluno.setText(FonteDados.getAlunoAtual().getInicioEstagio()); } catch(NullPointerException ex){ }
            try{ etFimEstagioAluno.setText(FonteDados.getAlunoAtual().getFimEstagio()); } catch(NullPointerException ex){ }

            try{ tvNomeTurmaAtual.setText(FonteDados.getTurma(FonteDados.getAlunoAtual().getIdTurmaAtual()).getNomeTurma()); } catch(NullPointerException ex){ }
        }
    }

    public void onClickAccept(View view){
        if (!verifyEmptyText(R.id.etNomeAluno)) return;
        if (!verifyEmptyText(R.id.etSobrenomeAluno)) return;
        if (!verifyEmptyText(R.id.etEmailAluno)) return;
        if (!verifyEmptyText(R.id.etSenhaAluno)) return;
        if (!verifyEmptyText(R.id.etConfirmarSenhaAluno)) return;

        if(!etSenhaAluno.getText().toString().equals(etConfirmarSenhaAluno.getText().toString())){
            mostraToast("A confirmação de senha não está correta, favor tentar novamente!");
            etConfirmarSenhaAluno.setText("");
            return;
        }else if(modificacaoDados) {
            FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

            DocumentReference aluno = store.collection("alunos").document(FonteDados.getIdAlunoAtual());

            ClsAluno obj = FonteDados.getAlunoAtual();

            if(!TextUtils.isEmpty(etCidadeAluno.getText().toString())) obj.setCidade(etCidadeAluno.getText().toString());
            if(!TextUtils.isEmpty(etDataNascimentoAluno.getText().toString())) obj.setDataNasc(etDataNascimentoAluno.getText().toString());
            if(!TextUtils.isEmpty(etFimEstagioAluno.getText().toString())) obj.setFimEstagio(etFimEstagioAluno.getText().toString());
            if(!TextUtils.isEmpty(etInicioEstagioAluno.getText().toString())) obj.setInicioEstagio(etInicioEstagioAluno.getText().toString());
            obj.setNomeAluno(etNomeAluno.getText().toString());
            if(!TextUtils.isEmpty(etNomeEmpresaAluno.getText().toString())) obj.setNomeEmpresa(etNomeEmpresaAluno.getText().toString());
            obj.setSobrenome(etSobrenomeAluno.getText().toString());
            if(!TextUtils.isEmpty(etUFaluno.getText().toString())) obj.setUF(etUFaluno.getText().toString());

            aluno
                    .set(obj, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> finish())
                    .addOnFailureListener(e -> mostraToast( "Erro no gravar a modificação!"));
        }else{
            ClsAlunoAuth objAuth = new ClsAlunoAuth();

            objAuth.setEmail(etEmailAluno.getText().toString());
            objAuth.setSenha(etSenhaAluno.getText().toString());

            mAuth = FirebaseServices.getFirebaseAuthInstance();

            mAuth.createUserWithEmailAndPassword(objAuth.getEmail(), objAuth.getSenha().toString())
                    .addOnCompleteListener(this, task -> {
                        if(task.isSuccessful()){
                            DatabaseReference firebase = FirebaseServices.getFirebaseDatabase();
                            firebase.child("Usuarios").child(mAuth.getCurrentUser().getUid()).setValue(objAuth);

                            FirebaseFirestore store = FirebaseServices.getFirebaseFirestoreInstance();

                            ClsAluno obj = new ClsAluno();

                            obj.setIdRealtime(mAuth.getCurrentUser().getUid());
                            obj.setNomeAluno(etNomeAluno.getText().toString());
                            obj.setSobrenome(etSobrenomeAluno.getText().toString());
                            obj.setTentativasAcesso(0);
                            obj.setEprof(false);

                            if(!TextUtils.isEmpty(etDataNascimentoAluno.getText().toString())) obj.setDataNasc(etDataNascimentoAluno.getText().toString());
                            if(!TextUtils.isEmpty(etCidadeAluno.getText().toString())) obj.setCidade(etCidadeAluno.getText().toString());
                            if(!TextUtils.isEmpty(etUFaluno.getText().toString())) obj.setUF(etUFaluno.getText().toString());

                            store.collection("alunos").add(obj)
                                    .addOnSuccessListener(e -> startActivity(new Intent(CadastroActivity.this, LoginActivity.class)))
                                    .addOnFailureListener(e -> mostraToast("Erro ao criar o Login no Firestore!"));
                        }else{
                            mostraToast("Erro ao criar o Login no Realtime!");
                        }
                    });
        }
    }

    private boolean verifyEmptyText(Integer id){
        Boolean res = true;

        EditText et = (EditText) findViewById(id);

        if(TextUtils.isEmpty(et.getText().toString())){
            mostraToast("Favor preencher o campo: <" + et.getHint() + ">");
            res = false;
        }

        return res;
    }

    public void mostraToast(String msg) { Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_LONG).show(); }

    public void onClickIbBackCadastroAluno(View view){ finish(); }
}