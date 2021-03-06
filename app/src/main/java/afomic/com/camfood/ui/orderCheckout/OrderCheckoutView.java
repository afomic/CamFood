package afomic.com.camfood.ui.orderCheckout;

import java.util.List;

import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface OrderCheckoutView extends LoadingBaseView {
    void showTotalAmount(String amount);

    void showOrderItem(List<OrderItem> orderItems);

    void showOrderItemQuantityDialog(OrderItem orderItem);

    void showPaymentView();

    void showHome();
}
