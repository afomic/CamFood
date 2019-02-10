package afomic.com.camfood.ui.orderCheckout;

import afomic.com.camfood.helper.OrderHelper;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.ui.base.BasePresenter;

public class OrderCheckoutPresenter extends BasePresenter<OrderCheckoutView> {
    private Order mOrder;

    public OrderCheckoutPresenter(OrderCheckoutView view, Order order) {
        super(view);
        mOrder = order;
    }

    @Override
    public void loadView() {
        view.showTotalAmount(OrderHelper.getTotalAmountString(mOrder.getOrderItems()));
        view.showOrderItem(mOrder.getOrderItems());
    }

    public void handleOrderItemClick(OrderItem orderItem) {

    }

    public void handleLocationSelected(String location) {

    }

    public void handleCheckoutOrder() {

    }

}
