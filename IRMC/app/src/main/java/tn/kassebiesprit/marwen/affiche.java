package irmc.esprit.tn.irmcmenufinale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class affiche extends AppCompatActivity {

    TextView tv_name, tv_sigle,tv_adr,tv_code,tv_desc,tv_type;
    Button delete;
   int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        SharedPreferences reportingPref = getSharedPreferences("reporting_app", MODE_PRIVATE);

        final Institute ins = (Institute)  getIntent().getSerializableExtra("ins");
        tv_name = findViewById(R.id.name);
        tv_sigle = findViewById(R.id.sigle);
        tv_desc = findViewById(R.id.description);
        tv_adr = findViewById(R.id.address);
//        tv_lat = findViewById(R.id.lat);
//        tv_long = findViewById(R.id.lon);
        tv_code = findViewById(R.id.codePostal);
        tv_type= findViewById(R.id.type);

        tv_name.setText(reportingPref.getString("name", "---"));
        tv_sigle.setText(reportingPref.getString("sigle", "---"));
        tv_adr.setText(reportingPref.getString("address", "---"));
        tv_code.setText(reportingPref.getString("code_postale", "---"));
        tv_desc.setText(reportingPref.getString("description", "---"));
       // tv_lat.setText(reportingPref.getFloat("latitude",0));
        //tv_long.setText(reportingPref.getFloat("longitude", 0));
        tv_type.setText(reportingPref.getString("type", "---"));



        id= reportingPref.getInt("id_inst", 0);
        final RequestQueue mRequestQueue = Volley.newRequestQueue(getBaseContext());

        mRequestQueue.start();
        delete=(Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(affiche.this, afficheInstitute.class);
                startActivity(intent);
                String url = "http://5f0863f4.ngrok.io/IRMCJEE-web/IRMC/institute/"+id;
                StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getBaseContext() , "Tow Deleted",Toast.LENGTH_SHORT).show();
                            }

                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getBaseContext() , "Tow already deleted",Toast.LENGTH_SHORT).show();

                            }
                        }
                );


                mRequestQueue.add(dr);
            }
        });

    }
}
