package afomic.com.camfood.ui.editProfile;

import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class EditProfilePresenter extends BasePresenter<EditProfileView> {
    private AuthManager authManager;

    public EditProfilePresenter(EditProfileView view, AuthManager authManager) {
        super(view);
        this.authManager = authManager;
    }

    @Override
    public void loadView() {

    }

    public void handleUserUpdate() {

        if (view.getName().isEmpty()) {
            view.showMessage("Name cannot be empty");
            return;
        }
        if (view.getPhoneNumber().isEmpty()) {
            view.showMessage("Phone number cannot be empty");
            return;
        }
        view.showProgressView();
        authManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                view.hideProgressView();
                user.name = view.getName();
                user.address = view.getAddress();
                user.phonNumber = view.getPhoneNumber();
                authManager.updateUser(user, new AuthManager.AuthCallback() {
                    @Override
                    public void onSuccess(User user) {
                        view.hideProgressView();
                    }

                    @Override
                    public void onError(String reason) {
                        view.hideProgressView();
                        view.showMessage(reason);
                    }
                });
            }

            @Override
            public void onError(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });
    }
}
