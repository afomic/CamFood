package afomic.com.camfood.ui.signUp;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class SignUpPresenter extends BasePresenter<SignUpView> {
    private int registrationType;
    private AuthManager mAuthManager;
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public SignUpPresenter(SignUpView view, AuthManager authManager, SharedPreferenceHelper sharedPreferenceHelper) {
        super(view);
        mAuthManager = authManager;
        mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    public void setRegistrationType(int registrationType) {
        this.registrationType = registrationType;
    }

    @Override
    public void loadView() {
        if (registrationType == Constants.RESTAURANT_REGISTRATION_TYPE) {
            view.showRestaurantRegistrationField();
            view.setTitle(R.string.restaurant_sign_up_title);
        } else {
            view.setTitle(R.string.customer_sign_up_title);
        }
    }

    public void signUp(User user, String password) {
        user.userType = registrationType;
        view.showProgressView();
        mAuthManager.createUser(user, password, new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                mSharedPreferenceHelper.saveIntegerPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE, user.userType);
                mSharedPreferenceHelper.saveStringPref(SharedPreferenceHelper.PREF_USER_ID, user.id);
                mSharedPreferenceHelper.saveBooleanPref(SharedPreferenceHelper.PREF_USER_EXIST,true);
                view.showHomeView();
            }

            @Override
            public void onError(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });


    }
}
