package afomic.com.camfood.ui.login;

import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface LoginView extends LoadingBaseView {
    void showHomeView();

    void showWelcomeView();
}
