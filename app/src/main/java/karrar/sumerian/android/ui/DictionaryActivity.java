package karrar.sumerian.android.ui;


import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.Proverbs;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.ui.views.adapters.DictionaryAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.json.JSONArray;

public class DictionaryActivity extends AppCompatActivity implements OnClickListener {


	private EditText input;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dict);


		// First we hide the action bar
		getSupportActionBar().hide();
		// To load the dictionary into the ram
		Dictionary.prepare(this);

        // First we set the views
		setViews();
		// The we set clickables
		setClickables();

		// here we check if it is from key button in item viewer
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			String key = extras.getString("key");
			JSONArray results = Dictionary.search(key, true);
			if(results != null){
				listView.setAdapter(new DictionaryAdapter(this,results));
			}else{
				//Toast.makeText(this, getString(R.string.dict_no_results), Toast.LENGTH_SHORT).show();
			}
		}

		SumerianConverter.prepare(this);
		Proverbs.prepare(this);
		Dictionary.prepare(this);
}

	/**
	 * We register the clickables to be used in the onclick switch
	 */
	private void setClickables() {
		findViewById(R.id.button1).setOnClickListener(this);
	}

	/**
	 * Here we register the views to be used across all the class
	 */
	private void setViews() {
		listView = findViewById(R.id.wordslist);
		input = findViewById(R.id.editText1);
		input.setImeActionLabel(getString(R.string.search), KeyEvent.KEYCODE_ENTER);
		enterButtonClickable();
	}

	/**
	 * For the button when clicked
	 */
	private void search(){
		String inputstr = input.getText().toString();
		if(!inputstr.equals(null) && !inputstr.equals("") && !inputstr.equals(" ")){
			JSONArray results = Dictionary.search(inputstr, false);
			if(results != null){
				listView.setAdapter(new DictionaryAdapter(this,results));
			}else{
				Toast.makeText(this, getString(R.string.dict_no_results), Toast.LENGTH_SHORT).show();
			}
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.button1:
				try {
					hideSoftKeyboard(this);
					search();
				} catch (Exception e) {
					Log.w("error",e.getMessage());
				}
				break;
		}
	}

	/**
	 * To hide the software keyboard on click
	 * @param activity
	 */
	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager =
				(InputMethodManager) activity.getSystemService(
						Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(
				activity.getCurrentFocus().getWindowToken(), 0);
	}

	private void enterButtonClickable(){
		input.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
						(keyCode == KeyEvent.KEYCODE_ENTER)) {
					hideSoftKeyboard(DictionaryActivity.this);
					search();
					return true;
				}
				return false;
			}
		});
	}
}
