package karrar.sumerian.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Mythologies;
import karrar.sumerian.android.ui.views.adapters.MythsAdapter;

public class MythActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myth);
        // First we hide the action bar
        getSupportActionBar().hide();
        Mythologies.prepare(this);

        setViews();
        registerClickables();
    }

    private void setViews(){
        list = findViewById(R.id.list);
    }

    private void registerClickables() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // setting the adapter
        JSONArray data = Mythologies.getList(this);
        if(data != null){
            list.setAdapter(new MythsAdapter(MythActivity.this, data));
        } else {
            Toast.makeText(this, "Error loading lists..", Toast.LENGTH_SHORT).show();
            Mythologies.prepare(this);
        }

    }
}
