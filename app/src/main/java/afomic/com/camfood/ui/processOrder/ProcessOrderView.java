package afomic.com.camfood.ui.processOrder;

import java.util.List;

import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.ui.base.BaseView;

public interface ProcessOrderView extends BaseView {
    void showOrder(Order order);

    void showOrderList(List<OrderItem> orderItems);
}
