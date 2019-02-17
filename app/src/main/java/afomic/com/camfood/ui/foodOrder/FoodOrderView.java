package afomic.com.camfood.ui.foodOrder;

import java.util.List;

import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.base.BaseView;

public interface FoodOrderView extends BaseView {
    void showFoodOrder(List<Order> orders);

    void showFoodOrderDetailView(Order order);

    void showEmptyView();

    void hideEmptyView();
}
