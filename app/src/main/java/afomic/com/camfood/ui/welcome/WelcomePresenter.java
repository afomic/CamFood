package afomic.com.camfood.ui.welcome;

import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.ui.base.BasePresenter;

public class WelcomePresenter extends BasePresenter<WelcomeView> {
    public WelcomePresenter(WelcomeView view) {
        super(view);
    }

    @Override
    public void loadView() {

    }
    public void handleSkipClick(){
        view.showHomeView();
    }
    public void handleCustomerRegistrationClick(){
        view.showCustomerRegistrationView();
    }
    public void handleRestaurantRegistrationClick(){
        view.showRestaurantRegistrationView();
    }

}
