package karrar.sumerian.android.ui;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Kings;
import karrar.sumerian.android.ui.views.adapters.KingsAdapter;

public class KingsActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kings);
        // First we hide the action bar
        getSupportActionBar().hide();

        Kings.prepare(this);

        setViews();
        registerClickables();

        setList();

    }

    private void setViews(){
        list = findViewById(R.id.list);
    }

    private void registerClickables(){
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KingsActivity.this);
                builder.setTitle(getString(R.string.more));

                try {
                    builder.setMessage(Kings.info);
                }catch (Exception e){
                    builder.setMessage("error getting info");
                }




                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setList(){
        // setting the adapter
        JSONArray data = Kings.getList(this);
        if(data != null){
            list.setAdapter(new KingsAdapter(KingsActivity.this, data));
        } else {
            Toast.makeText(this, "Error loading lists..", Toast.LENGTH_SHORT).show();
            Kings.prepare(this);
        }

    }
}
