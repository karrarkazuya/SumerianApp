package karrar.sumerian.android.ui.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.ui.views.dialogs.DictionaryItemViewer;

public class SignsViewerAdapter extends ArrayAdapter<String> {


    private final Activity context;
    private final JSONArray jsonobjects;

    public SignsViewerAdapter(Activity context, JSONArray objects) {
        super(context, R.layout.list_view_item);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.jsonobjects = objects;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_item, null,true);

        TextView sign = rowView.findViewById(R.id.sign);
        TextView sign_name = rowView.findViewById(R.id.sign_name);
        TextView value = rowView.findViewById(R.id.value);

        sign = SumerianConverter.setCuneiformFont(context, sign);
        try {
            final JSONObject item = jsonobjects.getJSONObject(position);
            String sign_raw = item.getString("sign_name");
            String converted = SumerianConverter.convertToSumerian(sign_raw);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                sign.setText(Html.fromHtml(converted, Html.FROM_HTML_MODE_LEGACY));
            else
                sign.setText(Html.fromHtml(converted));

            sign_name.setText(sign_raw);
            value.setText(item.getString("sign_value"));
        }catch (Exception e){
            // Error goes in here
        }

        return rowView;

    };


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return jsonobjects.length();
    }
}
