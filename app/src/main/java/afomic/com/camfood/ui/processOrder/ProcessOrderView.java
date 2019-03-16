package afomic.com.camfood.ui.processOrder;

import java.util.List;

import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface ProcessOrderView extends LoadingBaseView {
    void showOrder(Order order);

    void showOrderList(List<OrderItem> orderItems);
}
