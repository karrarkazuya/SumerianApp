package karrar.sumerian.android.sumerian;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class Kings {

    public static JSONArray kingsArray;
    public static String info;

    public static void prepare(Context conx){
        if(kingsArray == null)
            loadKings(conx);
    }

    private static void loadKings(final Context con) {
        try {
            InputStream is = con.getAssets().open("kings.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
            kingsArray = obj.getJSONArray("dynasties");
            info = obj.getString("info");
        } catch (Exception ex) {
            // Toast.makeText(con, con.getString(R.string.dict_no_json), Toast.LENGTH_SHORT).show();
        }
    }

    public static JSONArray getList(Context con){
        if(kingsArray != null)
            return kingsArray;
        loadKings(con);
        return kingsArray;
    }
}
