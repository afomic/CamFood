package afomic.com.camfood.ui.orderDetail;

import java.util.Collections;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.ResponseCallback;
import afomic.com.camfood.helper.OrderHelper;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    private Order mOrder;
    private DataSource<Order> mOrderDataSource;
    private AuthManager authManager;

    public OrderDetailPresenter(OrderDetailView view, Order order, DataSource<Order> orderDataSource) {
        super(view);
        this.mOrder = order;
        mOrderDataSource = orderDataSource;
    }

    public void setAuthManager(AuthManager authManager) {
        this.authManager = authManager;
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
                authManager.getUser(mOrder.getRestaurantId(), new AuthManager.AuthCallback() {
                    @Override
                    public void onSuccess(User user) {
                        int amount= OrderHelper.getTotalAmount(mOrder.getOrderItems());
                        fundRestuarant(user, amount);
                    }

                    @Override
                    public void onError(String reason) {
                        view.showMessage(reason);
                    }
                });
            }

            @Override
            public void onFailure(String reason) {
                view.hideProgressView();
                view.showMessage(reason);

            }
        });
    }

    private void fundRestuarant(User restaurant, int amount) {
        restaurant.accountBalance = restaurant.accountBalance + amount;
        authManager.updateUser(restaurant, new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                view.hideProgressView();
                view.showMessage("Order Marked As Completed");
                loadView();
            }

            @Override
            public void onError(String reason) {
                view.showMessage(reason);
            }
        });


    }
}
