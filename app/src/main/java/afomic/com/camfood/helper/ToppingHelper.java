package afomic.com.camfood.helper;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.model.FoodTopping;

public class ToppingHelper {
    public static List<FoodTopping> getRefreshment() {
        List<FoodTopping> refreshments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            FoodTopping foodTopping = new FoodTopping();
            foodTopping.setName("CocaCola");
            foodTopping.setPrice(100);
            foodTopping.setType(FoodTopping.TYPE_REFRESHMENT);
            refreshments.add(foodTopping);
        }
        return refreshments;
    }
}
