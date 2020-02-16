package karrar.sumerian.android.sumerian;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import karrar.sumerian.android.R;
import karrar.sumerian.android.utls.TinyDB;

public class TimeLine {

    public static JSONArray timelineArray = null;
    private static String language;

    public static void prepare(Context conx){
        language = (new TinyDB(conx)).getString("app_language");
        if(timelineArray == null)
            loadContent(conx);
    }

    /**
     * to load the json file and make a json object of it.
     * @param con
     */
    private static void loadContent(final Context con) {
                try {
                    if(language == null || language.equals(""))
                        language = "en";
                    InputStream is = con.getAssets().open(language+"/timeline/timeline.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
                    timelineArray = obj.getJSONArray("timeline");
                } catch (Exception ex) {
                    //Toast.makeText(con, con.getString(R.string.dict_no_json), Toast.LENGTH_SHORT).show();
                }
    }



    public static JSONObject getById(int id){
        try {
            return timelineArray.getJSONObject(id);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
