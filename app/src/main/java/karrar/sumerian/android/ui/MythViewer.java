package karrar.sumerian.android.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Mythologies;
import karrar.sumerian.android.sumerian.Signs;

public class MythViewer extends AppCompatActivity {

    private TextView title;
    private TextView title2;
    private TextView type;
    private TextView name_and_spellings;
    private TextView cult_place;
    private TextView time_periods_attested;
    private TextView divine_genealogy;
    private TextView functions;
    private TextView myth_info;
    private ImageView image;
    private JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myth_viewer);
        getSupportActionBar().hide();
        setViews();

        Intent i = getIntent();
        int id = i.getIntExtra("pos", 0);

        data = Mythologies.getById(id);

        setResults();

        registerClickables();
    }

    private void registerClickables() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MythViewer.this);
                builder.setTitle(getString(R.string.more));
                try {
                    builder.setMessage(Mythologies.reference);
                }catch (Exception e){
                    builder.setMessage("error getting info");
                }




                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setResults() {
        try{
            title.setText(data.getString("title"));
            title2.setText(data.getString("title"));
            type.setText(data.getString("type"));
            name_and_spellings.setText(data.getString("name_and_spellings"));
            cult_place.setText(data.getString("cult_place"));
            time_periods_attested.setText(data.getString("time_periods_attested"));
            divine_genealogy.setText(data.getString("divine_genealogy_and_syncretisms"));
            functions.setText(data.getString("functions"));
            myth_info.setText(data.getString("info"));

            String img = data.getString("image");
            if(!img.equals("")) {
                try {
                    // get input stream
                    InputStream ims = getAssets().open("images/" + img);
                    // load image as Drawable
                    Drawable d = Drawable.createFromStream(ims, null);
                    // set image to ImageView
                    image.setImageDrawable(d);
                    ims.close();
                } catch (IOException ex) {
                    image.setVisibility(View.GONE);
                }
            }else{
                image.setVisibility(View.GONE);
            }
        }catch (Exception e){

        }
    }

    private void setViews() {
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        title2 = findViewById(R.id.title2);
        type = findViewById(R.id.type);
        name_and_spellings = findViewById(R.id.name_and_spellings);
        cult_place = findViewById(R.id.cult_place);
        time_periods_attested = findViewById(R.id.time_periods_attested);
        divine_genealogy = findViewById(R.id.divine_genealogy);
        functions = findViewById(R.id.functions);
        myth_info = findViewById(R.id.myth_info);
    }
}
