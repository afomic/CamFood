package afomic.com.camfood.ui.foodList;

import java.util.List;

import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BaseView;

public interface FoodListView extends BaseView {
    void showFood(List<Food> foodList);
    void showEmptyView();
    void hideEmptyView();
    void showFoodToppingView(Food food);

}
