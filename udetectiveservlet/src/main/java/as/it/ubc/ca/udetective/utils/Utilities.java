package as.it.ubc.ca.udetective.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
     
    public static String transformYesNoToString(String booleanStr ) {
        
        log.debug("booleanStr="+booleanStr);
        
        String ret = "";
        if (booleanStr == null || booleanStr.trim().equals("")) {
            ret = "";
        } else if (booleanStr.equals("Yes")) {
            ret = "Y";
        } else if (booleanStr.equals("No")) {
            ret = "N";
        } else {
            ret = "";
        }
        
        return ret;
    }
    
    public static boolean isEmptyString(String str) {
        boolean ret = false;

        if (str == null || str.equals("")) {
            ret = true;
        }

        return ret;
    }
}