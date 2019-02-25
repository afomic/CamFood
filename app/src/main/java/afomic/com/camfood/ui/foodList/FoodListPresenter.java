package afomic.com.camfood.ui.foodList;

import java.util.List;

import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.DataSourceCallback;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodListPresenter extends BasePresenter<FoodListView> {
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private boolean restaurantAccount;
    private DataSource<Food> mFoodDataSource;

    public FoodListPresenter(FoodListView view, SharedPreferenceHelper sharedPreferenceHelper, DataSource<Food> dataSource) {
        super(view);
        mSharedPreferenceHelper = sharedPreferenceHelper;
        this.restaurantAccount = sharedPreferenceHelper.getBooleanPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE);
        mFoodDataSource = dataSource;
    }

    @Override
    public void loadView() {
        mFoodDataSource.getData(0, new DataSourceCallback<Food>() {
            @Override
            public void onSuccess(List<Food> data) {
                view.showFood(data);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
