package com.example.sergiocg.hackaton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {

    Button vender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        vender = findViewById(R.id.vender);

        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,Tomafotos.class);

                startActivity(intent);
            }
        });
    }
}
