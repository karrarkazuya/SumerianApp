package karrar.sumerian.android.sumerian;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import karrar.sumerian.android.R;

public class Signs {


    public static JSONArray signsArray;
    public static JSONArray referencesArray;
    public static JSONArray infoArray;
    public static ArrayList<String> preparedSigns;

    public static void prepare(Context conx){
        if(signsArray == null)
        loadSigns(conx);
    }

    private static void loadSigns(final Context con) {
                try {
                    InputStream is = con.getAssets().open("signs_list.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
                    signsArray = obj.getJSONArray("signs");
                    referencesArray = obj.getJSONArray("references");
                    infoArray = obj.getJSONArray("info");
                } catch (Exception ex) {
                   // Toast.makeText(con, con.getString(R.string.dict_no_json), Toast.LENGTH_SHORT).show();
                }
    }

    public static void prepareSigns(){
        if(preparedSigns == null) {
            ArrayList<String> signs = new ArrayList();
            try {
                for (int i = 0; i < signsArray.length(); i++) {

                    JSONObject item = signsArray.getJSONObject(i);

                    JSONArray SingsList = item.getJSONArray("images");
                    for (int j = 0; j < SingsList.length(); j++) {

                        boolean add = true;
                        for (int k = 0; k < signs.size(); k++) {
                            if (SingsList.getString(j).equals(signs.get(k))) {
                                add = false;
                                break;
                            }
                        }
                        if (add) {

                        String sign = SingsList.getString(j);
                        /*
                        String orginal = sign;
                        sign = sign.toLowerCase();
                        sign = sign.replace(".png", "");
                        sign = sign.replace("_", " ");

                        sign = SumerianConverter.convertToSumerian(sign);

                        if(!sign.contains("&#x"))
                            sign = orginal;
                            */
                        signs.add(sign);
                        }
                    }
                }
            } catch (Exception e) {
                Log.d("error prepare", e.getMessage());
            }
            preparedSigns = signs;
        }
    }

    public static JSONArray FindSignSimilars(String image){
        JSONArray js = new JSONArray();

        try {
            for (int i = 0; i < signsArray.length(); i++) {
                JSONObject obj = signsArray.getJSONObject(i);
                JSONArray images = obj.getJSONArray("images");
                for (int j = 0; j < images.length(); j++) {
                    if(images.getString(j).equals(image)){
                        js.put(obj);
                        break;
                    }
                }

            }
        }catch (Exception e){

        }

        return js;
    }

}



