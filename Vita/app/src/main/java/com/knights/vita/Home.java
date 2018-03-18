package com.knights.vita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    Button geneData,share,object,fit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        geneData = (Button) findViewById(R.id.geneData);
        share = (Button)findViewById(R.id.share);
        object = (Button)findViewById(R.id.object);
        fit = (Button)findViewById(R.id.fit);

        geneData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent geneIntent = new Intent(Home.this,GetGeneData.class);
                startActivity(geneIntent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Home.this,Share.class);
                startActivity(shareIntent);
            }
        });

        object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objectIntent = new Intent(Home.this,ObjectRecognition.class);
                startActivity(objectIntent);
            }
        });


        fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fitIntent = new Intent(Home.this,Fit.class);
                startActivity(fitIntent);
            }
        });


    }
}
