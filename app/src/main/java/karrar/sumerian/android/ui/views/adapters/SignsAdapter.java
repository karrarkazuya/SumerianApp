package karrar.sumerian.android.ui.views.adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.SumerianConverter;

public class SignsAdapter extends BaseAdapter {
    private Context mContext;
    // references to our images
    private String[] signImages;

    /*= {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
    */

    public SignsAdapter(Context c, String[] signImages) {
        mContext = c;
        this.signImages = signImages;
    }

    public int getCount() {
        return signImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {


        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 5, 10, 5);
            imageView.setLayoutParams(lp);
        } else {
            imageView = (ImageView) convertView;
        }

        try
        {
            // get input stream
            InputStream ims = mContext.getAssets().open("images/signs/"+signImages[position]);
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);

            int h = d.getIntrinsicHeight();
            int w = d.getIntrinsicWidth();
            h = h * 5;
            w = w * 5;
            /*
            if(h>w){
                h = 150;
                w = 100;
            }else if(w > h){
                w = 150;
                h = 100;
            }else{
                w = 150;
                h = 150;
            }
            */

            imageView.setLayoutParams(new ViewGroup.LayoutParams(w, h));
            // set image to ImageView
            imageView.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
        }

        return imageView;


        /*

        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setPadding(10, 10, 10, 10);
            textView.setTextColor(R.color.black);
            textView.setTextSize(25);
            textView = SumerianConverter.setCuneiformFont(mContext, textView);
        } else {
            textView = (TextView) convertView;
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            textView.setText(Html.fromHtml(signImages[position], Html.FROM_HTML_MODE_LEGACY));
        else
            textView.setText(Html.fromHtml(signImages[position]));

        return textView;
        */

    }



}