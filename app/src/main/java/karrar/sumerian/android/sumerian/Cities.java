package karrar.sumerian.android.sumerian;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

import karrar.sumerian.android.utls.TinyDB;

public class Cities {
    public static JSONArray citiesArray;
    private static String language;

    public static void prepare(Context conx){
        language = (new TinyDB(conx)).getString("app_language");
        if(citiesArray == null)
            loadCities(conx);
    }

    private static void loadCities(final Context con) {
        try {
            if(language == null || language.equals(""))
                language = "en";
            InputStream is = con.getAssets().open(language+"/cities.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
            citiesArray = obj.getJSONArray("cities");
        } catch (Exception ex) {
            //Toast.makeText(con, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static JSONObject getById(int id) {
        try {
            for (int i = 0; i < citiesArray.length(); i++) {
                if (id == i)
                    return citiesArray.getJSONObject(i);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    public static JSONArray getList(Context con){
        if(citiesArray != null)
            return citiesArray;
        loadCities(con);
        return citiesArray;
    }
}
