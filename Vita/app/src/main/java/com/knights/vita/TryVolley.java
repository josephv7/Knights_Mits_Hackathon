package com.knights.vita;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by josephvarghese on 18/03/18.
 */

public class TryVolley {


//    public void fetchData(final DataCallback callback) {
//        String url = "your-url-here";
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(tag, response.toString());
//
//                        try {
//                            callback.onSuccess(response);
//                        } catch (JSONException e) {
//                            Log.e(tag, e.getMessage(), e);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.d(tag, "Error: " + error.getMessage());
//                    }
//                });
//
//        NetworkController.getInstance().addToRequestQueue(jsonObjectRequest);
//    }
}
