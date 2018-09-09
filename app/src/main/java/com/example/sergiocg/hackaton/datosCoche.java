package com.example.sergiocg.hackaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class datosCoche extends AppCompatActivity {

    Button enviar;
    EditText precio,marcaE,kilometroE,modeloE,anio;
    String url = "http://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_coche);

        enviar = findViewById(R.id.botonEnviar);
        precio = findViewById(R.id.precio);
        marcaE = findViewById(R.id.marca);
        kilometroE = findViewById(R.id.kilometro);
        modeloE = findViewById(R.id.modelo);
        anio = findViewById(R.id.anio);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDatos();
            }
        });
    }

    public void enviarDatos(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(),"Si se pudo Valedor",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(datosCoche.this,dashboard.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Salio mal Valedor",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"errr:"  + error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("precio",precio.getText().toString().trim());
                params.put("modelo",modeloE.getText().toString().trim());
                params.put("marca",marcaE.getText().toString().trim());
                params.put("kilometraje",kilometroE.getText().toString().trim());
                params.put("anio",anio.getText().toString().trim());
                return params;
            }
        };
    }
}
