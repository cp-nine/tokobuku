import config.Values;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidasiString {

    public static void main(String[] args) {

        String text = "())(";


        if (Values.checkString(text)){
            System.out.println("valid");
        } else {
            System.out.println("not valid");
        }

        if (cekString(text)){
            System.out.println("valid");
        } else {
            System.out.println("not valid");
        }


    }


    static boolean cekString(String text) {
        // config pattern
        String ca = "\\(";
        String ma = "\\(*\\)";
        String cb = "\\{";
        String mb = "\\{*\\}";
        String cc = "\\[";
        String mc = "\\[*\\]";

        // declare variable status
        boolean status = false;

        // check by pattern
        if (matchPattern(text, ca) == matchPattern(text, ma)) {
            if (matchPattern(text, cb) == matchPattern(text, mb)) {
                if (matchPattern(text, cc) == matchPattern(text, mc)) {
                    status = true;
                }
            }
        }

        return status;
    }

    // check match pattern
    static int matchPattern(String text, String checker) {
        Pattern pattern = Pattern.compile(checker);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;

        }
        return count;

    }
}