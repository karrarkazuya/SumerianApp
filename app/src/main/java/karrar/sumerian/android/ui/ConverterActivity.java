package karrar.sumerian.android.ui;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.Proverbs;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.utls.TinyDB;

public class ConverterActivity extends AppCompatActivity implements OnClickListener {


	private int size = 8;
	private String acolor = "#000000";
	private EditText input;
	private TextView sizeview;
	private RelativeLayout colorview;
	private DrawerLayout mDrawerLayout;
	private TextView sumerian_result;
	private TextView inserted_result;

	private TextView font_1;
	private TextView font_2;
	private TextView font_3;
	private TextView font_4;
	private TextView font_5;
	private TextView font_6;
	private TinyDB tinydb;
	private Button font_saved;
	private String copytext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cuniform_converter);
		// First we hide the action bar
		getSupportActionBar().hide();
		// First we declare the views
		setViews();
		// Then we register the clickables
		setClickables();


		tinydb = new TinyDB(this);
		setValues(tinydb);

		// Setting the sliding menu
		//mDrawerLayout.openDrawer(Gravity.END);

		SumerianConverter.prepare(this);
		Proverbs.prepare(this);
		Dictionary.prepare(this);
	}

	/**
	 * To register the views
	 */
	private void setViews(){
		sumerian_result = findViewById(R.id.sumerian_result);
		sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
		inserted_result = findViewById(R.id.inserted_result);
		input = findViewById(R.id.editText1);
		sizeview = findViewById(R.id.textView2);
		colorview = findViewById(R.id.colorview);
		sizeview.setText(size+"");
		colorview.getBackground().setColorFilter(0xFF000000, Mode.MULTIPLY);

		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);


		font_saved = findViewById(R.id.font_saved);
		font_1 = findViewById(R.id.font_1);
		font_2 = findViewById(R.id.font_2);
		font_3 = findViewById(R.id.font_3);
		font_4 = findViewById(R.id.font_4);
		font_5 = findViewById(R.id.font_5);
		font_6 = findViewById(R.id.font_6);
	}

	/**
	 * Clickable for onclick
	 */
	private void setClickables(){
		findViewById(R.id.convertbtn).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);
		findViewById(R.id.menu).setOnClickListener(this);
		findViewById(R.id.nag).setOnClickListener(this);
		findViewById(R.id.plu).setOnClickListener(this);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		findViewById(R.id.Button01).setOnClickListener(this);
		findViewById(R.id.Button02).setOnClickListener(this);
		findViewById(R.id.copy).setOnClickListener(this);

		font_1.setOnClickListener(this);
		font_2.setOnClickListener(this);
		font_3.setOnClickListener(this);
		font_4.setOnClickListener(this);
		font_5.setOnClickListener(this);
		font_6.setOnClickListener(this);
		font_saved.setOnClickListener(this);
	}



	/**
	 * to set the values
	 * @param tinydb
	 */
	private void setValues(TinyDB tinydb) {


		String font = tinydb.getString("font");
		if(font == null)
			font = "CuneiformComposite";

		switch (font){
			case "CuneiformComposite":
				font_1.setBackgroundColor(R.color.halfprimary);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_1));
				break;

			case "Santakku":
				font_2.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_2));
				break;

			case "SantakkuM":
				font_3.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_3));
				break;

			case "UllikummiA":
				font_4.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_4));
				break;

			case "Assurbanipal":
				font_5.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_5));
				break;
			case "NotoSansCuneiform":
				font_5.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_6));
				break;
			default:
				tinydb.putString("font", "CuneiformComposite");
				font_1.setBackgroundColor(R.color.halfprimary);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_1));
				break;
		}


		try {
			TextView version = findViewById(R.id.version);
			PackageInfo info = this.getPackageManager().getPackageInfo(getPackageName(), 0);
			version.setText(info.versionName);
		}catch (Exception e){

		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.convertbtn:
				// To reduce the slowness
				try {
					hideSoftKeyboard();
					convertToSumerian();
				} catch (Exception e) {
					sumerian_result.setText("Error, unable to convert");
					inserted_result.setText("Contact the developer for a fix.");
				}
				break;
			case R.id.back:
				finish();
				break;
			case R.id.menu:
				mDrawerLayout.openDrawer(Gravity.END);
				break;
			case R.id.nag:
				if(size != 1){
					size = size - 1;
					sizeview.setText(size+"");
					sumerian_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
					inserted_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
				}
				break;
			case R.id.plu:
				if(size != 18){
					size = size + 1;
					sizeview.setText(size+"");
					sumerian_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);
					inserted_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
				}
				break;
			case R.id.button1:
				acolor = "#000000";
				colorview.setBackgroundResource(R.color.black);
				sumerian_result.setTextColor(Color.parseColor(acolor));
				inserted_result.setTextColor(Color.parseColor(acolor));
				break;
			case R.id.button2:
				acolor = "#0033cc";
				colorview.setBackgroundResource(R.color.azrag);
				sumerian_result.setTextColor(Color.parseColor(acolor));
				inserted_result.setTextColor(Color.parseColor(acolor));
				break;
			case R.id.button3:
				acolor = "#009900";
				colorview.setBackgroundResource(R.color.green);
				sumerian_result.setTextColor(Color.parseColor(acolor));
				inserted_result.setTextColor(Color.parseColor(acolor));
				break;
			case R.id.Button01:
				acolor = "#ff0066";
				colorview.setBackgroundResource(R.color.red);
				sumerian_result.setTextColor(Color.parseColor(acolor));
				inserted_result.setTextColor(Color.parseColor(acolor));
				break;
			case R.id.Button02:
				acolor = "#ffff00";
				colorview.setBackgroundResource(R.color.yellow);
				sumerian_result.setTextColor(Color.parseColor(acolor));
				inserted_result.setTextColor(Color.parseColor(acolor));
				break;

			case R.id.font_saved:
				if(this.findViewById(R.id.font_picker).getVisibility() == View.GONE)
					this.findViewById(R.id.font_picker).setVisibility(View.VISIBLE);
				else
					this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.font_1:
				tinydb.putString("font", "CuneiformComposite");
				font_1.setBackgroundColor(R.color.halfprimary);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_1));
				sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
				this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.font_2:
				tinydb.putString("font", "Santakku");
				font_2.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_2));
				sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
				this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.font_3:
				tinydb.putString("font", "SantakkuM");
				font_3.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_3));
				sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
				this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.font_4:
				tinydb.putString("font", "UllikummiA");
				font_4.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_4));
				sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
				this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.font_5:
				tinydb.putString("font", "Assurbanipal");
				font_5.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_6.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_5));
				sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
				this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.font_6:
				tinydb.putString("font", "NotoSansCuneiform");
				font_6.setBackgroundColor(R.color.halfprimary);
				font_1.setBackgroundDrawable(null);
				font_2.setBackgroundDrawable(null);
				font_3.setBackgroundDrawable(null);
				font_4.setBackgroundDrawable(null);
				font_5.setBackgroundDrawable(null);
				font_saved.setText(getString(R.string.font_6));
				sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
				this.findViewById(R.id.font_picker).setVisibility(View.GONE);
				break;

			case R.id.copy:
				try {
					int sdk = android.os.Build.VERSION.SDK_INT;
					Toast.makeText(ConverterActivity.this, "Copied", Toast.LENGTH_LONG).show();
					if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
						android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						clipboard.setText(copytext);
					} else {
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("textlabel1", copytext);
						clipboard.setPrimaryClip(clip);
					}
				}catch(Exception e){
					Toast.makeText(ConverterActivity.this, "Unable to copy", Toast.LENGTH_LONG).show();
				}
				break;
		}
	}

	/**
	 * On click/Convert
	 */
	private void convertToSumerian() {
		String text = input.getText().toString();
		String actul = text;
		actul = actul.replace("c", "š");
		actul = actul.replace("j", "ĝ");




		if(!text.equals(" ") && !text.equals("")) {

			text = SumerianConverter.convertToSumerian(text);

			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
				sumerian_result.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
			else
				sumerian_result.setText(Html.fromHtml(text));
			copytext = sumerian_result.getText().toString();

			sumerian_result.setTextColor(Color.parseColor(acolor));
			sumerian_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*5);

			inserted_result.setTextColor(Color.parseColor(acolor));
			inserted_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size*3);
			inserted_result.setText(actul);

		}
	}

	public void hideSoftKeyboard() {
		InputMethodManager ip = (InputMethodManager) ConverterActivity.this.getSystemService(
						Activity.INPUT_METHOD_SERVICE);
		if(ip != null)
		ip.hideSoftInputFromWindow(ConverterActivity.this.getCurrentFocus().getWindowToken(), 0);
	}


}
