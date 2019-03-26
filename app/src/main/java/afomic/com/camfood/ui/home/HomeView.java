package afomic.com.camfood.ui.home;

import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface HomeView extends LoadingBaseView {
    void showFoodView();

    void showOrderView();

    void showProfileView();

    void showAddFoodView();

    void showLoginView();
}
