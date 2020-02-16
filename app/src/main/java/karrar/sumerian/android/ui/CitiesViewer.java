package karrar.sumerian.android.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import karrar.sumerian.android.R;
import karrar.sumerian.android.sumerian.Cities;
import karrar.sumerian.android.sumerian.Mythologies;
import karrar.sumerian.android.sumerian.SumerianConverter;

public class CitiesViewer extends AppCompatActivity {

    private JSONObject data;
    private TextView title1;
    private TextView title2;
    private TextView alternate_name;
    private TextView prominence;
    private TextView location;
    private TextView coordinates;
    private TextView founded;
    private TextView abandoned;
    private TextView city_info;
    private TextView area;
    private TextView excavation_dates;
    private TextView architecture;
    private TextView archaeologists;
    private TextView archaeology;
    private TextView research;
    private TextView environment;
    private TextView occupation;
    private TextView part_of;
    private TextView criteria;
    private TextView inscription;
    private TextView buffer_zone;
    private TextView history;
    private TextView type;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_viewer);
        getSupportActionBar().hide();

        Intent i = getIntent();
        int id = i.getIntExtra("pos", 0);

        data = Cities.getById(id);

        setViews();

        setResults();

        setCliclables();
    }

    private void setCliclables() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String references = "";
                    JSONArray list = data.getJSONArray("references");
                    for (int i = 0; i < list.length(); i++) {
                        references = references + list.getString(i)+"\n\n";
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(CitiesViewer.this);
                    builder.setTitle(getString(R.string.more));
                    builder.setMessage(references);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }catch (Exception e){

                }

            }
        });
    }

    private void setViews() {
        image = findViewById(R.id.image);
        title1 = findViewById(R.id.title);
        title1 = SumerianConverter.setCuneiformFont(this, title1);
        title2 = findViewById(R.id.title2);
        title2 = SumerianConverter.setCuneiformFont(this, title2);
        alternate_name = findViewById(R.id.alternate_name);
        alternate_name = SumerianConverter.setCuneiformFont(this, alternate_name);
        type = findViewById(R.id.type);
        prominence = findViewById(R.id.prominence);
        prominence = SumerianConverter.setCuneiformFont(this, prominence);
        location = findViewById(R.id.location);
        coordinates = findViewById(R.id.coordinates);
        founded = findViewById(R.id.founded);
        abandoned = findViewById(R.id.abandoned);
        city_info = findViewById(R.id.city_info);
        city_info = SumerianConverter.setCuneiformFont(this, city_info);
        area = findViewById(R.id.area);
        excavation_dates = findViewById(R.id.excavation_dates);
        architecture = findViewById(R.id.architecture);
        architecture = SumerianConverter.setCuneiformFont(this, architecture);
        archaeologists = findViewById(R.id.archaeologists);
        archaeology = findViewById(R.id.archaeology);
        archaeology = SumerianConverter.setCuneiformFont(this, archaeology);
        research = findViewById(R.id.research);
        research = SumerianConverter.setCuneiformFont(this, research);
        environment = findViewById(R.id.environment);
        environment = SumerianConverter.setCuneiformFont(this, environment);
        occupation = findViewById(R.id.occupation);
        occupation = SumerianConverter.setCuneiformFont(this, occupation);
        part_of = findViewById(R.id.part_of);
        criteria = findViewById(R.id.criteria);
        inscription = findViewById(R.id.inscription);
        buffer_zone = findViewById(R.id.buffer_zone);
        history = findViewById(R.id.history);
        history = SumerianConverter.setCuneiformFont(this, history);
    }

    private void setResults() {
        try {
            String titleStr = data.getString("title");
            String typeStr = data.getString("type");
            String alternateNameStr = data.getString("alternate_name");
            String prominenceStr = data.getString("prominence");
            String locationStr = data.getString("location");
            String coordinatesStr = data.getString("coordinates");
            String foundedStr = data.getString("founded");
            String abandonedStr = data.getString("abandoned");
            String cityInfoStr = data.getString("info");
            String areaStr = data.getString("area");
            String excavationDatesStr = data.getString("excavation_dates");
            String architectureStr = data.getString("architecture");
            String archaeologistsStr = data.getString("archaeologists");
            String archaeologyStr = data.getString("archaeology");
            String researchStr = data.getString("research");
            String environmentStr = data.getString("environment");
            String occupationStr = data.getString("occupation");
            String partOfStr = data.getString("part_of");
            String criteriaStr = data.getString("criteria");
            String inscriptionStr = data.getString("inscription");
            String bufferZoneStr = data.getString("buffer_zone");
            String historyStr = data.getString("history");

            title1.setText(titleStr);
            title2.setText(titleStr);

            if(!typeStr.equals(""))
                type.setText(typeStr);
            else
                setHidden(type);

            if(!alternateNameStr.equals(""))
                alternate_name.setText(alternateNameStr);
            else
                setHidden(alternate_name);

            if(!prominenceStr.equals(""))
                prominence.setText(prominenceStr);
            else
                setHidden(prominence);

            if(!locationStr.equals(""))
                location.setText(locationStr);
            else
                setHidden(location);

            if(!coordinatesStr.equals(""))
                coordinates.setText(coordinatesStr);
            else
                setHidden(coordinates);

            if(!foundedStr.equals(""))
                founded.setText(foundedStr);
            else
                setHidden(founded);

            if(!abandonedStr.equals(""))
                abandoned.setText(abandonedStr);
            else
                setHidden(abandoned);


            if(!cityInfoStr.equals(""))
                city_info.setText(cityInfoStr);
            else
                setHidden(city_info);

            if(!areaStr.equals(""))
                area.setText(areaStr);
            else
                setHidden(area);

            if(!excavationDatesStr.equals(""))
                excavation_dates.setText(excavationDatesStr);
            else
                setHidden(excavation_dates);

            if(!architectureStr.equals(""))
                architecture.setText(architectureStr);
            else
                setHidden(architecture);

            if(!archaeologistsStr.equals(""))
                archaeologists.setText(archaeologistsStr);
            else
                setHidden(archaeologists);

            if(!archaeologyStr.equals(""))
                archaeology.setText(archaeologyStr);
            else
                setHidden(archaeology);

            if(!researchStr.equals(""))
                research.setText(researchStr);
            else
                setHidden(research);

            if(!environmentStr.equals(""))
                environment.setText(environmentStr);
            else
                setHidden(environment);

            if(!occupationStr.equals(""))
                occupation.setText(occupationStr);
            else
                setHidden(occupation);

            if(!partOfStr.equals(""))
                part_of.setText(partOfStr);
            else
                setHidden(part_of);

            if(!criteriaStr.equals(""))
                criteria.setText(criteriaStr);
            else
                setHidden(criteria);

            if(!inscriptionStr.equals(""))
                inscription.setText(inscriptionStr);
            else
                setHidden(inscription);

            if(!bufferZoneStr.equals(""))
                buffer_zone.setText(bufferZoneStr);
            else
                setHidden(buffer_zone);

            if(!historyStr.equals(""))
                history.setText(historyStr);
            else
                setHidden(history);



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
            finish();
        }
    }

    private void setHidden(TextView type) {
        LinearLayout father = (LinearLayout) type.getParent();
        father.setVisibility(View.GONE);
    }


}
