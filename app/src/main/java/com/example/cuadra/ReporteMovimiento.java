package com.example.cuadra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReporteMovimiento extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ModelClass> list;
    ModelClass modelClass;
    adapterClass adapter;
   // List<ModelClass> values = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_movimiento);
        recyclerView = findViewById((R.id.recyclerview));
        linearLayoutManager = new LinearLayoutManager(this);
        context=this;
        list=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
/*
             modelClass=new ModelClass("1","01/01/2020","Cuenta 1","500");
        list.add(modelClass);
        modelClass=new ModelClass("2","01/01/2020","Cuenta 2","400");
        list.add(modelClass);
        modelClass=new ModelClass("3","01/01/2020","Cuenta 3","700");
        list.add(modelClass);

        adapter=new adapterClass(list,context);

        recyclerView.setAdapter(adapter);
  */ getData();


    }

    private void getData() {
        String url = "http://startup-bolivia.net/proyectoquiron/php/cuentas/reportes/reportepagos.php";
        //url="http://startup-bolivia.net/proyectoquiron/php/cuentas/cuentas.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //   Toast.makeText(context, "Vamos", Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray respuesta = response;
//                    Toast.makeText(context, "Reportando..."+respuesta.length()+"registros", Toast.LENGTH_SHORT).show();
                    try {
//                        Toast.makeText(context, "Reportandooo", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < respuesta.length(); i++) {
                            JSONObject movimiento = respuesta.getJSONObject(i);
                            String fecha = movimiento.getString("fechahoramovimiento");
                            String cuenta1 = movimiento.getString("cuenta");
                            String monto = movimiento.getString("monto");
                            String tipo = movimiento.getString("tipo");
                            int nro=i+1;
                            modelClass = new ModelClass(nro + "", fecha, cuenta1, monto);
                            list.add(modelClass);
                        }
                      //  Toast.makeText(context, "Cargo todo", Toast.LENGTH_SHORT).show();
                        adapter = new adapterClass(list, context);
                        recyclerView.setAdapter(adapter);

                    }catch (JSONException e) {
                        Toast.makeText(context, "No pasa nada", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }} catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}