package irmc.esprit.tn.irmc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import irmc.esprit.tn.irmc.model.Event;

public class EventAdapter extends ArrayAdapter<Event> {
    private Context context;
    private List<Event> events;

    public EventAdapter(@NonNull Context context, int resource, @NonNull List<Event> objects) {
        super(context, resource, objects);
        this.context=context;
        this.events= objects;


    }

    @Override
    public View getView(final int pos, View convertView , ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_event,parent,false);

        TextView txtUserId =(TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername =(TextView) rowView.findViewById(R.id.txtUsername);

       // txtUserId.setText(String.format("Event_id: %d",events.get(pos).getIdEv()));
        txtUsername.setText(String.format("Event_title: %s",events.get(pos).getTitle()));
        txtUserId.setText(String.format("Event_description: %s",events.get(pos).getDescription()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start Activity user form
                Intent intent = new Intent(context , ListEvent.class);
                intent.putExtra("Event_title",events.get(pos).getTitle());
                intent.putExtra("Event_description",String.valueOf(events.get(pos).getDescription()));

                context.startActivity(intent);
            }
        });
        return  rowView;

    }
}
