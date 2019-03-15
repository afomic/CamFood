package afomic.com.camfood.ui.login;

import java.util.regex.Pattern;

import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {
    private AuthManager mAuthManager;
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public LoginPresenter(LoginView view, AuthManager authManager, SharedPreferenceHelper sharedPreferenceHelper) {
        super(view);
        mAuthManager = authManager;
        mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    @Override
    public void loadView() {

    }

    public void loginUser(String email, String password) {

        if(email.isEmpty()){
            view.showMessage("please provide email address");
            return;
        }
        if(password.isEmpty()){
            view.showMessage("please provide password address");
            return;
        }
        view.showProgressView();
        mAuthManager.login(email, password, new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                view.hideProgressView();
                mSharedPreferenceHelper.saveIntegerPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE,
                        user.userType);
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
