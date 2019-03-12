package afomic.com.camfood.ui.editProfile;

import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseView;

public interface EditProfileView extends BaseView {


    void showUser(User user);

    void setUpCustomerView();

    String getName();

    String getAddress();

    String getPhoneNumber();

    void finishActivity();

}
