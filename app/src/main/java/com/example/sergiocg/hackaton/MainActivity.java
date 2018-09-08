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


public class MainActivity extends AppCompatActivity {

    Button loginB , registro;
    private EditText editTextUsername, editTextPassword;
    String url = "http://192.168.43.150/MxHacks/datos.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro = findViewById(R.id.registroLog);
        loginB = findViewById(R.id.entrarlog);

        editTextUsername = (EditText) findViewById(R.id.correoLog);
        editTextPassword = (EditText) findViewById(R.id.passwordLog);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,registro.class);

                startActivity(intent);
            }
        });

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login();
                Intent intent = new Intent(MainActivity.this,dashboard.class);
                startActivity(intent);
            }
        });

    }

    public void login(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(),"Si se pudo Valedor",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,dashboard.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Salio mal Valedor",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"errr:"  + error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("correo",editTextUsername.getText().toString().trim());
                params.put("contrasenia",editTextPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
