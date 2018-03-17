package com.knights.vita;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ObjectRecognition extends AppCompatActivity {


    private Uri uriPhoto;
    Uri uploadUri;
    private Bitmap bitmap;

    String str,item;

    Integer userId = 1;

    String[] xyzing = {"apple","orange","bananna","carrot"};



    private int STORAGE_PERMISSION_CODE = 23;
    private int CAMERA_PERMISSION_CODE = 24;

    private int LOAD_IMAGE = 1;
    private int TAKE_CAMERA = 2;
    private int CHECK_IMAGE = 3;


//    private StorageReference mStorageRef;
//    private DatabaseReference mDatabaseRef;
//    private StorageMetadata metadata;


    Button camerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_recognition);


        camerButton = (Button) findViewById(R.id.cameraButton);



        StrictMode.VmPolicy.Builder builderStrict = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builderStrict.build());


        camerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (isCameraAllowed()){
                        openCamera();
                        return;
                    }
                    requestCamera();
                }else {
                    openCamera();
                }
            }
        });


    }


    private void openCamera() {

        Log.d("inside","openCamera");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        uriPhoto = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_CAMERA);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == TAKE_CAMERA && resultCode == RESULT_OK) {
            Uri selectedImage = uriPhoto;
            uploadUri = uriPhoto;
            getContentResolver().notifyChange(selectedImage, null);
            ContentResolver cr = getContentResolver();


            //byte[] encodeValue = Base64.encode(testValue.getBytes(), Base64.DEFAULT);


            byte[] byteArrayImage;
            String encodedImage = "";

            try {
                bitmap = android.provider.MediaStore.Images.Media
                        .getBitmap(cr, selectedImage);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                byteArrayImage = baos.toByteArray();

                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                Log.d("encodedImage",encodedImage);

                //img.setImageBitmap(bitmap);
                //not showing image as already shown in camera view
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
                Log.e("Camera", e.toString());
            }

            uploadImage(encodedImage);
        }

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


//        if(requestCode == STORAGE_PERMISSION_CODE){
//
//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this,"Permission granted.Click Selfie Again.",Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
//            }
//        }



        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted.Click Selfie Again.",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }



    private boolean isCameraAllowed() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);

        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }



    //Requesting permission
    private void requestCamera(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)){

        }
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
    }



    public void uploadImage(String encodedImage){


        String url = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyCtFDOWbGdYmjJrdKs4oUlwhoNR5w79Kew";



        final JsonArray requests = new JsonArray();
        JsonArray features = new JsonArray();
        JsonObject image = new JsonObject();
//        JsonObject source = new JsonObject();
        JsonObject list = new JsonObject();
        JsonObject forFeatures = new JsonObject();

        JsonObject finalObject = new JsonObject();


        JsonObject imageRoot = new JsonObject();
        JsonObject featuresRoot = new JsonObject();


        JsonObject finalOutput = new JsonObject();


        final JsonObject response = new JsonObject();
        


        try{
            image.addProperty("content",encodedImage);
            forFeatures.addProperty("type","LABEL_DETECTION");
            forFeatures.addProperty("maxResults",3);
            features.add(forFeatures);


            Map<String,JsonObject> mapPass = new HashMap<>();


            imageRoot.add("image", image);
            imageRoot.add("features",features);


            requests.add(imageRoot);


            finalOutput.add("requests",requests);

            finalObject = finalOutput.getAsJsonObject();



        }catch (Exception e){

        }




        Ion.with(getApplicationContext())
                .load(url)
                .setJsonObjectBody(finalObject)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {


                        str = result.toString();
                        Log.d("resp",str);

                    }
                });

        int flag = 0;
        Log.d("length xyz",Integer.toString(xyzing.length));

//        for(int i=0;i<xyzing.length;i++){
//            if(str.contains(xyzing[i])){
//                flag = 1;
//                item = xyzing[i];
//                break;
//
//            }
//
//        }

        //harcoding here TODO;remove this
        item = "carrot";


        if(flag == 1){


            Log.d("elementdfound","........");

            UrlClass urlClass = new UrlClass();
            String rootUrl = urlClass.getUrl();
            String JSON_URL =rootUrl + "/recs/object/" + item + "?id=" + userId;
            Log.d("completeUrl",JSON_URL);


            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                JSONObject obj = new JSONObject(response);

                                Log.d("test","success");
                                Log.d("sample",obj.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("here",".....");
                            Log.d("here",error.toString());
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }

    }



}
