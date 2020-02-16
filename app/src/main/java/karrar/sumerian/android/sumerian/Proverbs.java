package karrar.sumerian.android.sumerian;

import android.content.Context;
import android.os.Handler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.Random;

public class Proverbs {

    private static JSONArray proJSONarray;


    /**
     * Read the json file and save it
     * @return
     */
    public static void prepare(final Context con) {
        if(proJSONarray == null) {
            try {
                InputStream is = con.getAssets().open("en/sumerian_proverbs.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                try {
                    JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
                    proJSONarray = obj.getJSONArray("proverbs");
                } catch (Exception e) {
                }

            } catch (Exception ex) {
            }
        }
    }


    /**
     * To get and set a random proverb
     */
    public static JSONObject getRandomProverb(){
        Random rn = new Random();
        int randomNum = rn.nextInt(proJSONarray.length());

        try {
            if(proJSONarray != null){
                JSONObject item = proJSONarray.getJSONObject(randomNum);
                return item;
            }
            return null;
        } catch (Exception e) {
            return null;

        }
    }


    /**
     * Clean the proverbs and send them back
     * @param text
     */
    public static String Clean(String text, Boolean onlyRemoveNumbers){
        while(text.contains("[line]")){
            text = text.replace("[line]", " \n");
        }
        if(onlyRemoveNumbers)
            return text;
        return cleanLast(text);
    }

    /**
     * for the clean function
     * @param text
     * @return
     */
    private static String cleanLast(String text){
        text = text.toLowerCase();

        try {
            if(text.contains(")") && text.contains("(")){
                while(text.contains(")") && text.contains("(") && text.indexOf(")") < text.indexOf("(")){
                    text = text.replace(text.substring(0, text.indexOf(")")+1), "");
                }
            }
            while(text.contains(")") && text.contains("(")){
                int first = 0;
                int last = 0;
                for (int i = 0; i < text.length(); i++) {
                    if(Character.toString(text.charAt(i)).equals("(")){
                        first = i;
                    }else if(Character.toString(text.charAt(i)).equals(")")){
                        if(first == 0){
                            text = text.replace(text.substring(0, text.indexOf(")")+1), "");
                        }
                        break;
                    }
                }

                for (int i = first; i < text.length(); i++) {
                    if(Character.toString(text.charAt(i)).equals(")")){
                        last = i;
                    }
                }
                text = text.replace(text.substring(first, last+1),".");
            }

        }catch (Exception e){
            // pass
        }


        while (text.contains(")") && !text.contains("(")){
            text = text.replaceFirst(text.substring(0,text.indexOf(")")+1),"");
        }


        // We must deal with the strange letter
        text = text.replace("ḫ", "h");
        text = text.replace("c", "š");
        text = text.replace("j", "ĝ");

        text = text.replace("[", "");
        text = text.replace("]",".");
        text = text.replace("«"," « ");
        text = text.replace("»"," » ");

        while(text.contains("\\"))
            text = text.replace("\\", ".");
        while(text.contains(".."))
            text = text.replace("..", ".");
        text = text.replace("?", " ? ");
        while(text.contains("--")){ text = text.replace("--", "-"); }

        while(text.contains("  ")){ text = text.replaceAll("  ", " "); }

        // We most replace those
        return text;
    }
}
