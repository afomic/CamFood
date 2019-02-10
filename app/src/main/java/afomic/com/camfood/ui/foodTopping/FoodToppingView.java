package afomic.com.camfood.ui.foodTopping;

import java.util.List;

import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BaseView;

public interface FoodToppingView extends BaseView {
    void showOrderCheckoutView(Food food);

    void showFood(Food food);

    void showTopping(List<Object> toppings);

}
