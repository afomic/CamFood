package afomic.com.camfood.ui.foodList;

import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.DataSourceCallback;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodListPresenter extends BasePresenter<FoodListView> {
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private DataSource<Food> mFoodDataSource;

    public FoodListPresenter(FoodListView view, SharedPreferenceHelper sharedPreferenceHelper,
                             DataSource<Food> dataSource) {
        super(view);
        mSharedPreferenceHelper = sharedPreferenceHelper;
        mFoodDataSource = dataSource;
    }

    @Override
    public void loadView() {
        view.showProgressView();
        mFoodDataSource.getData(mSharedPreferenceHelper.getIntegerPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE),
                new DataSourceCallback<Food>() {
                    @Override
                    public void onSuccess(List<Food> data) {
                        view.hideEmptyView();
                        view.hideProgressView();
                        if (data.isEmpty()) {
                            view.showEmptyView();
                        } else {
                            view.showFood(data);
                        }
                    }

                    @Override
                    public void onFailure(String reason) {
                        view.showEmptyView();
                        view.hideProgressView();
                        view.showMessage(reason);
                    }
                });

    }

    public boolean isRestaurantAccount() {
        return mSharedPreferenceHelper.getIntegerPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE)
                == Constants.RESTAURANT_ACCOUNT_TYPE;
    }

}
