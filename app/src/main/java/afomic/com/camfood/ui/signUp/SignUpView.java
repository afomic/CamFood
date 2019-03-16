package afomic.com.camfood.ui.signUp;

import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface SignUpView extends LoadingBaseView {
    void showHomeView();

    void showWelcomeView();

    void showRestaurantRegistrationField();

    void setTitle(int stringId);
}
