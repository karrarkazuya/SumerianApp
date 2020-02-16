package karrar.sumerian.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.TimeLine;

public class TimelineViewer extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private TextView title2;
    private TextView from_to;
    private TextView info;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_viewer);

        // First we hide the action bar
        getSupportActionBar().hide();

        TimeLine.prepare(this);
        Intent i = getIntent();
        int id = i.getIntExtra("object", 0);

        setViewes();
        setResults(id);
        setClickable();
    }




    private void setViewes(){
        title = findViewById(R.id.title);
        title2 = findViewById(R.id.title2);
        from_to = findViewById(R.id.from_to);
        info = findViewById(R.id.info);
        image = findViewById(R.id.image);
    }


    private void setResults(int id) {
        try {
            JSONObject object = TimeLine.getById(id);
            title.setText(object.getString("title"));
            title2.setText(object.getString("title"));
            String from_to_text = object.getString("from")+"\n"+object.getString("to");
            from_to.setText(from_to_text);

            JSONArray list = object.getJSONArray("info");
            String info_str = "";
            for (int i = 0; i < list.length(); i++) {
                if(info_str.equals(""))
                    info_str = list.getString(i);
                else
                    info_str = info_str+"\n\n"+list.getString(i);
            }
            info.setText(info_str);

            String img = object.getString("image");
            if(img.equals("")){
                image.setVisibility(View.GONE);
            }else{
                try
                {
                    // get input stream
                    InputStream ims = getAssets().open("images/"+img);
                    // load image as Drawable
                    Drawable d = Drawable.createFromStream(ims, null);
                    // set image to ImageView
                    image.setImageDrawable(d);
                    ims .close();
                }
                catch(IOException ex)
                {
                    image.setVisibility(View.GONE);
                }
            }

        }catch (Exception e){

        }
    }

    private void setClickable(){
        findViewById(R.id.backbtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backbtn:
                finish();
                break;

        }
    }
}
