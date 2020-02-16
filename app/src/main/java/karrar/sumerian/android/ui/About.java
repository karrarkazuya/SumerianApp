package karrar.sumerian.android.ui;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import karrar.sumerian.android.R;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		try {


			WebView wv = (WebView) findViewById(R.id.aboutview);
			wv.getSettings().setJavaScriptEnabled(true);

			String lang = Locale.getDefault().getLanguage();

			String mod;
			if (savedInstanceState == null) {
				Bundle extras = getIntent().getExtras();
				if (extras == null) {
					mod = null;
				} else {
					mod = extras.getString("mod");
				}
			} else {
				mod = (String) savedInstanceState.getSerializable("mod");
			}


			if (mod.equals("info")) {
				if (lang.equals("ar")) {
					wv.loadUrl("file:///android_asset/pages/info-ar.html");
				} else {
					wv.loadUrl("file:///android_asset/pages/info.html");
				}
			} else {
				wv.loadUrl("file:///android_asset/pages/about.html");
			}


			// The go to our page button
			findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {


				@Override
				public void onClick(View v) {
					startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/karrar.sattar.5")));
				}

			});


			findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {


				@Override
				public void onClick(View v) {
					finish();
				}

			});
		}catch (Exception e){
			finish();
		}

	}


}
