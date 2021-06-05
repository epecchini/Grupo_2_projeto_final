package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;
import br.edu.uniritter.mobile.grupo_2_projeto_final.services.FirebaseServices;

public class CadastroActivity extends AppCompatActivity {

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
        }else {
            ClsAluno obj = new ClsAluno();

            obj.setNomeAluno(etNomeAluno.getText().toString());
            obj.setSobrenome(etSobrenomeAluno.getText().toString());
            obj.setEmail(etEmailAluno.getText().toString());
            obj.setSenha(etSenhaAluno.getText().toString());
            obj.setTentativasAcesso(0);

            if(!TextUtils.isEmpty(etDataNascimentoAluno.getText().toString())) obj.setDataNasc(etDataNascimentoAluno.getText().toString());
            if(!TextUtils.isEmpty(etCidadeAluno.getText().toString())) obj.setCidade(etCidadeAluno.getText().toString());
            if(!TextUtils.isEmpty(etUFaluno.getText().toString())) obj.setUF(etUFaluno.getText().toString());

            mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(obj.getEmail(), obj.getSenha())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        DatabaseReference firebase = FirebaseServices.getFirebaseDatabase();
                        firebase.child("Usuarios").child(mAuth.getCurrentUser().getUid()).setValue(obj);

                        startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(CadastroActivity.this, "Erro ao criar o Login", Toast.LENGTH_SHORT).show();
                    }
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