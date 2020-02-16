package karrar.sumerian.android.sumerian;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import karrar.sumerian.android.R;
import karrar.sumerian.android.utls.TinyDB;

public class Dictionary {
    public static JSONArray dictJSONarray;
    private static Context conx;
    private static String language;

    public static void prepare(Context conx){
        language = (new TinyDB(conx)).getString("app_language");
        if(dictJSONarray == null)
            loadWords(conx);
    }

    /**
     * to load the json file and make a json object of it.
     * @param con
     */
    private static void loadWords(final Context con) {
        // To reduce the slowness
                try {
                    if(language == null || language.equals(""))
                        language = "en";
                    InputStream is = con.getAssets().open(language+"/sumerian_dict.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
                    dictJSONarray = obj.getJSONArray("words");
                } catch (Exception ex) {
                    //Toast.makeText(con, con.getString(R.string.dict_no_json), Toast.LENGTH_SHORT).show();
                }
    }

    /**
     * Search and return results
     * @param input
     * @return
     */
    public static JSONArray search(String input, boolean onlyEquals) {

        String original = input.toLowerCase();
        input = input.toLowerCase();
        input = input.replace("c", "š");
        input = input.replace("j", "ĝ");

        try {
            String sum_key;
            String sum_word;
            String meaning_key;
            String meaning_word;
            String akkadian;
            JSONArray jsonArray = new JSONArray();
            for (int j = 0; j < dictJSONarray.length(); j++) {
                JSONObject item = dictJSONarray.getJSONObject(j);
                sum_key = item.getString("sum_key").toLowerCase();
                sum_word = item.getString("sum_word").toLowerCase();
                meaning_key = item.getString("meaning_key").toLowerCase();
                meaning_word = item.getString("meaning_word").toLowerCase();
                akkadian = item.getString("akkadian").toLowerCase();

                if(onlyEquals){
                    if(sum_key.equals(input) || sum_key.equals(original)){
                        jsonArray.put(item);
                    }else if(meaning_key.equals(input) || meaning_key.equals(original)){
                        jsonArray.put(item);
                    }
                }else{
                    if(sum_key.contains(input) || sum_key.contains(original)){
                        jsonArray.put(item);
                    }else if(sum_word.contains(input) || sum_word.contains(original)){
                        jsonArray.put(item);
                    }else if(meaning_key.contains(input) || meaning_key.contains(original)){
                        jsonArray.put(item);
                    }else if(meaning_word.contains(input) || meaning_word.contains(original)){
                        jsonArray.put(item);
                    }else if(akkadian.contains(input) || akkadian.contains(original)){
                        jsonArray.put(item);
                    }
                }

            }
            // removing repeated
            JSONArray result = removeRepeated(jsonArray);
            // making equal ones at top
            return showEqualsFirst(result, input, original);

        }catch (Exception e){
            Toast.makeText(conx, conx.getString(R.string.faild_searching), Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    /**
     * to show equal inputs first
     * @param result
     * @param input
     * @param original
     * @return
     */
    private static JSONArray showEqualsFirst(JSONArray result, String input, String original) {
        if(result == null)
            return null;
        JSONArray equalList =  new JSONArray();
        JSONArray containsList = new JSONArray();
        try {
            for (int i = 0; i < result.length(); i++) {
                JSONObject item = result.getJSONObject(i);
                String sum_key = item.getString("sum_key").toLowerCase();
                String meaning_key = item.getString("meaning_key").toLowerCase();
                if (sum_key.equals(input) || sum_key.equals(original) || meaning_key.equals(input) || meaning_key.equals(original))
                    equalList.put(item);
                else
                    containsList.put(item);
            }
            for (int i = 0; i < containsList.length(); i++)
                equalList.put(containsList.getJSONObject(i));
            return equalList;
        }catch (Exception e){
            return null;
        }
    }


    /**
     * Remove similar results, they just few but in case there is more
     * @param results
     * @return
     */
    private static JSONArray removeRepeated(JSONArray results) {
        JSONArray edited = results;
        JSONArray oneToCompare = results;
        for (int i=0;i < results.length();i++){
            for(int j = (i+1);j < results.length();j++){
                try {
                    if (results.getJSONObject(i).getString("sum_key").equals(oneToCompare.getJSONObject(j).getString("sum_key")) &&
                            results.getJSONObject(i).getString("meaning_key").equals(oneToCompare.getJSONObject(j).getString("meaning_key")) &&
                            results.getJSONObject(i).getString("sum_word").equals(oneToCompare.getJSONObject(j).getString("sum_word")) &&
                            results.getJSONObject(i).getString("meaning_word").equals(oneToCompare.getJSONObject(j).getString("meaning_word"))) {
                        edited.remove(j);
                    }
                }catch (Exception e){
                    // error
                }
            }
        }
        return edited;
    }
}
