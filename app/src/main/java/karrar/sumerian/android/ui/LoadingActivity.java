package karrar.sumerian.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Cities;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.Mythologies;
import karrar.sumerian.android.sumerian.Proverbs;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.utls.TinyDB;

public class LoadingActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		// First we hide the action bar
		getSupportActionBar().hide();

		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				// here we load everything we need
				String language = (new TinyDB(LoadingActivity.this)).getString("app_language");
				if(language == null)
					(new TinyDB(LoadingActivity.this)).putString("app_language", "en");

				SumerianConverter.prepare(LoadingActivity.this);
				Proverbs.prepare(LoadingActivity.this);
				Dictionary.prepare(LoadingActivity.this);
				Mythologies.prepare(LoadingActivity.this);

				// prepare the json list
				Cities.prepare(LoadingActivity.this);

				LoadingActivity.this.finish();
			}
		}, 500);


	}

}
