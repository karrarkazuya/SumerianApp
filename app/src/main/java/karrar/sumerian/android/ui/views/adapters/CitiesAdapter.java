package karrar.sumerian.android.ui.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import karrar.sumerian.android.R;
import karrar.sumerian.android.ui.CitiesViewer;
import karrar.sumerian.android.ui.MythViewer;

public class CitiesAdapter  extends ArrayAdapter<JSONArray> {

    private final Activity context;
    private final JSONArray jsonobjects;

    public CitiesAdapter(Activity context, JSONArray objects) {
        super(context, R.layout.myth_item);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.jsonobjects = objects;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.myth_item, null,true);

        TextView title = rowView.findViewById(R.id.title);
        TextView type = rowView.findViewById(R.id.type);

        try {
            final JSONObject item = jsonobjects.getJSONObject(position);
            String titleStr = item.getString("title");
            String typeStr = item.getString("type");

            if(titleStr != null && !titleStr.equals(""))
                title.setText(titleStr);
            else
                title.setVisibility(View.GONE);

            if(typeStr != null && !typeStr.equals("")){
                type.setText(typeStr);
            } else {
                LinearLayout layout = (LinearLayout) type.getParent();
                layout.setVisibility(View.GONE);
            }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity((new Intent(context, CitiesViewer.class).putExtra("pos", position)));
                }
            });

        }catch (Exception e){
            title.setText(e.getMessage());
        }

        return rowView;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return jsonobjects.length();
    }
}
