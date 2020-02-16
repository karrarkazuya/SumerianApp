package karrar.sumerian.android.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Cities;
import karrar.sumerian.android.sumerian.Dictionary;
import karrar.sumerian.android.sumerian.TimeLine;
import karrar.sumerian.android.utls.TinyDB;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lang_1;
    private TextView lang_2;

    private TextView font_1;
    private TextView font_2;
    private TextView font_3;
    private TextView font_4;
    private TextView font_5;
    private TextView font_6;
    private TinyDB tinydb;
    private TextView lang_saved;
    private TextView font_saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        tinydb = new TinyDB(this);
        // First we hide the action bar
        getSupportActionBar().hide();


        // Registering the buttons
        registerViews();
        // Registering the buttons
        registerClickables();

        setValues(tinydb);
    }

    /**
     * to set the values
     * @param tinydb
     */
    private void setValues(TinyDB tinydb) {
        String language = tinydb.getString("app_language");
        if(language == null)
            language = "en";

        switch (language){
            case "en":
                lang_1.setBackgroundColor(R.color.halfprimary);
                lang_2.setBackgroundDrawable(null);
                lang_saved.setText("English");
                break;

            case "ar":
                lang_2.setBackgroundColor(R.color.halfprimary);
                lang_1.setBackgroundDrawable(null);
                lang_saved.setText("العربية");
                break;
                default:
                    tinydb.putString("app_language", "en");
                    lang_1.setBackgroundColor(R.color.halfprimary);
                    lang_2.setBackgroundDrawable(null);
                    lang_saved.setText("English");
                    break;
        }


        String font = tinydb.getString("font");
        if(font == null)
            font = "CuneiformComposite";

        switch (font){
            case "CuneiformComposite":
                font_1.setBackgroundColor(R.color.halfprimary);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_1));
                break;

            case "Santakku":
                font_2.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_2));
                break;

            case "SantakkuM":
                font_3.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_3));
                break;

            case "UllikummiA":
                font_4.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_4));
                break;

            case "Assurbanipal":
                font_5.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_5));
                break;
            case "NotoSansCuneiform":
                font_5.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_6));
                break;
                default:
                    tinydb.putString("font", "CuneiformComposite");
                    font_1.setBackgroundColor(R.color.halfprimary);
                    font_2.setBackgroundDrawable(null);
                    font_3.setBackgroundDrawable(null);
                    font_4.setBackgroundDrawable(null);
                    font_5.setBackgroundDrawable(null);
                    font_6.setBackgroundDrawable(null);
                    font_saved.setText(getString(R.string.font_1));
                    break;
        }


        try {
            TextView version = findViewById(R.id.version);
            PackageInfo info = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version.setText(info.versionName);
        }catch (Exception e){

        }
    }

    /**
     * to register views
     */
    private void registerViews() {
        lang_saved = findViewById(R.id.lang_saved);
        lang_1 = findViewById(R.id.lang_1);
        lang_2 = findViewById(R.id.lang_2);


        font_saved = findViewById(R.id.font_saved);
        font_1 = findViewById(R.id.font_1);
        font_2 = findViewById(R.id.font_2);
        font_3 = findViewById(R.id.font_3);
        font_4 = findViewById(R.id.font_4);
        font_5 = findViewById(R.id.font_5);
        font_6 = findViewById(R.id.font_6);
    }

    /**
     * Here we register the clickables/buttons/views
     */
    private void registerClickables(){
        this.findViewById(R.id.backbtn).setOnClickListener(this);
        this.findViewById(R.id.app_language).setOnClickListener(this);
        this.findViewById(R.id.cuneiform_font).setOnClickListener(this);
        this.findViewById(R.id.dialog).setOnClickListener(this);
        this.findViewById(R.id.main_layout).setOnClickListener(this);
        // languages
        lang_1.setOnClickListener(this);
        lang_2.setOnClickListener(this);

        font_1.setOnClickListener(this);
        font_2.setOnClickListener(this);
        font_3.setOnClickListener(this);
        font_4.setOnClickListener(this);
        font_5.setOnClickListener(this);
        font_6.setOnClickListener(this);


        findViewById(R.id.sources).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backbtn:
                finish();
                break;
            case R.id.app_language:
                this.findViewById(R.id.dialog).setVisibility(View.VISIBLE);
                this.findViewById(R.id.language_picker).setVisibility(View.VISIBLE);
                this.findViewById(R.id.font_picker).setVisibility(View.GONE);
                break;
            case R.id.cuneiform_font:
                this.findViewById(R.id.dialog).setVisibility(View.VISIBLE);
                this.findViewById(R.id.font_picker).setVisibility(View.VISIBLE);
                this.findViewById(R.id.language_picker).setVisibility(View.GONE);
                break;
            case R.id.main_layout:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                break;
            case R.id.lang_1:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("app_language", "en");
                lang_saved.setText("English");
                lang_1.setBackgroundColor(R.color.halfprimary);
                lang_2.setBackgroundDrawable(null);
                Dictionary.dictJSONarray = null;
                TimeLine.timelineArray = null;
                Cities.citiesArray = null;
                break;
            case R.id.lang_2:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("app_language", "ar");
                lang_saved.setText("العربية");
                lang_2.setBackgroundColor(R.color.halfprimary);
                lang_1.setBackgroundDrawable(null);
                Dictionary.dictJSONarray = null;
                TimeLine.timelineArray = null;
                Cities.citiesArray = null;
                break;

            case R.id.font_1:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("font", "CuneiformComposite");
                font_1.setBackgroundColor(R.color.halfprimary);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_1));
                break;

            case R.id.font_2:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("font", "Santakku");
                font_2.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_2));
                break;

            case R.id.font_3:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("font", "SantakkuM");
                font_3.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_3));
                break;

            case R.id.font_4:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("font", "UllikummiA");
                font_4.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_4));
                break;

            case R.id.font_5:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("font", "Assurbanipal");
                font_5.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_6.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_5));
                break;

            case R.id.font_6:
                this.findViewById(R.id.dialog).setVisibility(View.GONE);
                tinydb.putString("font", "NotoSansCuneiform");
                font_6.setBackgroundColor(R.color.halfprimary);
                font_1.setBackgroundDrawable(null);
                font_2.setBackgroundDrawable(null);
                font_3.setBackgroundDrawable(null);
                font_4.setBackgroundDrawable(null);
                font_5.setBackgroundDrawable(null);
                font_saved.setText(getString(R.string.font_6));
                break;

            case R.id.sources:
                startActivity(new Intent(this, ReferencesActivity.class));
                break;


        }
    }
}
