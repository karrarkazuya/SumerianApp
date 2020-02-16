package karrar.sumerian.android.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Signs;
import karrar.sumerian.android.sumerian.SumerianConverter;
import karrar.sumerian.android.ui.views.adapters.SignsViewerAdapter;

public class SignViewerActivity extends AppCompatActivity {

    private TextView sign_tv;
    private ImageView sign_im;
    private ListView list;
    private TextView sign_im_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_viewer);
        // First we hide the action bar
        getSupportActionBar().hide();

        Intent i = getIntent();
        String sign = i.getStringExtra("sign");

        setViews();

        setResults(sign);
    }

    private void setResults(String sign) {
        try
        {
            // get input stream
            InputStream ims = this.getAssets().open("images/signs/"+sign);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            sign_im.setImageDrawable(d);
            ims.close();
        }
        catch(IOException ex)
        {
        }

        list.setAdapter(new SignsViewerAdapter(this, Signs.FindSignSimilars(sign)));

        sign = sign.replace(".png", "");
        sign = sign.replace("_", " ");
        String signConverted = SumerianConverter.convertToSumerian(sign);

        //if(signConverted.contains("&#x")) {
        if(signConverted != null) {
            sign_im_tv = SumerianConverter.setCuneiformFont(this, sign_im_tv);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                sign_im_tv.setText(Html.fromHtml(signConverted, Html.FROM_HTML_MODE_LEGACY));
            else
                sign_im_tv.setText(Html.fromHtml(signConverted));
            sign_im.setVisibility(View.GONE);
        }else{
            sign_im_tv.setVisibility(View.GONE);
        }
        sign_tv.setText(sign);

    }

    private void setViews() {
        sign_tv = findViewById(R.id.sign_name);
        sign_im_tv = findViewById(R.id.sign_text);
        sign_im = findViewById(R.id.sign_image);
        list = findViewById(R.id.list);

        findViewById(R.id.backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
