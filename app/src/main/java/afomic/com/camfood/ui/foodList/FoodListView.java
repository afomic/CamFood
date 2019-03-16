package afomic.com.camfood.ui.foodList;

import java.util.List;

import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface FoodListView extends LoadingBaseView {
    void showFood(List<Food> foodList);
    void showEmptyView();
    void hideEmptyView();
    void showFoodToppingView(Food food);

}
