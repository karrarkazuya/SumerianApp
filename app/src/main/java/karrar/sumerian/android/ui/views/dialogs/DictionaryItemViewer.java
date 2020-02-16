package karrar.sumerian.android.ui.views.dialogs;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import karrar.sumerian.android.R;
import karrar.sumerian.android.ui.DictionaryActivity;
import karrar.sumerian.android.sumerian.SumerianConverter;

public class DictionaryItemViewer extends Activity implements OnInitListener, View.OnClickListener {
	private TextView sumKey;
	private TextView sumWord;
	private TextView engKey;
	private TextView engWord;
	private TextView Akkadian;
	private TextView Source;
	private TextView arabicSpeak;
	private JSONObject JsonObject;
	private TextToSpeech tts;
	private String sum_key;
	private String sum_word;
	private String meaning_key;
	private JSONArray meaning_word;
	private String akkadian;
	private String source;
	private TextView sumerian_result;
	private RelativeLayout backbtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_window);


		// First we set the views
		setViews();
		// Then we set the clickables
		setClickables();


		// Then we get the object
		Intent intent = getIntent();

		// We get the items of the object
		try {
			JsonObject = new JSONObject(intent.getStringExtra("json_object"));
			sum_key = JsonObject.getString("sum_key");
			sum_word = JsonObject.getString("sum_word").replace(","," ");
			meaning_key = JsonObject.getString("meaning_key");
			meaning_word = JsonObject.getJSONArray("meaning_word");
			akkadian = JsonObject.getString("akkadian");
			source = JsonObject.getString("source");
		} catch (Exception e) {
			finish();
		}

		//Then we set the text to speach tool
		tts = new TextToSpeech(this, this);
		tts.setLanguage(Locale.US);



		setResults();

		// Loading the font display files
		convertToSumerian();
	}

	/**
	 * Do we have to do this always?
	 */
	private void setViews() {
		engKey = findViewById(R.id.meaning_key);
		sumWord = findViewById(R.id.sum_word);

		sumKey = findViewById(R.id.sum_key);
		engWord = findViewById(R.id.meaning_word);

		arabicSpeak = findViewById(R.id.arabic_speak);

		Akkadian = findViewById(R.id.akkadian);
		Source = findViewById(R.id.source);

		backbtn = findViewById(R.id.backbtn);


		sumerian_result = findViewById(R.id.sumerian_result);
		// Here we set the sumerian font
		sumerian_result = SumerianConverter.setCuneiformFont(this, sumerian_result);
	}

	/**
	 * For the onclick switch
	 */
	private void setClickables() {
		findViewById(R.id.speak).setOnClickListener(this);
		findViewById(R.id.copy).setOnClickListener(this);
		backbtn.setOnClickListener(this);
		findViewById(R.id.skey).setOnClickListener(this);
		findViewById(R.id.ekey).setOnClickListener(this);
	}

	/**
	 * To set the results into the views
	 */
	private void setResults() {
		sumWord.setText(sum_word);
		String listMeanings = "";
		for (int i = 0; i < meaning_word.length(); i++) {
			try {
				listMeanings = listMeanings + meaning_word.get(i) + "\n";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		engWord.setText(listMeanings);


		if(!meaning_key.equals("")) {
			engKey.setText(meaning_key.toUpperCase());
		}else{
            findViewById(R.id.ekey).setVisibility(View.GONE);
		}

		if(!sum_key.equals("")) {
			sumKey.setText(sum_key.toUpperCase());
		}else{
            findViewById(R.id.skey).setVisibility(View.GONE);
		}



		if(!akkadian.equals("")) {
			Akkadian.setText(akkadian);
		}else{
			findViewById(R.id.akkadian_layout).setVisibility(View.GONE);
		}
		if(source.equals("sl")) {
			Source.setText(getString(R.string.dict_source)+" http://www.sumerian.org/sumerlex.htm");
		}else{
			Source.setText(getString(R.string.dict_source)+" http://psd.museum.upenn.edu");
		}
		arabicSpeak.setText(Arabicform(sum_key.toLowerCase()));

	}


	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

	}


	/**
	 * For the arabic text view readable form
	 * @param text
	 * @return
	 */
	private String Arabicform(String text){

		text = text.replace("gai", "گاي");
		text = text.replace("gae", "گي");
		text = text.replace("pkh", "پخ");
		text = text.replace("kh", "خ");
		text = text.replace("sh", "ش");
		text = text.replace("th", "ز");
		text = text.replace("a", "ا");
		text = text.replace("q", "ق");
		text = text.replace("w", "و");
		text = text.replace("e", "ي");
		text = text.replace("r", "ر");
		text = text.replace("t", "ت");
		text = text.replace("y", "ي");
		text = text.replace("u", "و");
		text = text.replace("i", "ي");
		text = text.replace("o", "و");
		text = text.replace("ph", "پ");
		text = text.replace("p", "پ");
		text = text.replace("s", "س");
		text = text.replace("d", "د");
		text = text.replace("f", "پ");
		text = text.replace("g", "گ");
		text = text.replace("h", "خ");
		text = text.replace("j", "گ");
		text = text.replace("k", "ك");
		text = text.replace("l", "ل");
		text = text.replace("z", "ز");
		text = text.replace("b", "ب");
		text = text.replace("n", "ن");
		text = text.replace("m", "م");
		text = text.replace("ĝ", "غ");
		text = text.replace("š", "ش");
		return text;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.speak:
				tts.speak(readable(sum_key), TextToSpeech.QUEUE_ADD, null);
				break;
			case R.id.backbtn:
				finish();
				break;
			case R.id.skey:
				Intent i = new Intent(DictionaryItemViewer.this, DictionaryActivity.class);
				i.putExtra("key", sum_key);
				startActivity(i);
				break;
			case R.id.ekey:
				Intent j = new Intent(DictionaryItemViewer.this, DictionaryActivity.class);
				j.putExtra("key", meaning_key);
				startActivity(j);
				break;
			case R.id.copy:
				int sdk = android.os.Build.VERSION.SDK_INT;
				Toast.makeText(DictionaryItemViewer.this, "Copied text", Toast.LENGTH_LONG).show();
				if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
					android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					clipboard.setText(sum_word);
				} else {
					android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					android.content.ClipData clip = android.content.ClipData.newPlainText("textlabel1",sum_word);
					clipboard.setPrimaryClip(clip);
				}
				finish();
				break;
		}
	}

	/**
	 * For the text to speech
	 * @param text
	 * @return
	 */
	private String readable(String text){

		text = text.replace("h", "kh").replace("ĝ", "g");
		text = text.replace("š", "sh");
		text = text.replace("th", "z");
		text = text.replace("ph", "pkh");
		text = text.replace("j", "g");
		text = text.replace("ge", "gae");
		text = text.replace("gi", "gai");

		return text;
	}


	/**
	 * To convert to viewable
	 */
	private void convertToSumerian(){
		String sutext = sum_word;
		if(sutext.contains(")") && !sutext.contains("("))
			sutext = sutext.replaceFirst(sutext.substring(0,sutext.indexOf(")")+1),"");
		sutext = SumerianConverter.convertToSumerian(sutext);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
			sumerian_result.setText(Html.fromHtml(sutext, Html.FROM_HTML_MODE_LEGACY));
		else
			sumerian_result.setText(Html.fromHtml(sutext));
	}


}
