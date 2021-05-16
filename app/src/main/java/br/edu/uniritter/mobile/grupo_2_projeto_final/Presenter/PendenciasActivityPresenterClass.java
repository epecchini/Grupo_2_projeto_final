package br.edu.uniritter.mobile.grupo_2_projeto_final.Presenter;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.uniritter.mobile.grupo_2_projeto_final.classGroup.Pendencias;

public class PendenciasActivityPresenterClass implements Response.Listener<JSONArray>,
        Response.ErrorListener, PendenciasActivityPresenterInterface.PendenciasActivityPresenterPresenter {

    private List<Pendencias> list =  new ArrayList<>();
    private PendenciasActivityPresenterInterface.PendenciasActivityPresenterView view;

    public PendenciasActivityPresenterClass(PendenciasActivityPresenterInterface.PendenciasActivityPresenterView view){
        this.view = view;
        start();
    }

    @Override
    public void start() {
        RequestQueue queue = Volley.newRequestQueue(view.getContexto());
        String url = "https://jsonplaceholder.typicode.com/albums";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void encerrar() { }

    @Override
    public void onErrorResponse(VolleyError error) { view.mostraToast("deu erro: " + error.getMessage()); }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject json = response.getJSONObject(i);
                Pendencias obj = new Pendencias(
                        json.getInt("userId"),
                        json.getString("id"),
                        json.getString("title"));
                list.add(obj);
            }
            view.bindPendencias(list);
        } catch (JSONException e) {
            Log.e("erro", e.getMessage());
            e.printStackTrace();
        }
    }
}
