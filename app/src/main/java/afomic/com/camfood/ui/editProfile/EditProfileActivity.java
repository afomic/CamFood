package afomic.com.camfood.ui.editProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends BaseActivity implements EditProfileView {
    @BindView(R.id.edt_username)
    EditText usernameEditText;

    @BindView(R.id.edt_phone_number)
    EditText phoneNumberEditText;

    @BindView(R.id.edt_address)
    EditText addressEditText;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private EditProfilePresenter editProfilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        editProfilePresenter = new EditProfilePresenter(this, AuthManager.getInstance());
        editProfilePresenter.loadView();
    }

    @Override
    public void setup() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Edit Profile");
    }

    @Override
    public void showUser(User user) {
        addressEditText.setText(user.address);
        phoneNumberEditText.setText(user.phonNumber);
        usernameEditText.setText(user.name);
    }

    @Override
    public void setUpCustomerView() {
        addressEditText.setVisibility(View.GONE);
    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void hideProgressView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_update_profile)
    public void onUpdateClicked() {
        editProfilePresenter.handleUserUpdate();
    }

    @Override
    public String getName() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getAddress() {
        return addressEditText.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumberEditText.getText().toString();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
