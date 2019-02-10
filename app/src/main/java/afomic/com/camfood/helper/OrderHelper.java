package afomic.com.camfood.helper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.model.Food;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.model.OrderItem;

public class OrderHelper {

    public static List<OrderItem> getOrderItems(Food food, List<FoodTopping> selectedToppings) {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem foodOrder = new OrderItem();
        foodOrder.setAmount(food.getAmount());
        foodOrder.setName(food.getName());
        foodOrder.setQuantity(1);
        foodOrder.setPictureUrl(food.getPictureUrl());
        orderItems.add(foodOrder);
        for (FoodTopping foodTopping : selectedToppings) {
            OrderItem item = new OrderItem();
            item.setQuantity(1);
            item.setName(foodTopping.getName());
            item.setPictureUrl(foodTopping.getPictureUrl());
            item.setAmount(foodTopping.getPrice());
            orderItems.add(item);
        }
        return orderItems;
    }

    public static String getTotalAmount(List<OrderItem> orderItems) {
        int totalAmount = 0;
        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.getAmount() * orderItem.getQuantity();
        }
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return "₦" + numberFormat.format(totalAmount);
    }
}
