package afomic.com.camfood.ui.createFood;

import android.net.Uri;

import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.ui.base.BasePresenter;

public class CreateFoodPresenter extends BasePresenter<CreateFoodView> {
    private ToppingHelper mToppingHelper;

    public CreateFoodPresenter(CreateFoodView view, ToppingHelper toppingHelper) {
        super(view);
        mToppingHelper = toppingHelper;
    }

    @Override
    public void loadView() {
        view.showFoodTopping(mToppingHelper.foodToppings);

    }

    public void handleToppingSelected(FoodTopping topping) {

    }

    public void handleToppingRemoved(FoodTopping topping) {

    }

    public void handleImageViewSelected(Uri uri) {

    }
}
