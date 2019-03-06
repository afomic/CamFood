package afomic.com.camfood.ui.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements ProfileView {
    @BindView(R.id.tv_name)
    TextView nameTextView;
    @BindView(R.id.tv_email)
    TextView emailTextView;
    @BindView(R.id.tv_address)
    TextView addressTextView;
    @BindView(R.id.tv_phone_number)
    TextView phoneNumberTextView;

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
    public void showProfile(User user) {
        nameTextView.setText(user.name);
        emailTextView.setText(user.email);
        addressTextView.setText(user.address);
        phoneNumberTextView.setText(user.phonNumber);
    }

    @Override
    public void showEditProfileView() {

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void hideProgressView() {

    }
}
