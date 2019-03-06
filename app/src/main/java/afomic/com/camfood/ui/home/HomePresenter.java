package afomic.com.camfood.ui.home;

import afomic.com.camfood.R;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.ui.base.BasePresenter;

public class HomePresenter extends BasePresenter<HomeView> {
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public HomePresenter(HomeView view, SharedPreferenceHelper sharedPreferenceHelper) {
        super(view);
        this.mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    @Override
    public void loadView() {
        view.showFoodView();
    }

    public void handleNavItemSelected(int id) {
        switch (id) {
            case R.id.menu_home:
                view.showFoodView();
                break;
            case R.id.menu_order:
                view.showOrderView();
                break;
            case R.id.menu_profile:
                view.showProfileView();
                break;
        }
    }

}
