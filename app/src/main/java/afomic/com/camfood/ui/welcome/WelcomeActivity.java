package afomic.com.camfood.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.ui.home.HomeActivity;
import afomic.com.camfood.ui.login.LoginActivity;
import afomic.com.camfood.ui.signUp.SignUpActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {
    private WelcomePresenter mWelcomePresenter;
    @BindView(R.id.tv_login_span)
    TextView loginSpanTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mWelcomePresenter = new WelcomePresenter(this);
        mWelcomePresenter.loadView();
    }

    @Override
    public void setup() {
        String text = loginSpanTextView.getText().toString();
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                mWelcomePresenter.handleLoginSpan();
            }
        }, 0, text.length(), 0);
        loginSpanTextView.setMovementMethod(new LinkMovementMethod());
        loginSpanTextView.setText(spannableString);

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
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCustomerRegistrationView() {
        showSignUpView(Constants.CUSTOMER_REGISTRATION_TYPE);
    }

    @Override
    public void showRestaurantRegistrationView() {
        showSignUpView(Constants.RESTAURANT_REGISTRATION_TYPE);
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

    public void showSignUpView(int registrationType) {
        Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
        intent.putExtra(Constants.EXTRA_REGISTRATION_TYPE, registrationType);
        startActivity(intent);
    }
}
