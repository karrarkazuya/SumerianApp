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
import karrar.sumerian.android.ui.views.dialogs.DictionaryItemViewer;

    public class KingsItemsAdapter  extends ArrayAdapter<JSONArray> {

    private final Activity context;
    private final JSONArray jsonobjects;

    public KingsItemsAdapter(Activity context, JSONArray objects) {
        super(context, R.layout.king_sub_item);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.jsonobjects = objects;

    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.king_sub_item, null,true);

        TextView ruler = rowView.findViewById(R.id.ruler);
        TextView epithet = rowView.findViewById(R.id.epithet);
        TextView reign_length = rowView.findViewById(R.id.reign_length);
        TextView approx_dates = rowView.findViewById(R.id.approx_dates);
        TextView more_info = rowView.findViewById(R.id.more_info);
        TextView extra_info = rowView.findViewById(R.id.extra_info);

        try {
            final JSONObject item = jsonobjects.getJSONObject(position);

            String rulerStr = null;
            String epithetStr = null;
            String lengthOfReignStr = null;
            String approxDatesStr = null;
            String moreInfoStr = null;
            String extraInfoStr = null;

            if(item.has("ruler"))
                rulerStr = item.getString("ruler");
            if(item.has("epithet"))
                epithetStr = item.getString("epithet");

            if(item.has("length_of_reign"))
                lengthOfReignStr = item.getString("length_of_reign");

            if(item.has("approx_dates"))
                approxDatesStr = item.getString("approx_dates");

            if(item.has("more_info"))
                moreInfoStr = item.getString("more_info");

            if(item.has("extra_info"))
                extraInfoStr = item.getString("extra_info");

            if(rulerStr != null) {

                ruler.setText(rulerStr);

                if (epithetStr != null && !epithetStr.equals(""))
                    epithet.setText(epithetStr);
                else
                    rowView.findViewById(R.id.epithet_layout).setVisibility(View.GONE);

                if (lengthOfReignStr != null && !lengthOfReignStr.equals(""))
                    reign_length.setText(lengthOfReignStr);
                else
                    rowView.findViewById(R.id.reign_length_layout).setVisibility(View.GONE);

                if (approxDatesStr != null && !approxDatesStr.equals(""))
                    approx_dates.setText(approxDatesStr);
                else
                    rowView.findViewById(R.id.approx_dates_layout).setVisibility(View.GONE);

                if (moreInfoStr != null && !moreInfoStr.equals(""))
                    more_info.setText(moreInfoStr);
                else
                    rowView.findViewById(R.id.more_info_layout).setVisibility(View.GONE);

            }else{
                rowView.findViewById(R.id.ruler_info_layout).setVisibility(View.GONE);
            }

            if(extraInfoStr != null && !extraInfoStr.equals("")){
                extra_info.setText(extraInfoStr);
            }else{
                extra_info.setVisibility(View.GONE);
            }

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
