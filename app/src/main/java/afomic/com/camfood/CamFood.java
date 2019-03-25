package afomic.com.camfood;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class CamFood extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance()
                .setPersistenceEnabled(true);
    }

}
