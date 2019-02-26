package afomic.com.camfood.ui.signUp;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class SignUpPresenter extends BasePresenter<SignUpView> {
    private int registrationType;

    public SignUpPresenter(SignUpView view, int registrationType) {
        super(view);
        this.registrationType = registrationType;
    }

    @Override
    public void loadView() {
        if (registrationType == Constants.RESTAURANT_REGISTRATION_TYPE) {
            view.showRestaurantRegistrationField();
            view.setTitle(R.string.restaurant_sign_up_title);
        }else {
            view.setTitle(R.string.customer_sign_up_title);
        }
    }

    public void signUp(User user, String password) {
        view.showHomeView();
    }
}
