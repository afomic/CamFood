package afomic.com.camfood.ui.processOrder;

import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.base.BasePresenter;

public class ProcessOrderPresenter extends BasePresenter<ProcessOrderView> {
    private Order mOrder;

    public ProcessOrderPresenter(ProcessOrderView view, Order order) {
        super(view);
        mOrder = order;
    }

    @Override
    public void loadView() {
        view.showOrder(mOrder);
        view.showOrderList(mOrder.getOrderItems());
    }
    public void handleOrderAccepted(){

    }
    public void handleOrderCancelled(){

    }
    public void handleOrderDelivered(){

    }
}
