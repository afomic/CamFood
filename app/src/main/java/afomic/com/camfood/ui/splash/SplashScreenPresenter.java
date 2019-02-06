package afomic.com.camfood.ui.splash;

import android.os.Handler;

import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.ui.base.BasePresenter;

public class SplashScreenPresenter extends BasePresenter<SplashScreenView> {
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public SplashScreenPresenter(SplashScreenView view, SharedPreferenceHelper sharedPreferenceHelper) {
        super(view);
        mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    @Override
    public void loadView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPreferenceHelper.getBooleanPref(SharedPreferenceHelper.PREF_USER_EXIST)) {
                    view.showHomeView();
                } else {
                    view.showWelcomeView();
                }
            }
        }, 3000);
    }
}
