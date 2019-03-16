package afomic.com.camfood.ui.orderDetail;

import java.util.List;

import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface OrderDetailView extends LoadingBaseView {
    void showOrderImage(String pictureUrl);

    void showStatus(List<OrderStatus> statusList);

    void showFinishedButton();

    void hideFinishedButton();
}
