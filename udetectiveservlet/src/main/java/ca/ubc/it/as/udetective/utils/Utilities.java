package ca.ubc.it.as.udetective.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.regex.Matcher;

/**
 * Static utilities
 * @author UBC IT
 */
public class Utilities {
    
    private static final Logger log = LogManager.getLogger(Utilities.class);
    
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            log.error(ex.toString());
        }
        return date != null;
    }
     
    public static boolean compareDates(String date1, String date2, String format) {

        SimpleDateFormat frm = new SimpleDateFormat(format);

        Date d1 = null;
        Date d2 = null;
        
        try {
            d1 = frm.parse(date1);
            d2 = frm.parse(date2);
        } catch (ParseException ex) {
            log.error(ex.toString());
        }
        
        log.error(d1.compareTo(d2));
        
        if (d1.compareTo(d2) > 0) {
            log.error(date2 + " is an earlier date than " + d1);
            return false;
        }         
        return true;
    }
         
    public static boolean isEmptyString(String str) {
        boolean ret = false;

        if (str == null || "".equals(str)) {
            ret = true;
        }

        return ret;
    }
    
    public static String extractIpAddress(String message) {
        String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group();
        } else{
            return "0.0.0.0";
        }
    }
}