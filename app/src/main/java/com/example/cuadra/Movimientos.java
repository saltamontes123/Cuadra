package com.example.cuadra;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movimientos extends AppCompatActivity implements View.OnClickListener {

    ImageButton pagos, cobros, reportes, salir;
    Spinner spinner;
    String[] cuentas;
    List<String> values = new ArrayList<String>();
    StringRequest stringRequest;
    TextView cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);
        pagos = (ImageButton) findViewById(R.id.ibpagos);
        pagos.setOnClickListener(this);
        cobros = (ImageButton) findViewById(R.id.ibreportes);
        cobros.setOnClickListener(this);
        reportes = (ImageButton) findViewById(R.id.ibcobros);
        reportes.setOnClickListener(this);
        salir = (ImageButton) findViewById(R.id.ibsalir);
        salir.setOnClickListener(this);
        spinner = (Spinner) findViewById(R.id.spcuenta);
        cantidad = (TextView) findViewById(R.id.etmonto);
        llenarSpinner();
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>
                                               arg0, View arg1, int arg2, long arg3) {
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        values.add("Seleccionar...");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }//cierra el procedimiento oncreate

    @Override
    public void onClick(View view) {
//  el codigo comentado salta a otro intent con parametros
//        Intent intent = new Intent(this, Materias.class);
//        intent.putExtra("area", view.getTag().toString());
//        startActivity(intent);
        switch (view.getId()) {
            case R.id.ibpagos:
                guardarPago(view);
                break;
            case R.id.ibcobros:
                guardarCobro(view);
                //Toast.makeText(this, "Guardando cobro...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibreportes:
                //Toast.makeText(this, "Generando reporte...", Toast.LENGTH_SHORT).show();
                Intent re = new Intent(Movimientos.this, ReporteMovimiento.class);
                startActivity(re);
                break;
            case R.id.ibsalir:
                // finish();
                // System.exit(0);
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Confirmar");
                dialogo1.setMessage("Realmente desea salir del programa?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialogo1.show();

        }
    }

    //Guardar pago
    public void guardarPago(View view) {
        //     Toast.makeText(this, "Guardando el registro de pago...", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://startup-bolivia.net/proyectoquiron/php/cuentas/movimiento.php";
        String url = "http://startup-bolivia.net/proyectoquiron/php/cuentas/movimiento.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Movimientos.this, "Hay respuesta", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Movimientos.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String cuenta = spinner.getSelectedItem().toString();
                String fechahoramovimiento = "2020-01-16 10:20:00";
                String idusuario = "9";
                String glosa = "glosa que debe ser escrita en un editor de texto";
                String monto = cantidad.getText().toString();
                String tipo = "1";

                Map<String, String> parametros = new HashMap<String,String>();
                parametros.put("cuenta", cuenta);
                parametros.put("fechahoramovimiento",fechahoramovimiento);
                parametros.put("idusuario", idusuario);
                parametros.put("glosa", glosa);
                parametros.put("monto", monto);
                parametros.put("tipo", tipo);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        //   request.add(stringRequest);
    }

    //Guardar cobro
    public void guardarCobro(View view) {
        //     Toast.makeText(this, "Guardando el registro de pago...", Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://startup-bolivia.net/proyectoquiron/php/cuentas/movimiento.php";
        String url = "http://startup-bolivia.net/proyectoquiron/php/cuentas/movimiento.php?";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Movimientos.this, "Registro de cobro guardado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Movimientos.this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String cuenta = spinner.getSelectedItem().toString();
                String fechahoramovimiento = "2020-01-16 10:20:00";
                String idusuario = "9";
                String glosa = "glosa que debe ser escrita en un editor de texto";
                String monto = cantidad.getText().toString();
                String tipo = "2";

                Map<String, String> parametros = new HashMap<String,String>();
                parametros.put("cuenta", cuenta);
                parametros.put("fechahoramovimiento",fechahoramovimiento);
                parametros.put("idusuario", idusuario);
                parametros.put("glosa", glosa);
                parametros.put("monto", monto);
                parametros.put("tipo", tipo);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        //   request.add(stringRequest);
    }
    //llenar el spiner con las cuentas correspondientes a cada usuario
    private void llenarSpinner() {
        String url = "http://startup-bolivia.net/proyectoquiron/php/cuentas/cuentas.php";
        //       url="http://startup-bolivia.net/mental/preguntar.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Toast.makeText(Movimientos.this, "Cuentas", Toast.LENGTH_SHORT).show();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject cuenta = response.getJSONObject(i);
                                String codigocuenta = cuenta.getString("codigocuenta");
                                String cuenta1 = cuenta.getString("cuenta");
                                values.add(codigocuenta + " " + cuenta1);
                            }
                        } catch (JSONException e) {
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
///guardar los datos ingresados
}