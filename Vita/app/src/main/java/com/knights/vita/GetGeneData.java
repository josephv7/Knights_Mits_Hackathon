package com.knights.vita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetGeneData extends AppCompatActivity {


    Integer userId = 1;

    UrlClass urlClass = new UrlClass();
    String JSON_URL = urlClass.getUrl();
    String dummyUrl = "http://10.90.90.19:3000/recs/1";


    ArrayList<String> mainList;
    ArrayList<String> responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_gene_data);
        setContentView(R.layout.activity_home);


        mainList = new ArrayList<String>();
        responseList = new ArrayList<String>();



//        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL + "/recs/" + Integer.toString(userId),
        StringRequest stringRequest = new StringRequest(Request.Method.GET,dummyUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("size",Integer.toString(jsonArray.length()));
                            Log.d("response",response);



                            Log.d("test","success");
                            Log.d("sample",jsonArray.toString());



                            for(int i =0; i < jsonArray.length();i++){
                                mainList.add(jsonArray.get(i).toString());
                            }


                            Log.d("main size",Integer.toString(mainList.size()));
                            ///////////////////////////

                            Log.d("main",mainList.get(0).toString());

                            for(int i =0; i<mainList.size();i++){
                                String dummy1 = mainList.get(i).toString();
                                Log.d("dummy",dummy1);
                                try {
                                    JSONArray jsonArray1 = new JSONArray(dummy1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        responseList.add(jsonArray1.get(2).toString());
                                        Log.d("logs", jsonArray1.get(2).toString());
                                    }
                                }catch (Exception e){

                                }
                            }

                            //////////////////////////////


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
