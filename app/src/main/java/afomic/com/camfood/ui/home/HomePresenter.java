package afomic.com.camfood.ui.home;

import afomic.com.camfood.R;
import afomic.com.camfood.ui.base.BasePresenter;

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void loadView() {
        view.showFoodView();
    }

    public void handleNavItemSelected(int id){
        switch (id){
            case R.id.menu_home:
                view.showFoodView();
                break;
            case R.id.menu_order:
                view.showOrderView();
                break;
        }
    }
}
