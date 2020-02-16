package karrar.sumerian.android.sumerian;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import karrar.sumerian.android.utls.TinyDB;


public class SumerianConverter {
    private static JSONArray sylJSONarray;
    private static JSONArray cuneJSONarray;
    public static boolean finished;

    public static boolean prepare(Context con){
        if(cuneJSONarray == null)
        loadSyllabic(con);
        return true;
    }


    public static TextView setCuneiformFont(Context conx, TextView tv){
        try{
            TinyDB tinyDB = new TinyDB(conx);
            String font = tinyDB.getString("font");
            if(font == null)
                font = "CuneiformComposite";
            Typeface tf = Typeface.createFromAsset(conx.getAssets(), "fonts/"+font+".ttf");
            tv.setTypeface(tf);
            return tv;
        }catch (Exception e){
            TinyDB tinyDB = new TinyDB(conx);
            tinyDB.putString("font", "CuneiformComposite");
            Typeface tf = Typeface.createFromAsset(conx.getAssets(), "fonts/CuneiformComposite.ttf");
            tv.setTypeface(tf);
            return tv;
        }

    }


    /**
     * To load the syllabic json file into list
     */
    private static void loadSyllabic(final Context con){
                try {
                    InputStream is = con.getAssets().open("sumerian_syllabic.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                        JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
                        sylJSONarray = obj.getJSONArray("syllabic");
                        // Then we load the cuneiform letters
                        loadCuneiform(con);
                        finished = true;
                } catch (Exception e) {
                    finished = false;
                }
    }

    /**
     * To load the cuneiform json file into the list
     */
    private static void loadCuneiform(final Context con){
        // To reduce the slowness
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = con.getAssets().open("sumerian_cuneiform.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));
                    cuneJSONarray = obj.getJSONArray("cuneiform");
                } catch (Exception ex) {
                    //return null;
                }
            }});
    }

    /**
     * Check if it is digit
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();

        if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

        for ( char c : str.substring( 1 ).toCharArray() )
        {
            if ( !Character.isDigit( c ) )
            {
                if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
                {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Replace with numbers
     * @return
     */
    private static String workOnNumbers(String text){


        //First case
        String[] first = {"0","&#x12415;","&#x12416;","&#x12417;","&#x12418;","&#x12419;","&#x1241a;","&#x1241b;","&#x1241c;","&#x1241d;"};
        //tens
        String[] tens = {"0","&#x1230b;","&#x1230b;&#x1230b;","&#x1230d;","&#x1240f;","&#x12410;","&#x12415;","&#x12415;&#x1230b;","&#x12415;&#x1230C;","&#x12415;&#x1230d;"};
        //hundereds
        String[] hund = {"0","&#x12415;&#x1240f;","&#x12416;&#x12413;","&#x12419;","&#x1241a;&#x1240f;","&#x1241c;&#x1230C;","&#x1241E;","&#x1241E;&#x12415;&#x1240f;","&#x1241E;&#x12416;&#x12413;","&#x1241E;&#x12419;"};


        int tempval = Integer.valueOf(text);

        List line = new ArrayList();


        if(tempval>9 && tempval<100){
            String firstd = text.substring(0, 1);
            String secoundd = text.substring(1, 2);
            if(secoundd.equals("0")){
                line.add(tens[Integer.valueOf(firstd)]+"");
            }else{
                line.add(tens[Integer.valueOf(firstd)]+".");
                line.add(first[Integer.valueOf(secoundd)]+"");
            }
        }else if(tempval>99 && tempval<1000){

            String firstd = text.substring(0, 1);
            String secoundd = text.substring(1, 2);
            String therd = text.substring(2, 3);

            if(secoundd.equals("0") && therd.equals("0") == false){
                line.add(hund[Integer.valueOf(firstd)]+".");
                line.add(first[Integer.valueOf(therd)]+"");

            }else if(secoundd.equals("0") && therd.equals("0")){
                line.add(hund[Integer.valueOf(firstd)]+"");

            }else if(therd.equals("0") && secoundd.equals("0") == false){
                line.add(hund[Integer.valueOf(firstd)]+".");
                line.add(tens[Integer.valueOf(secoundd)]+"");

            }else{
                line.add(hund[Integer.valueOf(firstd)]+".");
                line.add(tens[Integer.valueOf(secoundd)]+".");
                line.add(first[Integer.valueOf(therd)]+"");
            }

        }else if(tempval<10){
            line.add(first[Integer.valueOf(text)]+"");

        }else{
            return text;
        }

        String result = "";
        for(int i = 0;i<line.size();i++){
            result = result+line.get(i).toString();
        }
        return result;
    }


    /**
     * To replace words into unicodes
     * @param text
     * @return
     */
    private static String replaceWithUnicodes(String text){


        // We clean first
        // First we deal with the has instead
        text = text.toLowerCase();
        String newtext = "";
        text = text+" ";
        while(text.contains("  ")){
            text = text.replace("  ", " ");
        }
        while(text.startsWith(" ")){
            text = text.replaceFirst(" ", "");
        }


        LinkedList chars = splitBySpace(text);


        // USING SYLS TO CONVERT LATER
        try {
            for (int i = 0; i < chars.size(); i++) {
                String letter = chars.get(i).toString().toLowerCase();
                if(!letter.contains("&#x"))
                for (int j = 0; j < sylJSONarray.length(); j++) {
                    JSONObject item = sylJSONarray.getJSONObject(j);
                    JSONArray subs = item.getJSONArray("subs");
                    for (int l = 0; l <subs.length();l++){
                        String sub = subs.getString(l).toLowerCase();
                        if(sub.equals(letter) || sub.startsWith(letter+"(")){
                            // here we replace
                            String tag = item.getString("tag").toLowerCase();
                            tag = tag.replace("ḫ", "h");
                            tag = tag.replace("š", "c");
                            tag = tag.replace("ĝ", "j");
                            tag = tag.replace("@180", "inversum");
                            chars.set(i,tag);
                            break;
                        }
                    }
                }
            }
        }catch (Exception e){

        }

        chars = pureLatin(chars);

        // STAGE THREE! CONVERTING INTO UNICODES LASTTIME
        try {
            for (int i = 0; i < chars.size(); i++) {
                String letter = chars.get(i).toString().toLowerCase();
                if(!letter.contains("&#x"))
                for (int j = 0; j < cuneJSONarray.length(); j++) {
                    JSONObject item = cuneJSONarray.getJSONObject(j);
                    String tag = item.getString("tag").toLowerCase();
                    if(tag.equals(letter)){
                        // here we replace
                        chars.set(i,item.getString("unicode"));
                        break;
                    }
                }

            }



            chars = convertByReverse(chars);
            Log.w("converted", chars + "");
        }catch (Exception e){

        }

        newtext = "";
        for (int i = 0;i<chars.size();i++){
            newtext = newtext + chars.get(i).toString() + " ";
        }

        return newtext;
    }

    private static LinkedList convertByReverse(LinkedList chars) {




        return chars;
    }


    /**
     * For converting to pure latin
     * @param list
     * @return
     */
    private static LinkedList pureLatin(LinkedList list){
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            text = text + " " + list.get(i).toString();
        }
        if(text.startsWith(" "))
            text = text.substring(1, text.length());

        text = text.toLowerCase();
        text = text.replace("!","");

        text = text.replace("ḫ", "h");
        text = text.replace("š", "c");
        text = text.replace("ĝ", "j");
        //text = text.replace("×", " × ");

        // Now we remove few extra unwanted stuff
        text = text.replace("."," |-| ");
        while (text.contains("|-| |-|"))
            text = text.replace("|-| |-|","|-|");

        return splitBySpace(text);
    }


    /**
     * On click/Convert
     */
    public static String convertToSumerian(String text) {
        if (!text.equals(" ") && !text.equals("")) {
            text = android.text.Html.fromHtml(text).toString();
            // Here is the magic
            text = cleanAndReady(text);
            return text;
        }
        return text;
    }



    /**
     * Converting the text into unicodes to be viewed in the html webview
     * @param text
     * @return
     */
    private static String cleanAndReady(String text) {

        text = drawSigns(text);
        return text;
    }

    /**
     * So we call it twice to fix the x issue
     * @param text
     * @return
     */
    private static String drawSigns(String text) {
        text = cleanFirst(text);

        if(isNumeric(text)){
            text = workOnNumbers(text);
        }
        text = replaceWithUnicodes(text.replace(")", "  ").replace("(", "  "));


        text = cleanAfter(text);
        return text;
    }


    /**
     * For the numbers tho not working now..
     * @param text
     * @return
     */
    private static int getImageSize(int text){

        if(text>1){
            if(text>7){
                text = 7*15;
                text = text/2;
            }else{
                text = text*15;
                text = text/2;
            }
        }
        return text;
    }






    /**
     * Applied before converting to clean unwatned letters
     * @param text
     * @return
     */
    private static String cleanFirst(String text){
        text = text.toLowerCase();


        // We must deal with the strange letter

        text = text.replace(" crossing ", "%");
        text = text.replace(" times ", "×");
        text = text.replace(" plus ", "+");
        text = text.replace(" over ", "/");
        text = text.replace(" tenu ", "@t");
        text = text.replace(" gunu ", "@g");
        text = text.replace(" tenu", "@t");
        text = text.replace(" gunu", "@g");
        text = text.replace(" inverted ", "_opposing_");
        text = text.replace("(inverted)", "_opposing_");
        text = text.replace(" opposing ", "_opposing_");
        text = text.replace("ḫ", "h");
        text = text.replace("c", "š");
        text = text.replace("j", "ĝ");

        text = text.replace("[", " |^^| ");
        text = text.replace("]"," |^| ");
        text = text.replace("«"," |«| ");
        text = text.replace("»"," |»| ");

        if(text.startsWith("/"))
            text = text.substring(1,text.length());
        text = text.replace("!", "");
        text = text.replace(" /", " ");
        text = text.replace("-/", "-");
        text = text.replace("/ ", " ");
        text = text.replace("/-", "-");
        text = text.replace("\\", "-");
        text = text.replace("?", "-");
        text = text.replace(".", "-");
        while(text.contains("--")){ text = text.replace("--", "-"); }
        text = text.replace("-", " |-| ");




        while(text.contains("|-| |-|"))
            text = text.replace("|-| |-|", "|-|");

        text = text.replace(" +2 ", "|+2|");//<font color='#ffcc00'>+</font>
        text = text.replace(" +3 ", "|+3|");//<font color='#cc33ff'>+</font>

        while(text.contains("  ")){ text = text.replace("  ", " "); }

        return text;
    }


    /**
     * Applied before returning as html
     * @param text
     * @return
     */
    private static String cleanAfter(String text){
        // We must deal with the strange letter
        text = text.replace("š","c");
        text = text.replace("ĝ","j");

        text = text.replace(" |^^| ", "[ ");
        text = text.replace(" |^| ", " ]");
        text = text.replace(" |-| ", "");
        text = text.replace("|-|", "");

        text = text.replace("|«|", "«");
        text = text.replace("|»|", "»");

        text = text.replace("|+2|", "<font color='#ffcc00'>+</font>"); // means inside the first
        text = text.replace("|+3|", "<font color='#cc33ff'>+</font>"); // means on the first

        while(text.contains("  ")){ text = text.replaceAll("  ", " "); }

        return text;
    }

    private static LinkedList splitBySpace(String text){
        LinkedList list = new <String>LinkedList();
        String[] splitStr = text.split("\\s+");
        list.addAll(Arrays.asList(splitStr));
        return list;
    }

}
