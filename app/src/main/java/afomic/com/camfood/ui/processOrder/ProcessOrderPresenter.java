package afomic.com.camfood.ui.processOrder;

import afomic.com.camfood.Constants;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.ResponseCallback;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.ui.base.BasePresenter;

public class ProcessOrderPresenter extends BasePresenter<ProcessOrderView> {
    private Order mOrder;
    private DataSource<Order> mOrderDataSource;

    public ProcessOrderPresenter(ProcessOrderView view, Order order, DataSource<Order> orderDataSource) {
        super(view);
        mOrder = order;
        mOrderDataSource = orderDataSource;
    }

    @Override
    public void loadView() {
        view.showOrder(mOrder);
        view.showOrderList(mOrder.getOrderItems());
    }

    public void handleOrderAccepted() {
        updateStatus(Constants.ORDER_STATUS_ACCEPTED, "Order Accepted");
    }

    public void handleOrderCancelled() {
        updateStatus(Constants.ORDER_STATUS_DECLINED, "Order Declined");
    }

    public void handleOrderDelivered() {
        updateStatus(Constants.ORDER_STATUS_DELIVERED, "Marked as Delivered");
    }

    public void handleStatProcessing() {
        updateStatus(Constants.ORDER_STATUS_PROCESSING, "Processing of Order started ");
    }

    private void updateStatus(int status, final String sucessMessage) {
        view.showProgressView();
        mOrder.addStatus(new OrderStatus(status, System.currentTimeMillis()));
        mOrderDataSource.update(mOrder, new ResponseCallback() {
            @Override
            public void onSuccess() {
                view.hideProgressView();
                view.showMessage(sucessMessage);
                view.showOrder(mOrder);
            }

            @Override
            public void onFailure(String reason) {
                view.hideProgressView();
                view.showMessage(reason);

            }
        });
    }
}
