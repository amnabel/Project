package irmc.esprit.tn.irmc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import irmc.esprit.tn.irmc.model.Event;
import irmc.esprit.tn.irmc.remote.ApiUtils;
import irmc.esprit.tn.irmc.remote.ServiceEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListByUser extends AppCompatActivity {
    ServiceEvent serviceEvent = ApiUtils.getServiceEvent();
    ListView listView;
    Button btngetuserList;
    List<Event> list = new ArrayList<Event>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_by_user);
        btngetuserList = (Button) findViewById(R.id.btngetuserList);
        listView = (ListView) findViewById(R.id.listView);
        btngetuserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get User list
                getUserList();
            }
        });
/*        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this , UserActivity.class);
                intent.putExtra("user_name","");
                intent.putExtra("user_prenom","");
                intent.putExtra("user_salaire","");
                intent.putExtra("user_age","");
                intent.putExtra("user_cin","");
                intent.putExtra("user_mail","");
                intent.putExtra("user_password","");
                startActivity(intent);
            }
        });*/
    }

    public void getUserList(){
        Call<List<Event>> call = serviceEvent.getEventByUser(1);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new EventAdapter(ListByUser.this, R.layout.list_by_user,list));
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("ERROR: " , t.getMessage());
            }
        });
    }
}
