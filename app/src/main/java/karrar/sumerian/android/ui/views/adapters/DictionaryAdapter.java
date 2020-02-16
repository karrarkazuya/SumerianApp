package karrar.sumerian.android.ui.views.adapters;

import karrar.sumerian.android.R;
import karrar.sumerian.android.ui.views.dialogs.DictionaryItemViewer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class DictionaryAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final JSONArray jsonobject;

	public DictionaryAdapter(Activity context, JSONArray object) {
		super(context, R.layout.menuitem);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.jsonobject=object;

	}
	
	public View getView(int position,View view,ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.menuitem, null,true);

		TextView sumerian = rowView.findViewById(R.id.sumerian);
		TextView meaning = rowView.findViewById(R.id.meaning);
		TextView meaning_key = rowView.findViewById(R.id.meaning_key);
		TextView id = rowView.findViewById(R.id.theid);
		try {
			final JSONObject item = jsonobject.getJSONObject(position);
			sumerian.setText(item.getString("sum_key"));
			JSONArray list = item.getJSONArray("meaning_word");
			String result = "";
			for (int i = 0; i < list.length(); i++) {
				result = result + list.get(i)+", ";
			}
			result = result.substring(0,result.length()-2);
			meaning.setText(result);
			id.setText(item.getString("id"));
			meaning_key.setText(item.getString("meaning_key"));
			rowView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(context, DictionaryItemViewer.class);
					i.putExtra("json_object", item.toString());
					context.startActivity(i);
				}
			});
		}catch (Exception e){
			// Error goes in here
		}

		return rowView;
		
	};


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jsonobject.length();
	}

}