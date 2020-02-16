package karrar.sumerian.android.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import karrar.sumerian.android.R;
import karrar.sumerian.android.ui.views.adapters.KingsItemsAdapter;

public class KingItemActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.king_item);
        // First we hide the action bar
        getSupportActionBar().hide();
        setviews();

        setClickables();

        setList();

    }

    private void setList() {
        try {
            Intent intent = getIntent();
            JSONArray data = new JSONArray(intent.getStringExtra("list"));
            list.setAdapter(new KingsItemsAdapter(this, data));
        }catch (Exception e){
            Toast.makeText(this, "Error loading lists..", Toast.LENGTH_SHORT).show();
        }
    }

    private void setClickables() {

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setviews() {
        list = findViewById(R.id.list);
    }
}
