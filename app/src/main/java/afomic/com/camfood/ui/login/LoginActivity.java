package afomic.com.camfood.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import afomic.com.camfood.R;
import afomic.com.camfood.ui.home.HomeActivity;
import afomic.com.camfood.ui.welcome.WelcomeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.edt_email)
    EditText emailEditText;
    @BindView(R.id.edt_password)
    EditText passwordEditText;

    private LoginPresenter mLoginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.loadView();
    }

    @Override
    public void setup() {

    }

    @Override
    public void showHomeView() {
        showActivity(HomeActivity.class);
    }

    @Override
    public void showWelcomeView() {
        showActivity(WelcomeActivity.class);
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

    private void showActivity(Class whereto) {
        Intent intent = new Intent(LoginActivity.this, whereto);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imv_back)
    public void backClick() {
        showWelcomeView();
    }

    @OnClick(R.id.btn_sign_in)
    public void signInClicked() {
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();
        mLoginPresenter.loginUser(email, password);
    }
}
