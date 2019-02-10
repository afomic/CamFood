package afomic.com.camfood.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class Common {
    public static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public static Object parseJSONToObject(String json, TypeToken objectType) {
        try {
            Gson gson = new GsonBuilder().create();
            JsonReader reader = new JsonReader(new StringReader(json));
            return gson.fromJson(reader, objectType.getType());
        } catch (JsonSyntaxException jse) {
            jse.printStackTrace();
            return null;
        }
    }
    public static String parseObjectToJSON(Object object) {
        try {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(object);
        } catch (JsonSyntaxException jse) {
            jse.printStackTrace();
            return "";
        }
    }
}
