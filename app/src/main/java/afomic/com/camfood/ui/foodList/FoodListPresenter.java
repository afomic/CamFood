package afomic.com.camfood.ui.foodList;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodListPresenter extends BasePresenter<FoodListView> {
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private int userType;

    public FoodListPresenter(FoodListView view, SharedPreferenceHelper sharedPreferenceHelper) {
        super(view);
        mSharedPreferenceHelper = sharedPreferenceHelper;
        this.userType = sharedPreferenceHelper.getIntegerPref(SharedPreferenceHelper.PREF_ACCOUNT_TYPE);
    }

    @Override
    public void loadView() {
        view.showFood(getDummyFood());
    }

    private List<Food> getDummyFood() {
        List<Food> foods=new ArrayList<>();
        foods.add(new Food());
        foods.add(new Food());
        return foods;
    }
}
