package afomic.com.camfood.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import afomic.com.camfood.R;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.service.NotificationService;
import afomic.com.camfood.ui.base.BaseActivity;
import afomic.com.camfood.ui.base.BaseFragment;
import afomic.com.camfood.ui.home.HomeActivity;
import afomic.com.camfood.ui.welcome.WelcomeActivity;


public class SplashScreen extends BaseActivity implements SplashScreenView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SplashScreenPresenter mSplashScreenPresenter = new SplashScreenPresenter(this, new SharedPreferenceHelper(SplashScreen.this));
        mSplashScreenPresenter.loadView();
    }

    @Override
    public void setup() {

    }

    @Override
    public void showWelcomeView() {
        Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showHomeView() {
        Intent service = new Intent(getApplicationContext(), NotificationService.class);
        startService(service);
        Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
