package tn.kassebiesprit.sara.irmc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import entities.User;

public class MainActivity extends AppCompatActivity {


    Button bSignUP,back,bSignIN;
    EditText username,password;
     public static User user;
    String url = "http://d5b3d6ca.ngrok.io/IRMCJEE-web/IRMC/user/Check";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSignIN = (Button) findViewById(R.id.bSignIN);
        bSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(MainActivity.this,UserAffichageActivity.class);
                startActivity(intent);
                //  finish();

            }
        });
        bSignUP = (Button) findViewById(R.id.bSignUP);


        username = (EditText) findViewById(R.id.etUserNameLogin);
        password = (EditText) findViewById(R.id.etUserPasswordLogin);




        bSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map = new HashMap<>();
                map.put("username",username.getText().toString());
                map.put("password",password.getText().toString());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(map), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        {
                         //   if (response == null){
                             //   Toast.makeText(MainActivity.this, "Couldn't login ! Pleas try again.", Toast.LENGTH_LONG).show();

                              //  return;
                           // }
                           // user = new Gson().fromJson(response.toString(), new TypeToken<User>() {}.getType());

                            try {
                               user =new User();
                                user.setFirstName(response.getString("firstName"));
                                user.setLastName(response.getString("lastName"));
                                user.setEmail(response.getString("email"));
                                user.setId(response.getInt("id"));
                                user.setUsername(response.getString("username"));
                                System.out.println(user.getEmail());
                                System.out.println(user.getId());
                                 if(user!=null){
                                     Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                                     startActivity(intent);
                                 }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Context context = getApplicationContext();
                        CharSequence text = error.getMessage();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");

                        return headers;
                    }
                };


                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(request);

            }
        }

        );





    }}