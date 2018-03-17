package com.knights.vita;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Scanner;

public class Share extends AppCompatActivity {

    Button shareData;
    Context context;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        ll = (LinearLayout) findViewById(R.id.ll);

        context = getApplicationContext();


        shareData = (Button)findViewById(R.id.shareData);



        final IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);



        shareData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                integrator.initiateScan();



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                Snackbar.make(findViewById(R.id.rootview),"Press Once More To Exit.", Snackbar.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//                Intent passIntent = new Intent(Share.this,MainActivity.class);
//                TODO call api here


                String[] scanParts = result.getContents().split("-");
                passIntent.putExtra("dealerId",dealerId);
                passIntent.putExtra("userId",scanParts[0]);
                passIntent.putExtra("vehicleId",scanParts[1]);
                startActivity(passIntent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
