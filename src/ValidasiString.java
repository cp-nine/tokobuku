import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidasiString {

    public static void main(String[] args) {

//        String text = "(())";
//
//        cekString(text);

    }

    static void regex(String text){

        try {
            Pattern.matches(text,"\\(*\\)");
            Pattern.matches(text,"\\[*\\]");
            Pattern.matches(text,"\\^{*\\}");
            System.out.println("valid");
        }catch (Exception e){
            System.out.println("not valid");
        }
    }


    static void cekString(String text) {
        // config pattern
        String ca = "\\(";
        String ma = "\\(*\\)";
        String cb = "\\{";
        String mb = "\\{*\\}";
        String cc = "\\[";
        String mc = "\\[*\\]";

        // declare variable status
        String status;

        // check by pattern
        if (matchPattern(text, ca) == matchPattern(text, ma)) {
            if (matchPattern(text, cb) == matchPattern(text, mb)) {
                if (matchPattern(text, cc) == matchPattern(text, mc)) {
                    status = "valid";
                } else {
                    status = "not valid";
                }
            } else {
                status = "not valid";
            }
        } else {
            status = "not valid";
        }

        System.out.println(status);
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