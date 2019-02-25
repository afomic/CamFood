package afomic.com.camfood.ui.createFood;

import java.util.List;

import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.ui.base.BaseView;

public interface CreateFoodView extends BaseView {

    void showFoodTopping(List<FoodTopping> foodToppings);

    void selectImageFromPhone();


}
