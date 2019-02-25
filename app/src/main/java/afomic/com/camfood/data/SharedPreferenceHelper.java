package afomic.com.camfood.data;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceHelper {
    private SharedPreferences mSharedPreferences;

    private static final String PREFERENCE_FILE_NAME = "afomic.com.camfood";
    public static final String PREF_USER_NAME = "username";
    public static final String PREF_USER_ID = "user_id";
    public static final String PREF_USER_PHONE_NUMBER = "phone_number";
    public static final String PREF_RESTAURANT_ACCOUNT_TYPE = "account_type";
    public static final String PREF_USER_EXIST = "user_exist";

    public SharedPreferenceHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveStringPref(String key, String value) {
        mSharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    public String getStringPref(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public void saveBooleanPref(String key, boolean value) {
        mSharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    public Boolean getBooleanPref(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void saveIntegerPref(String key, int value) {
        mSharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }

    public int getIntegerPref(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
