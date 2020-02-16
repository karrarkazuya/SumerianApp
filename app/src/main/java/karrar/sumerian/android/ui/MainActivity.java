package karrar.sumerian.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import karrar.sumerian.android.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	//private SlidingMenu SMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_dash);

		// First we hide the action bar
		getSupportActionBar().hide();

		// Registering the buttons
		registerClickables();

		startActivity(new Intent(this, LoadingActivity.class));

	}




	/**
	 * Here we register the clickables/buttons/views
	 */
	private void registerClickables(){
		this.findViewById(R.id.menu).setOnClickListener(this);
		this.findViewById(R.id.dic).setOnClickListener(this);
		this.findViewById(R.id.tra).setOnClickListener(this);
		this.findViewById(R.id.conv).setOnClickListener(this);
		this.findViewById(R.id.proverbs).setOnClickListener(this);
		this.findViewById(R.id.signs).setOnClickListener(this);
		this.findViewById(R.id.kings).setOnClickListener(this);
		this.findViewById(R.id.mythologies).setOnClickListener(this);
		this.findViewById(R.id.cities).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.menu:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
			case R.id.dic:
				startActivity(new Intent(this, DictionaryActivity.class));
				break;
			case R.id.tra:
				startActivity(new Intent(this, TimelineActivity.class));
				break;
			case R.id.conv:
				startActivity(new Intent(this, ConverterActivity.class));
				break;
			case R.id.proverbs:
				startActivity(new Intent(this, ProverbsActivity.class));
				break;
			case R.id.signs:
				startActivity(new Intent(this, SignsListActivity.class));
				break;
			case R.id.kings:
				startActivity(new Intent(this, KingsActivity.class));
				break;
			case R.id.mythologies:
				startActivity(new Intent(this, MythActivity.class));
				break;
			case R.id.cities:
				startActivity(new Intent(this, CitiesActivity.class));
				break;
	}
	}
}
