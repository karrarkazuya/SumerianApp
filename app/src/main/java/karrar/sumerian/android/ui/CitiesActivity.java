package karrar.sumerian.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Cities;
import karrar.sumerian.android.ui.views.adapters.CitiesAdapter;

public class CitiesActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        getSupportActionBar().hide();
        // prepare the json list
        Cities.prepare(this);

        setViews();

        setClickables();
    }

    private void setClickables() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // setting the adapter
        JSONArray data = Cities.getList(this);
        if(data != null){
            list.setAdapter(new CitiesAdapter(this, data));
        } else {
            Toast.makeText(this, "Error loading lists..", Toast.LENGTH_SHORT).show();
            Cities.prepare(this);
        }
    }

    private void setViews() {
        list = findViewById(R.id.list);
    }
}
