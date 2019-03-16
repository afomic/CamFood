package afomic.com.camfood.ui.editProfile;

import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface EditProfileView extends LoadingBaseView {


    void showUser(User user);

    void setUpCustomerView();

    String getName();

    String getAddress();

    String getPhoneNumber();

    void finishActivity();

}
