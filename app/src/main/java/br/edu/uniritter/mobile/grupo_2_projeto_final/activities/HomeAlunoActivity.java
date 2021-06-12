package br.edu.uniritter.mobile.grupo_2_projeto_final.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.Adapter.AdaptTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter.IntTurmaAluno;
import br.edu.uniritter.mobile.grupo_2_projeto_final.R;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.ClsTurma;
import br.edu.uniritter.mobile.grupo_2_projeto_final.model.FonteDados;

public class HomeAlunoActivity extends AppCompatActivity
        implements IntTurmaAluno.intTurmAlunoPresView {

    private IntTurmaAluno.intTurmAlunoPres presenter;

    TextView textX, textY, textZ;
    RecyclerView rv;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aluno);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textZ = (TextView) findViewById(R.id.textZ);

        rv = (RecyclerView) findViewById(R.id.rvHomeAluno);

        Log.i("Eliseo_onCreate_HomeAlunoActivity", "ok");
    }

    @Override
    public void onResume(){
        super.onResume();

        List<ClsTurma> list = new ArrayList<>();

        for (ClsTurma obj : FonteDados.getTurma_list()) {
            if(obj.isLiberada() && !obj.isEncerrada()) list.add(obj);
        }

        bindTurmaAluno(list);

        sensorManager.registerListener(accelListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(accelListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelListener);
    }

    SensorEventListener accelListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("X : " + (int)x);
            textY.setText("Y : " + (int)y);
            textZ.setText("Z : " + (int)z);

            if(event.values[0] > 0) rv.setBackgroundResource(R.drawable.gradiante1);
            else if (event.values[0] < 0) rv.setBackgroundResource(R.drawable.gradiante2);
            else rv.setBackgroundResource(R.drawable.gradiante0);
        }
    };

    public void onClick(View view) {
        if(TextUtils.isEmpty(FonteDados.getAlunoAtual().getIdTurmaAtual())) return;

        Intent intent = new Intent(view.getContext(), EtapasAlunoActivity.class);
        intent.putExtra("idTurma", FonteDados.getAlunoAtual().getIdTurmaAtual());
        view.getContext().startActivity(intent);
    }

    public void onClickDadosPessoais(View view) {
        Intent intent = new Intent(view.getContext(), CadastroActivity.class);
        intent.putExtra("modificacaoDados", true);
        view.getContext().startActivity(intent);
    }

    public void onClickbtWhatsappHomeAluno(View view) {
        String url = "https://api.whatsapp.com/send?phone=+555130925600";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void bindTurmaAluno(List<ClsTurma> list) {
        RecyclerView rv = findViewById(R.id.rvHomeAluno);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //
        rv.setLayoutManager(llm);
        AdaptTurmaAluno cls = new AdaptTurmaAluno(list);
        rv.setAdapter(cls);
    }

    @Override
    public void mostraToast(String msg) { Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show(); }

    @Override
    public Context getContexto() { return this.getApplicationContext(); }
}
