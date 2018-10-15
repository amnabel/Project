package irmc.esprit.tn.irmcmenufinale;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InstituteAdapter extends ArrayAdapter<Institute> {

    public InstituteAdapter(Context context, int resource, List<Institute> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
       Institute inst = getItem(position);
        if (v == null) {
            final LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.view_layout, null);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences reportingPref = getContext().getSharedPreferences("reporting_app", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = reportingPref.edit();

                    prefEditor.putInt("id_inst", getItem(position).getIdInst());
                    prefEditor.putString("name", getItem(position).getName());
                    prefEditor.putString("sigle",(getItem(position).getSigle()));
                    prefEditor.putString("description",(getItem(position).getDescription()));
                    prefEditor.putString("address",(getItem(position).getAddress()));
                    prefEditor.putString("code_postale",(getItem(position).getCodePostale()));
                    prefEditor.putString("type",(getItem(position).getType()));
                   // prefEditor.putString("sigle",(getItem(position).getLongitude())));
                   // prefEditor.putDouble("sigle",(getItem(position).getLatitude()));


                    prefEditor.commit();

                    Intent i = new Intent(getContext(), affiche.class);
                    getContext().startActivity(i);
                }
            });

        }


        if (inst != null) {

            TextView zone = v.findViewById(R.id.zone);


            zone.setText(inst.getName());


        }


        return v;
    }

}
