package karrar.sumerian.android.ui.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.ui.KingItemActivity;
import karrar.sumerian.android.ui.views.dialogs.DictionaryItemViewer;

public class KingsAdapter  extends ArrayAdapter<JSONArray> {

    private final Activity context;
    private final JSONArray jsonobjects;

    public KingsAdapter(Activity context, JSONArray objects) {
        super(context, R.layout.kings_list_item);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.jsonobjects = objects;

    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.kings_list_item, null,true);

        TextView title = rowView.findViewById(R.id.title);
        TextView info = rowView.findViewById(R.id.info);


        try {
            final JSONObject item = jsonobjects.getJSONObject(position);
            String titleStr = item.getString("title");
            String infoStr = item.getString("info");

            if(titleStr != null && !titleStr.equals(""))
                title.setText(titleStr);
            else
                title.setVisibility(View.GONE);

            if(infoStr != null && !infoStr.equals(""))
                info.setText(infoStr);
            else
                info.setVisibility(View.GONE);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(context, KingItemActivity.class);
                        i.putExtra("list", item.getJSONArray("kings").toString());
                        context.startActivity(i);
                    }catch (Exception e){

                    }

                }
            });
        }catch (Exception e){
            // Error goes in here
        }

        return rowView;

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return jsonobjects.length();
    }

}
