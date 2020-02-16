package karrar.sumerian.android.sumerian;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class Mythologies {
    public static JSONArray mythsArray;
    public static String reference;

    public static void prepare(Context conx){
        if(mythsArray == null)
            loadMyths(conx);
    }

    private static void loadMyths(final Context con) {
        try {
            InputStream is = con.getAssets().open("en/gods.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
            mythsArray = obj.getJSONArray("gods");
            reference = obj.getString("reference");
        } catch (Exception ex) {
            //Toast.makeText(con, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static JSONObject getById(int id) {
        try {
            for (int i = 0; i < mythsArray.length(); i++) {
                if (id == i)
                    return mythsArray.getJSONObject(i);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }


    public static JSONArray getList(Context con){
        if(mythsArray != null)
            return mythsArray;
        loadMyths(con);
        return mythsArray;
    }
}
