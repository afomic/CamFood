package afomic.com.camfood.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseFragment;
import afomic.com.camfood.ui.editProfile.EditProfileActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements ProfileView {
    @BindView(R.id.tv_name)
    TextView nameTextView;
    @BindView(R.id.tv_email)
    TextView emailTextView;
    @BindView(R.id.tv_address)
    TextView addressTextView;
    @BindView(R.id.tv_phone_number)
    TextView phoneNumberTextView;
    @BindView(R.id.tv_balance)
    TextView accountBalanceTextView;
    @BindView(R.id.address_layout)
    LinearLayout addressLayout;
    @BindView(R.id.btn_fund_wallet)
    Button fundWalletButton;

    private ProfilePresenter mProfilePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mProfilePresenter = new ProfilePresenter(this, AuthManager.getInstance());
        mProfilePresenter.loadView();
    }

    @Override
    public void setup() {

    }

    @Override
    public void setUpCustomerProfile() {
        addressLayout.setVisibility(View.GONE);
        fundWalletButton.setText(getString(R.string.fund_wallet_button_title));
        fundWalletButton.setBackgroundColor(getResources().getColor(R.color.acceptedColor));
    }

    @Override
    public void showProfile(User user) {
        nameTextView.setText(user.name);
        emailTextView.setText(user.email);
        addressTextView.setText(user.address);
        phoneNumberTextView.setText(user.phonNumber);
        String balance = getString(R.string.naira) + NumberFormat.getNumberInstance().format(user.accountBalance);
        accountBalanceTextView.setText(balance);

    }

    @Override
    public void showEditProfileView() {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_edit_profile)
    public void onEditProfileClicked() {
        mProfilePresenter.handleEditProfile();

    }

    @Override
    public void showFundWalletDialog() {


    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void hideProgressView() {

    }

    @OnClick(R.id.btn_fund_wallet)
    public void fundWallet() {

    }
}
