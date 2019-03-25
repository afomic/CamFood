package afomic.com.camfood.ui.profile;

import afomic.com.camfood.Constants;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class ProfilePresenter extends BasePresenter<ProfileView> {
    private AuthManager mAuthManager;

    public ProfilePresenter(ProfileView view, AuthManager authManager) {
        super(view);
        mAuthManager = authManager;
    }

    @Override
    public void loadView() {
        view.showProgressView();
        mAuthManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                view.hideProgressView();
                view.showProfile(user);
                if (user.userType != Constants.RESTAURANT_ACCOUNT_TYPE) {
                    view.setUpCustomerProfile();
                }
            }

            @Override
            public void onError(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });
    }

    public void handleEditProfile() {
        view.showEditProfileView();
    }
    public void handleFundWallet(){

    }
}
