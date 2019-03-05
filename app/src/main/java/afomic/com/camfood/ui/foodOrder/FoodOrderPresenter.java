package afomic.com.camfood.ui.foodOrder;

import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.DataSourceCallback;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodOrderPresenter extends BasePresenter<FoodOrderView> {
    private DataSource<Order> mDataSource;
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public FoodOrderPresenter(FoodOrderView view, DataSource<Order> foodOrderDataSource, SharedPreferenceHelper sharedPreferenceHelper) {
        super(view);
        mDataSource = foodOrderDataSource;
        mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    @Override
    public void loadView() {
        view.showProgressView();
        mDataSource.getData(mSharedPreferenceHelper.getIntegerPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE),
                new DataSourceCallback<Order>() {
                    @Override
                    public void onSuccess(List<Order> data) {
                        view.hideEmptyView();
                        if (data != null && data.size() > 0) {
                            view.showFoodOrder(data);
                        } else {
                            view.showEmptyView();
                        }

                    }

                    @Override
                    public void onFailure(String reason) {
                        view.hideEmptyView();
                        view.showMessage(reason);
                    }
                });
    }

    public void handleFoodOrderClicked(Order order) {
        view.showFoodOrderDetailView(order);

    }

    public boolean isRestaurantAccount() {
        return mSharedPreferenceHelper.getIntegerPref(SharedPreferenceHelper.PREF_RESTAURANT_ACCOUNT_TYPE) == Constants.RESTAURANT_ACCOUNT_TYPE;
    }
}
