package afomic.com.camfood.ui.foodOrder;

import java.util.List;

import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface FoodOrderView extends LoadingBaseView {
    void showFoodOrder(List<Order> orders);

    void showFoodOrderDetailView(Order order);

    void showEmptyView();

    void hideEmptyView();
}
