package afomic.com.camfood.ui.foodList;

import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodListPresenter extends BasePresenter<FoodListView> {
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private int userType;

    public FoodListPresenter(FoodListView view, SharedPreferenceHelper sharedPreferenceHelper, int userType) {
        super(view);
        mSharedPreferenceHelper = sharedPreferenceHelper;
        this.userType = userType;
    }

    @Override
    public void loadView() {

    }
}
