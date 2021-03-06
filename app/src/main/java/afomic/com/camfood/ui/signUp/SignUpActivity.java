package afomic.com.camfood.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.User;
import afomic.com.camfood.service.NotificationService;
import afomic.com.camfood.ui.base.BaseActivity;
import afomic.com.camfood.ui.home.HomeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements SignUpView {
    @BindView(R.id.edt_password)
    EditText passwordEditText;
    @BindView(R.id.edt_email)
    EditText emailEditText;
    @BindView(R.id.edt_address)
    EditText addressEditText;
    @BindView(R.id.edt_phone_number)
    EditText phoneNumberEditText;
    @BindView(R.id.edt_username)
    EditText nameEditText;
    @BindView(R.id.spn_restaurant_type)
    Spinner restaurantTypeSpinner;
    @BindView(R.id.restaurant_layout)
    LinearLayout restaurantLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.progress)
    RelativeLayout progressLayout;

    private SignUpPresenter mSignUpPresenter;
    int registrationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        registrationType = getIntent().getIntExtra(Constants.EXTRA_REGISTRATION_TYPE, 0);
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);
        mSignUpPresenter = new SignUpPresenter(this, AuthManager.getInstance(), sharedPreferenceHelper);
        mSignUpPresenter.setRegistrationType(registrationType);
        mSignUpPresenter.loadView();
    }

    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (registrationType == Constants.RESTAURANT_REGISTRATION_TYPE) {
            nameEditText.setHint(R.string.restaurant_name);
        }
    }

    @Override
    public void showHomeView() {
        Intent service = new Intent(getApplicationContext(), NotificationService.class);
        startService(service);
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void showWelcomeView() {
        finish();
    }



    @Override
    public void showProgressView() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showRestaurantRegistrationField() {
        restaurantLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_sign_up)
    public void signUpClicked() {
        User user = new User();
        user.address = addressEditText.getText().toString();
        user.phonNumber = phoneNumberEditText.getText().toString();
        user.email = emailEditText.getText().toString();
        user.name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        mSignUpPresenter.signUp(user, password);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showWelcomeView();
        }
        return super.onOptionsItemSelected(item);
    }
}
