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

    public void handleUserUpdate(){

    }
}
