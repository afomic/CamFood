package afomic.com.camfood.helper;


import android.text.format.DateUtils;

import java.text.NumberFormat;

public class StringUtil {
    public static String getFormattedSting(int value) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return numberFormat.format(value);
    }

}
