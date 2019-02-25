package afomic.com.camfood.ui.orderDetail;

import java.util.List;

import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.ui.base.BaseView;

public interface OrderDetailView extends BaseView {
    void showOrderImage(String pictureUrl);

    void showStatus(List<OrderStatus> statusList);

    void showFinishedButton();

    void hideFinishedButton();
}
