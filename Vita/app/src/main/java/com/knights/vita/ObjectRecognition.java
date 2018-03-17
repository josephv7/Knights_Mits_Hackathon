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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ObjectRecognition extends AppCompatActivity {


    private Uri uriPhoto;
    Uri uploadUri;
    private Bitmap bitmap;



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



//        if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri filePath = data.getData();
//            uploadUri = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//
//                writeToFile(BitMapToString(bitmap),Home.this);
//                Intent showImage = new Intent(Home.this,ShowSelected.class);
//                //showImage.putExtra("imageBitmap",BitMapToString(bitmap));
//                startActivityForResult(showImage,CHECK_IMAGE);
////                img.setImageBitmap(bitmap);
//                //dont show here ...show in a different activity
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


//        if (requestCode == CHECK_IMAGE){
//            if (resultCode == RESULT_OK){
//                uploadImage();
//
//            }else if(resultCode == RESULT_CANCELED){
//                /////
//            }
//        }


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


//        String request = "{" +
//                "  \"requests\":[" +
//                "    {" +
//                "      \"image\":{" +
//                "        \"content\":\"/9j/7QBEUGhvdG9zaG9...image contents...fXNWzvDEeYxxxzj/Coa6Bax//Z\"" +
//                "      },\n" +
//                "      \"features\":[\n" +
//                "        {" +
//                "          \"type\":\"FACE_DETECTION\"," +
//                "          \"maxResults\":10\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}"
//

        JsonArray requests = new JsonArray();
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
//            image.put("content",encodedImage);
            image.addProperty("content",encodedImage);
//            forFeatures.put("type","LABEL_DETECTION");
            forFeatures.addProperty("type","LABEL_DETECTION");
//            forFeatures.put("maxResults",1);
            forFeatures.addProperty("maxResults",1);
//            features.put(0,forFeatures);
            features.add(forFeatures);
//            requests.put(0,image);
//            requests.put(1,features);


            Map<String,JsonObject> mapPass = new HashMap<>();


            imageRoot.add("image", image);
            imageRoot.add("features",features);


            requests.add(imageRoot);
//            requests.add(featuresRoot);

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


                        JsonObject response = new JsonObject();
                        JsonArray responseArray = new JsonArray();
                        responseArray = response.getAsJsonArray();
                        Log.d("size",Integer.toString(responseArray.size()));


                    }
                });





    }



}
