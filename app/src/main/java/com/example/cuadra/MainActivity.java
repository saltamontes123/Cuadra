package com.example.cuadra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    EditText edtusuario, edtclave;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtusuario = (EditText) findViewById(R.id.etusuario);
        edtclave = (EditText) findViewById(R.id.etpassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario("http://startup-bolivia.net/mental/validarpost.php");
            }
        });

    }

    private void validarUsuario(String URL) {
        // Intent intent=new Intent(getApplicationContext(),Preguntas.class);
        // startActivity(intent);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    //Intent intent=new Intent(MainActivity.this,Preguntas.class);
                    //intent.putExtra("area","salud");
                    //startActivity(intent);
                    Intent i = new Intent(MainActivity.this, Movimientos.class);
                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "No encontrados", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", edtusuario.getText().toString());
                parametros.put("password", edtclave.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
