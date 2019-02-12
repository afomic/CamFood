package afomic.com.camfood.ui.login;

import afomic.com.camfood.ui.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    @Override
    public void loadView() {

    }

    public void loginUser(String email, String password) {
        view.showHomeView();
    }
}
