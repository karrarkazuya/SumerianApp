package karrar.sumerian.android.utls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringsHandler {
    public static String hexConverter(String input){
        Pattern HEX = Pattern.compile("(?i)&#x([0-9a-f]+);|&#(\\d+);");
        Matcher m = HEX.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (m.find())
            m.appendReplacement(sb,
                    String.valueOf((char) (m.group(1) != null ?
                            Integer.parseInt(m.group(1), 16) :
                            Integer.parseInt(m.group(2)))));
        m.appendTail(sb);
        String output = sb.toString();
        return output;
    }


}
