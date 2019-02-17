package afomic.com.camfood.data;


import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.model.OrderStatus;

public class DummyFoodOrderDataSource implements DataSource<Order> {
    List<Order> mOrders;

    public DummyFoodOrderDataSource() {
        mOrders = new ArrayList<>();
    }

    @Override
    public void getData(int pageNumber, DataSourceCallback<Order> callback) {
        OrderStatus createStatus=new OrderStatus(Constants.ORDER_STATUS_CREATED,System.currentTimeMillis(),"wewe");
        OrderStatus acceptStatus=new OrderStatus(Constants.ORDER_STATUS_ACCEPTED,System.currentTimeMillis(),"wewe");
        OrderStatus declineStatus=new OrderStatus(Constants.ORDER_STATUS_DECLINED,System.currentTimeMillis(),"wewe");
        Order order =new Order();
        order.setUserPhoneNumber("2348174442314");
        order.setUserName("afomic");
        order.setAmount(2000);
        order.setRestaurantId("eyeycb");
        order.setLocation("Awolowo Hall");
        order.addStatus(createStatus);
        order.addStatus(acceptStatus);
        List<OrderItem> orderItems=new ArrayList<>();
        OrderItem item=new OrderItem();

        item.setAmount(1000);
        item.setQuantity(1);
        item.setName("Fried Rice and Shrimp");
        item.setPictureUrl("https://www.melaniecooks.com/wp-content/uploads/2009/07/shrimp-fried-rice1.jpg");
        orderItems.add(item);
        order.setOrderItems(orderItems);


        Order order1 =new Order();
        order1.setUserPhoneNumber("2348174442314");
        order1.setUserName("afomic");
        order1.setAmount(1000);
        order1.setRestaurantId("eyeycb");
        order1.setLocation("Awolowo Hall");
        order1.addStatus(createStatus);
        order1.addStatus(declineStatus);
        List<OrderItem> orderItems1=new ArrayList<>();
        OrderItem item1=new OrderItem();

        item1.setAmount(1000);
        item1.setQuantity(1);
        item1.setName("Amala Dudu");
        item1.setPictureUrl("https://i0.wp.com/www.mydiasporakitchen.com/wp-content/uploads/2018/09/savingpng-233.jpg");
        orderItems1.add(item1);
        order1.setOrderItems(orderItems1);



        mOrders.add(order);
        mOrders.add(order1);
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
