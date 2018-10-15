package tn.kassebiesprit.sara.irmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import entities.User;

public class ProfileActivity extends AppCompatActivity {
    User user = MainActivity.user;
    Button bDelete;
    String url = "http://d5b3d6ca.ngrok.io/IRMCJEE-web/IRMC/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView mTextView = (TextView) findViewById(R.id.lastname);
        mTextView.setText(user.getLastName());

        TextView mTextView1 = (TextView) findViewById(R.id.category);
        mTextView1.setText(user.getFirstName());

        TextView mTextView2 = (TextView) findViewById(R.id.author);
        mTextView2.setText(user.getEmail());


        TextView mTextView3 = (TextView) findViewById(R.id.category1);
        mTextView3.setText(user.getUsername());

        TextView mTextView4 = (TextView) findViewById(R.id.datepublish1);
        mTextView4.setText(user.getPassword());


        bDelete = (Button) findViewById(R.id.delete);

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject j = new JSONObject();
                try {
                    j.put("name", "anything");
                } catch (JSONException e) {

                    e.printStackTrace();
                }
                StringRequest js = new StringRequest(Request.Method.DELETE, "http://d5b3d6ca.ngrok.io/IRMCJEE-web/IRMC/user/"+user.getId(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ProfileActivity.this, response, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("Content-type", "application/json; charset=utf-8");
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(js);

            }
        });
    }
}