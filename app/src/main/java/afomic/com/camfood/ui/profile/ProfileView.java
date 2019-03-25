package afomic.com.camfood.ui.profile;

import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface ProfileView extends LoadingBaseView {
    void showProfile(User user);

    void showEditProfileView();

    void setUpCustomerProfile();

    void showFundWalletDialog();
}
