package afomic.com.camfood;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.database.FirebaseDatabase;

import afomic.com.camfood.service.NotificationService;

public class CamFood extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance()
                .setPersistenceEnabled(true);
        Intent intent = new Intent(getApplicationContext(), NotificationService.class);
        startService(intent);
    }

}
