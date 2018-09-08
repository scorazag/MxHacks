package com.example.sergiocg.hackaton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Tomafotos extends AppCompatActivity {

    Button tomarFoto;
    Button choose,upload;
    String url = "http://192.168.43.150/MxHacks/datos.php";
    int PICK_IMAGE_REQUEST = 111;
    protected ImageView image;
    ProgressDialog progressDialog;



    static  final  int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomafotos);

        image = findViewById(R.id.fotoExp);
        tomarFoto = findViewById(R.id.tomarFoto);
        choose = findViewById(R.id.selecfoto);
        upload = findViewById(R.id.enviarFoto);


        tomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    //Bitmap bitmap = BitmapFactory.decodeFile(path, options);


                }
            }
        });

        //opening image chooser option
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

    }
}
