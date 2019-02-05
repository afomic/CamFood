package afomic.com.camfood.ui.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import afomic.com.camfood.R;


public class SplashScreen extends AppCompatActivity implements SplashScreenView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    public void showWelcomeView() {

    }

    @Override
    public void showHomeView() {

    }
}
