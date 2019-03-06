package afomic.com.camfood.ui.profile;

import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseView;

public interface ProfileView extends BaseView {
    void showProfile(User user);

    void showEditProfileView();
}
