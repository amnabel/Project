package irmc.esprit.tn.irmcmenufinale;



import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ViewInstituteFragment extends Fragment {
    View myView;
    ListView lv;




        String url= "http://5f0863f4.ngrok.io/IRMCJEE-web/IRMC/institute/";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.view_layout,container,false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("response "+response);

                            JSONArray array = new JSONArray(response);
                            System.out.println(array);
                            ArrayList<Institute> items = new ArrayList<>();
                            System.out.println("response1 "+items.toString());

                            for (int i=0; i<array.length();i++){
                                JSONObject obj1 = array.getJSONObject(i);
                                Institute ins = new Institute();
                                ins.setIdInst(obj1.getInt("id_inst"));
                                ins.setName((obj1.getString("name")));
                                ins.setSigle(obj1.getString("sigle"));
                                items.add(ins);
                            }

                           lv =  getActivity().findViewById(R.id.lv_insurence);

                            InstituteAdapter ad = new InstituteAdapter(getActivity().getApplicationContext(),R.layout.activity_affiche_institute,items);

                            System.out.println("reponseeeeee" +items.toString());
                            //lv.setAdapter(ad);
                            lv = (ListView) myView.findViewById(R.id.lv_insurence);
                            lv.setAdapter(ad);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!"+error);
            }
        }) ;

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(stringRequest);



        return myView;
    }
}
