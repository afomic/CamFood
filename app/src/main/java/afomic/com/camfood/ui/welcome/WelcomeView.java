package afomic.com.camfood.ui.welcome;

import afomic.com.camfood.ui.base.BaseView;

public interface WelcomeView extends BaseView {
    void showHomeView();
    void showLoginView();
    void showCustomerRegistrationView();
    void showRestaurantRegistrationView();
}
