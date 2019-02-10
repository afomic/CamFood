package afomic.com.camfood.ui.home;

import afomic.com.camfood.ui.base.BasePresenter;

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    public void loadView() {
        view.showFoodView();
    }
}
