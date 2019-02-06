package afomic.com.camfood.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import afomic.com.camfood.R;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.ui.home.HomeActivity;
import afomic.com.camfood.ui.welcome.WelcomeActivity;


public class SplashScreen extends AppCompatActivity implements SplashScreenView {

    private SplashScreenPresenter mSplashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mSplashScreenPresenter = new SplashScreenPresenter(this, new SharedPreferenceHelper(SplashScreen.this));
        mSplashScreenPresenter.loadView();
    }

    @Override
    public void showWelcomeView() {
        Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showHomeView() {
        Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void hideProgressView() {

    }
}
