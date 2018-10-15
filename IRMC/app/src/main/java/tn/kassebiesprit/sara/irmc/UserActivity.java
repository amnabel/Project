package tn.kassebiesprit.sara.irmc;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import entities.User;

public class UserActivity {

    private ArrayList<User> itemList;
   private static final String TAG = UserActivity.class.getSimpleName();
    private static final String URL = "http://d5b3d6ca.ngrok.io/IRMCJEE-web/IRMC/user";


    private void fetchUserItems() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {

                            return;
                        }

                        List<User> items = new Gson().fromJson(response.toString(), new TypeToken<List<User>>() {
                        }.getType());

                        itemList.clear();
                        itemList.addAll(items);
                        System.out.println("hhhhhhhh"+itemList);
                        // refreshing recycler view

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());

            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }
}
