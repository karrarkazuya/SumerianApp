package karrar.sumerian.android.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.Proverbs;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;

public class ProverbsActivity extends Activity implements OnClickListener {

	private String copytext;
	private TextView proverb_converted;
	private TextView proverb_sumerian;
	private TextView proverb;
	private TextView source;
	private DrawerLayout mDrawerLayout;
	private int size = 5;
	private String pickedColor = "#000000";
	private TextView sizeView;
	private RelativeLayout colorView;
	private JSONObject item = null;
	private LinearLayout proverb_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proverbs);

		// We set the views
		setViews();
		registerClickables();
		setListners();
		// Setting the sliding menu
		mDrawerLayout.openDrawer(Gravity.END);

		SumerianConverter.prepare(this);
		Proverbs.prepare(this);
		Dictionary.prepare(this);


		proverb_converted.setTextColor(Color.parseColor(pickedColor));
		proverb_sumerian.setTextColor(Color.parseColor(pickedColor));
		proverb_converted.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
		proverb_sumerian.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
		proverb.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
		source.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
	}

	/**
	 * Here we register the clickables for the click switch
	 */
	private void registerClickables() {
		findViewById(R.id.copybtn).setOnClickListener(this);
		findViewById(R.id.nextbtn).setOnClickListener(this);
		findViewById(R.id.backbtn).setOnClickListener(this);
		findViewById(R.id.menu).setOnClickListener(this);
		findViewById(R.id.nag).setOnClickListener(this);
		findViewById(R.id.plu).setOnClickListener(this);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.Button01).setOnClickListener(this);
		findViewById(R.id.Button02).setOnClickListener(this);
	}


	/**
	 * Here we set the views from the res
	 */
	private void setViews() {
		proverb_converted = findViewById(R.id.proverb_converted);
		proverb_converted = SumerianConverter.setCuneiformFont(this, proverb_converted);
		proverb_sumerian = findViewById(R.id.proverb_sumerian);
		proverb = findViewById(R.id.proverb);
		source = findViewById(R.id.source);
		mDrawerLayout = findViewById(R.id.drawer);
		sizeView = findViewById(R.id.textView2);
		colorView = findViewById(R.id.colorview);
		sizeView.setText(size+"");
		colorView.getBackground().setColorFilter(0xFF000000, PorterDuff.Mode.MULTIPLY);
		proverb_layout = findViewById(R.id.proverb_layout);
	}



	private void setListners(){
		if (mDrawerLayout != null) {
			mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
				@Override
				public void onDrawerSlide(View view, float v) {

				}

				@Override
				public void onDrawerOpened(View view) {

				}

				@Override
				public void onDrawerClosed(View view) {
					if(item == null)
						randomProverb();
				}

				@Override
				public void onDrawerStateChanged(int i) {

				}
			});
		}
	}



	/**
	 * Set the text into the webview
	 */
	private void randomProverb(){
		// To reduce the slowness
		try {
			item = Proverbs.getRandomProverb();
			if(item != null){
				if(proverb_layout.getVisibility() == View.GONE)
					proverb_layout.setVisibility(View.VISIBLE);
				convertToSumerian(Proverbs.Clean(item.getString("sumerian"), false));
				proverb_sumerian.setText(Proverbs.Clean(item.getString("sumerian"), true));
				String meaning = Proverbs.Clean(item.getString("english"), true);
				proverb.setText(meaning);
				source.setText(item.getString("source"));
				copytext = proverb_converted.getText().toString()+"\n"+proverb_sumerian.getText().toString()+"\n"+proverb.getText().toString();
			}else{
				randomProverb();
			}
		} catch (Exception e) {
			Toast.makeText(this, "Oops! this is not meant to happen.. it is an error please contact the developer", Toast.LENGTH_LONG).show();
		}
	}


	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.copybtn:
				try {
					int sdk = android.os.Build.VERSION.SDK_INT;
					Toast.makeText(ProverbsActivity.this, "Copied", Toast.LENGTH_LONG).show();
					copytext = copytext.replace("</br>", "\n").replace("<br>", "\n");
					if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
						android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						clipboard.setText(copytext);
					} else {
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("textlabel1", copytext);
						clipboard.setPrimaryClip(clip);
					}
				}catch(Exception e){
					Toast.makeText(ProverbsActivity.this, "Unable to copy", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.nextbtn:
				randomProverb();
				break;
			case R.id.backbtn:
				finish();
				break;
			case R.id.menu:
				mDrawerLayout.openDrawer(Gravity.END);
				break;

			case R.id.nag:
				if(size != 1){
					size = size - 1;
					sizeView.setText(size+"");
					proverb_converted.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
					proverb_sumerian.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
					proverb.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
					source.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
				}
				break;
			case R.id.plu:
				if(size != 18){
					size = size + 1;
					sizeView.setText(size+"");
					proverb_converted.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
					proverb_sumerian.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
					proverb.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
					source.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
				}
				break;
			case R.id.button1:
				pickedColor = "#000000";
				colorView.setBackgroundResource(R.color.black);
				proverb_converted.setTextColor(Color.parseColor(pickedColor));
				proverb_sumerian.setTextColor(Color.parseColor(pickedColor));
				break;
			case R.id.button2:
				pickedColor = "#0033cc";
				colorView.setBackgroundResource(R.color.azrag);
				proverb_converted.setTextColor(Color.parseColor(pickedColor));
				proverb_sumerian.setTextColor(Color.parseColor(pickedColor));
				break;
			case R.id.button3:
				pickedColor = "#009900";
				colorView.setBackgroundResource(R.color.green);
				proverb_converted.setTextColor(Color.parseColor(pickedColor));
				proverb_sumerian.setTextColor(Color.parseColor(pickedColor));
				break;
			case R.id.Button01:
				pickedColor = "#ff0066";
				colorView.setBackgroundResource(R.color.red);
				proverb_converted.setTextColor(Color.parseColor(pickedColor));
				proverb_sumerian.setTextColor(Color.parseColor(pickedColor));
				break;
			case R.id.Button02:
				pickedColor = "#ffff00";
				colorView.setBackgroundResource(R.color.yellow);
				proverb_converted.setTextColor(Color.parseColor(pickedColor));
				proverb_sumerian.setTextColor(Color.parseColor(pickedColor));
				break;
		}
	}


	/**
	 * To show readable sumerian font
	 * @param text
	 */
	private void convertToSumerian(String text) {
			text = SumerianConverter.convertToSumerian(text);

			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
				proverb_converted.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
			else
				proverb_converted.setText(Html.fromHtml(text));

	}



	@Override
	protected void onStart()
	{
		super.onStart();
	}


	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}
}
