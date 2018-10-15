package irmc.esprit.tn.irmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.app.DatePickerDialog;

import irmc.esprit.tn.irmc.model.Category;
import irmc.esprit.tn.irmc.model.Event;
import irmc.esprit.tn.irmc.remote.ApiUtils;
import irmc.esprit.tn.irmc.remote.ServiceEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    ServiceEvent serviceEvent =ApiUtils.getServiceEvent();
          EditText edprenom;
          EditText edtUsername;
          EditText edmail;
    ListView listView;
    List<Event> list = new ArrayList<Event>();
    EditText startDatePicker,endDatePicker;
         // DatePicker startDatePicker,endDatePicker;
          Spinner spinner;
       //   SimpleDateFormat dateFormatter ;
          Button btnSave,btnGo;
   // Date d ;
    String entered_dob ;
    String entered_dob1 ;
   // SimpleDateFormat dateFormatter1 ;
  //  Date d1 ;
   Calendar mycal1 = Calendar.getInstance();
    Calendar mycal2 = Calendar.getInstance();
    Context c;
    Date currentTime = Calendar.getInstance().getTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        //edtUId = (EditText) findViewById(R.id.edtUId);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edprenom = (EditText) findViewById(R.id.edprenom);
        edmail = (EditText) findViewById(R.id.edmail);
        startDatePicker = (EditText) findViewById(R.id.startDatePicker);
        endDatePicker = (EditText) findViewById(R.id.endDatePicker);
      //  startDatePicker= (DatePicker) findViewById(R.id.startDatePicker);
       // endDatePicker= (DatePicker) findViewById(R.id.endDatePicker);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnGo= (Button) findViewById(R.id.btnGo);
        mySpinner.setAdapter(new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, Category.values()));
        // txtUId = (TextView) findViewById(R.id.txtUId);

      //  serviceEvent = ApiUtils.getServiceEvent();

//        edtUId.setText(userId);
        // edtUsername.setText(userName);
        //edprenom.setText(userPrenom);
   /*     int day = startDatePicker.getDayOfMonth();
        int month = startDatePicker.getMonth();
        int year = startDatePicker.getYear()-1900;*/
/*
        d = new Date(year, month, day);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        entered_dob = dateFormatter.format(d);
////

        int day1 = endDatePicker.getDayOfMonth();
        int month1 = endDatePicker.getMonth();
        int year1 = endDatePicker.getYear()-1900;

        d1 = new Date(year1, month1, day1);
        dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
        entered_dob1 = dateFormatter1.format(d1);*/
        // it make it listen to to the user's click


        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,date, mycal1.get(Calendar.YEAR), mycal1.get(Calendar.MONTH), mycal1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,date2, mycal2.get(Calendar.YEAR), mycal1.get(Calendar.MONTH), mycal1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event u = new Event() ;
                u.setTitle(edprenom.getText().toString());
                u.setDescription(edtUsername.getText().toString());
                u.setCapacity(Integer.parseInt(edmail.getText().toString()));

                u.setStartDate(startDatePicker.getText().toString());
                u.setEndDate(endDatePicker.getText().toString());
              //   u.setCreationDate(currentTime.toString());
             //  u.setStartDate(startDatePicker.toString());
             //   u.setStartDate(entered_dob);
               // u.setEndDate(endDatePicker.toString());
              // u.setEndDate(entered_dob1);
        //       u.setCat(mySpinner.);
                addEvent(u);

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserList();
            }
        });
    }

    public void addEvent (Event u){
        Call<Event> call = serviceEvent.addEvent(u);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Event Created successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

                Log.e("ERROR",t.getMessage());
            }
        });

    }
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mycal1.set(Calendar.YEAR, year);
            mycal1.set(Calendar.MONTH, month);
            mycal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String format = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            startDatePicker.setText(simpleDateFormat.format(mycal1.getTime()));

        }
    };

    final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mycal2.set(Calendar.YEAR, year);
            mycal2.set(Calendar.MONTH, month);
            mycal2.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String format = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            endDatePicker.setText(simpleDateFormat.format(mycal2.getTime()));

        }
    };
    public void getUserList(){
        Call<List<Event>> call = serviceEvent.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new EventAdapter(MainActivity.this, R.layout.activity_list_event,list));
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("ERROR: " , t.getMessage());
            }
        });
    }
}


