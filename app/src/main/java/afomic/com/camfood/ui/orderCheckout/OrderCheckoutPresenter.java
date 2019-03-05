package afomic.com.camfood.ui.orderCheckout;

import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.ResponseCallback;
import afomic.com.camfood.helper.OrderHelper;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class OrderCheckoutPresenter extends BasePresenter<OrderCheckoutView> {
    private Order mOrder;
    private AuthManager mAuthManager;
    private DataSource<Order> mOrderDataSource;

    public OrderCheckoutPresenter(OrderCheckoutView view, Order order) {
        super(view);
        mOrder = order;
    }

    public void setAuthManager(AuthManager authManager) {
        mAuthManager = authManager;
    }

    public void setOrderDataSource(DataSource<Order> orderDataSource) {
        mOrderDataSource = orderDataSource;
    }

    @Override
    public void loadView() {
        view.showTotalAmount(OrderHelper.getTotalAmountString(mOrder.getOrderItems()));
        view.showOrderItem(mOrder.getOrderItems());
    }

    public void handleOrderItemClick(OrderItem orderItem) {
        view.showOrderItemQuantityDialog(orderItem);
    }

    public void handleOrderItemDelete(OrderItem orderItem) {
        List<OrderItem> orderItems = mOrder.getOrderItems();
        orderItems.remove(orderItem);
        view.showOrderItem(orderItems);
        view.showTotalAmount(OrderHelper.getTotalAmountString(orderItems));
    }

    public void handleOrderItemUpdate() {
        view.showTotalAmount(OrderHelper.getTotalAmountString(mOrder.getOrderItems()));
        view.showOrderItem(mOrder.getOrderItems());
    }

    public void handleLocationSelected(String location) {
        mOrder.setLocation(location);
    }

    public void handleCheckoutOrder() {
        if (mOrder.getLocation() == null) {
            view.showMessage("Select pick up location");
            return;
        }
        view.showProgressView();
        mOrder.addStatus(new OrderStatus(Constants.ORDER_STATUS_CREATED, System.currentTimeMillis()));
        mAuthManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                mOrder.setUserId(user.id);
                mOrder.setUserPhoneNumber(user.phonNumber);
                mOrder.setUserName(user.name);
                saveOrder();
            }

            @Override
            public void onError(String reason) {

            }
        });


    }

    public void saveOrder() {
        mOrderDataSource.save(mOrder, new ResponseCallback() {
            @Override
            public void onSuccess() {
                view.hideProgressView();
                view.showHome();
            }

            @Override
            public void onFailure(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });
    }

}
