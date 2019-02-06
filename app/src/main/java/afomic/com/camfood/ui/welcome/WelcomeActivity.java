package afomic.com.camfood.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import afomic.com.camfood.R;
import afomic.com.camfood.ui.home.HomeActivity;
import afomic.com.camfood.ui.login.LoginActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {
    private WelcomePresenter mWelcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mWelcomePresenter = new WelcomePresenter(this);
    }

    @OnClick(R.id.btn_customer_register)
    public void registerCustomerClick() {
        mWelcomePresenter.handleCustomerRegistrationClick();
    }

    @OnClick(R.id.btn_restaurant_register)
    public void registerRestaurantClick() {
        mWelcomePresenter.handleRestaurantRegistrationClick();
    }

    @OnClick(R.id.fab_skip)
    public void skipClick() {
        mWelcomePresenter.handleSkipClick();
    }

    @Override
    public void showHomeView() {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginView() {
        Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCustomerRegistrationView() {

    }

    @Override
    public void showRestaurantRegistrationView() {

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
