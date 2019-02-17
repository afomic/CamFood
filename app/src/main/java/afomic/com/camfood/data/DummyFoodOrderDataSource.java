package afomic.com.camfood.data;


import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.model.Order;

public class DummyFoodOrderDataSource implements DataSource<Order> {
    List<Order> mOrders;

    public DummyFoodOrderDataSource() {
        mOrders = new ArrayList<>();
    }

    @Override
    public void getData(int pageNumber, DataSourceCallback<Order> callback) {
        callback.onSuccess(mOrders);

    }

    @Override
    public void save(List<Order> data) {

    }

    @Override
    public void save(Order data) {

    }

    @Override
    public void update(Order data) {

    }

    @Override
    public void delete(Order data) {

    }
}
