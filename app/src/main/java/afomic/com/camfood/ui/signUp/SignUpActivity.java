package afomic.com.camfood.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.home.HomeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
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

    private SignUpPresenter mSignUpPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        int registrationType = getIntent().getIntExtra(Constants.EXTRA_REGISTRATION_TYPE, 0);
        mSignUpPresenter = new SignUpPresenter(this, registrationType);
        mSignUpPresenter.loadView();
    }

    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showHomeView() {
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showWelcomeView() {
        finish();
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
        if(item.getItemId()==android.R.id.home){
            showWelcomeView();
        }
        return super.onOptionsItemSelected(item);
    }
}
