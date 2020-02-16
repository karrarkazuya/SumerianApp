package karrar.sumerian.android.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.Proverbs;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.ui.views.adapters.SignsAdapter;

public class SignsListActivity extends AppCompatActivity {

	private GridView gridview;
	private String[] signsImages;
	private View infoBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signs_list);

		// First we hide the action bar
		getSupportActionBar().hide();

		SumerianConverter.prepare(this);
		Proverbs.prepare(this);
		Dictionary.prepare(this);
		Signs.prepare(this);
		Signs.prepareSigns();

		ArrayList<String> signs = Signs.preparedSigns;

		signsImages = signs.toArray(new String[0]);

		setViews();
		registerAdapters();
	}

	private void setViews(){
		gridview = findViewById(R.id.gridview);
		infoBtn = findViewById(R.id.info);

	}

	private void registerAdapters() {
		// setting the adapter
		if(signsImages != null){
			gridview.setAdapter(new SignsAdapter(this, signsImages));
		} else {
			Toast.makeText(this, "Error loading lists..", Toast.LENGTH_SHORT).show();
			Signs.prepare(this);
		}


		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
									int position, long id) {
				Intent i = new Intent(SignsListActivity.this, SignViewerActivity.class);
				i.putExtra("sign", signsImages[position]);
				startActivity(i);
			}
		});

		findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		infoBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder builder = new AlertDialog.Builder(SignsListActivity.this);

				builder.setTitle(getString(R.string.more));

				String message = "";

				try {
					message = message + "Info:\n";
					JSONArray info = Signs.infoArray;
					for (int i = 0; i < info.length(); i++) {
						message = message + info.getString(i) + "\n";
					}


					message = message + "\n\nReference:\n";
					JSONArray ref = Signs.referencesArray;
					for (int i = 0; i < ref.length(); i++) {
						message = message + ref.getString(i) + "\n";
					}
					builder.setMessage(message);
				}catch (Exception e){
					builder.setMessage("error getting info");
				}




				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});

	}

}
