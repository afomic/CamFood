package afomic.com.camfood.ui.createFood;

import java.util.List;

import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.ui.base.BaseView;
import afomic.com.camfood.ui.base.LoadingBaseView;

public interface CreateFoodView extends LoadingBaseView {

    void showFoodTopping(List<FoodTopping> foodToppings);

    void selectImageFromPhone();

    void showHome();

}
