package karrar.sumerian.android.ui;

//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.Proverbs;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.sumerian.TimeLine;

@SuppressLint("NewApi")
public class TimelineActivity extends AppCompatActivity implements View.OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);

		// First we hide the action bar
		getSupportActionBar().hide();
		setClickables();


		SumerianConverter.prepare(this);
		Proverbs.prepare(this);
		Dictionary.prepare(this);
	}

	private void setClickables() {
		findViewById(R.id.u).setOnClickListener(this);
		findViewById(R.id.pd).setOnClickListener(this);
		findViewById(R.id.a).setOnClickListener(this);
		findViewById(R.id.g).setOnClickListener(this);
		findViewById(R.id.u3).setOnClickListener(this);
		findViewById(R.id.ak).setOnClickListener(this);
		findViewById(R.id.pbe).setOnClickListener(this);
		findViewById(R.id.cd).setOnClickListener(this);
		findViewById(R.id.cd2).setOnClickListener(this);
		findViewById(R.id.sp).setOnClickListener(this);
		findViewById(R.id.nae).setOnClickListener(this);
		findViewById(R.id.nbe).setOnClickListener(this);
		findViewById(R.id.pe).setOnClickListener(this);
		findViewById(R.id.me).setOnClickListener(this);
		findViewById(R.id.se).setOnClickListener(this);
		findViewById(R.id.h).setOnClickListener(this);
		findViewById(R.id.pa).setOnClickListener(this);
		findViewById(R.id.h2).setOnClickListener(this);
		findViewById(R.id.pa2).setOnClickListener(this);
		findViewById(R.id.back).setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.back:
				finish();
				break;
			case R.id.u3:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",0));
				break;
			case R.id.u:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",1));
				break;
			case R.id.sp:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",2));
				break;
			case R.id.se:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",3));
				break;
			case R.id.pe:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",4));
				break;
			case R.id.pd:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",5));
				break;
			case R.id.pbe:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",6));
				break;
			case R.id.pa:
			case R.id.pa2:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",7));
				break;
			case R.id.nbe:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",8));
				break;
			case R.id.nae:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",9));
				break;
			case R.id.me:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",10));
				break;
			case R.id.h:
			case R.id.h2:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",11));
				break;
			case R.id.g:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",12));
				break;
			case R.id.cd:
			case R.id.cd2:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",13));
				break;
			case R.id.a:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",14));
				break;
			case R.id.ak:
				startActivity(new Intent(this,TimelineViewer.class).putExtra("object",15));
				break;


		}
	}
}
