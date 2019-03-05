package afomic.com.camfood.ui.orderDetail;

import java.util.Collections;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.ResponseCallback;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.ui.base.BasePresenter;

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    private Order mOrder;
    private DataSource<Order> mOrderDataSource;

    public OrderDetailPresenter(OrderDetailView view, Order order, DataSource<Order> orderDataSource) {
        super(view);
        this.mOrder = order;
        mOrderDataSource = orderDataSource;
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
        view.showProgressView();
        mOrder.addStatus(new OrderStatus(Constants.ORDER_STATUS_FINISHED, System.currentTimeMillis()));
        mOrderDataSource.update(mOrder, new ResponseCallback() {
            @Override
            public void onSuccess() {
                view.hideProgressView();
                view.showMessage("Order Marked As Completed");
                loadView();
            }

            @Override
            public void onFailure(String reason) {
                view.hideProgressView();
                view.showMessage(reason);

            }
        });
    }
}
