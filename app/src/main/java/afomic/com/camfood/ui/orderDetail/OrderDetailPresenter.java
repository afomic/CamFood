package afomic.com.camfood.ui.orderDetail;

import java.util.Collections;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.ui.base.BasePresenter;

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    private Order mOrder;

    public OrderDetailPresenter(OrderDetailView view, Order order) {
        super(view);
        this.mOrder = order;
    }

    @Override
    public void loadView() {
        OrderItem foodOrder = mOrder.getOrderItems().get(0);
        view.showOrderImage(foodOrder.getPictureUrl());
        List<OrderStatus> statusList = mOrder.getStatus();
        if (statusList.get(0).getType() == Constants.ORDER_STATUS_DELIVERED) {
            view.showFinishedButton();
        }
        Collections.reverse(statusList);
        view.showStatus(statusList);
    }

    public void handleApproved() {

    }
}
