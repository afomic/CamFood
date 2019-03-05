package afomic.com.camfood.ui.foodTopping;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.helper.OrderHelper;
import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.model.FoodToppingTitle;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.base.BasePresenter;

public class FoodToppingPresenter extends BasePresenter<FoodToppingView> {
    private Food mFood;
    private List<FoodTopping> selectedToppings;
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private ToppingHelper mToppingHelper;

    public FoodToppingPresenter(FoodToppingView view, Food food, SharedPreferenceHelper sharedPreferenceHelper, ToppingHelper toppingHelper) {
        super(view);
        mFood = food;
        selectedToppings = new ArrayList<>();
        mToppingHelper = toppingHelper;
        mSharedPreferenceHelper = sharedPreferenceHelper;
    }

    @Override
    public void loadView() {
        view.showFood(mFood);
        List<Object> toppings = new ArrayList<>();
        if(mFood.getFoodToppings()!=null){
            toppings.add(new FoodToppingTitle("Select Food Topping"));
            toppings.addAll(mFood.getFoodToppings());
        }
        toppings.add(new FoodToppingTitle("Select Refreshment"));
        toppings.addAll(mToppingHelper.refreshments);
        view.showTopping(toppings);
    }

    public void handleCreateOrder() {
        Order order = new Order();
        order.setOrderItems(OrderHelper.getOrderItems(mFood, selectedToppings));
        order.setRestaurantId(mFood.getRestaurantId());
        order.setUserId(mSharedPreferenceHelper.getStringPref(SharedPreferenceHelper.PREF_USER_ID));
        order.setUserName(mSharedPreferenceHelper.getStringPref(SharedPreferenceHelper.PREF_USER_NAME));
        order.setUserPhoneNumber(mSharedPreferenceHelper.getStringPref(SharedPreferenceHelper.PREF_USER_PHONE_NUMBER));
        view.showOrderCheckoutView(order);

    }

    public void handleToppingSelected(FoodTopping topping) {
        selectedToppings.add(topping);
    }

    public void handleToppingRemoved(FoodTopping topping) {
        selectedToppings.remove(topping);
    }
}
