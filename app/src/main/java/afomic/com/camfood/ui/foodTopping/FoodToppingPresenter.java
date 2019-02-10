package afomic.com.camfood.ui.foodTopping;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.model.FoodToppingTitle;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodToppingPresenter extends BasePresenter<FoodToppingView> {
    private Food mFood;
    private List<FoodTopping> selectedToppings;

    public FoodToppingPresenter(FoodToppingView view, Food food) {
        super(view);
        mFood = food;
        selectedToppings = new ArrayList<>();
    }

    @Override
    public void loadView() {
        view.showFood(mFood);
        List<Object> toppings = new ArrayList<>();
        toppings.add(new FoodToppingTitle("Select Food Topping"));
        toppings.addAll(ToppingHelper.getRefreshment());
        toppings.add(new FoodToppingTitle("Select Refreshment"));
        toppings.addAll(ToppingHelper.getRefreshment());
        view.showTopping(toppings);
    }

    public void handleCreateOrder() {

    }

    public void handleToppingSelected(FoodTopping topping) {
        selectedToppings.add(topping);
    }

    public void handleToppingRemoved(FoodTopping topping) {
        selectedToppings.remove(topping);
    }
}
