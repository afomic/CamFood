package afomic.com.camfood.ui.foodOrder;

import java.util.List;

import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.DataSourceCallback;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodOrderPresenter extends BasePresenter<FoodOrderView> {
    private DataSource<Order> mDataSource;

    public FoodOrderPresenter(FoodOrderView view, DataSource<Order> foodOrderDataSource) {
        super(view);
        mDataSource = foodOrderDataSource;
    }

    @Override
    public void loadView() {
        mDataSource.getData(0, new DataSourceCallback<Order>() {
            @Override
            public void onSuccess(List<Order> data) {
                if (data != null && data.size() > 0) {
                    view.showFoodOrder(data);
                } else {
                    view.showEmptyView();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void handleFoodOrderClicked(Order order) {
        view.showFoodOrderDetailView(order);

    }
}
