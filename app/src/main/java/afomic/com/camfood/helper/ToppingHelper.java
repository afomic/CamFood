package afomic.com.camfood.helper;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.model.ConfigResponse;
import afomic.com.camfood.model.FoodTopping;

public class ToppingHelper {
    private static ToppingHelper sToppingHelper;
    public List<FoodTopping> refreshments, foodToppings;

    public static ToppingHelper getInstance(Context context) {
        if (sToppingHelper == null) {
            sToppingHelper = new ToppingHelper(context.getApplicationContext());
        }
        return sToppingHelper;
    }

    private ToppingHelper(Context context) {
        refreshments = new ArrayList<>();
        foodToppings = new ArrayList<>();
        loadFromJson(context);
    }

    public void loadFromJson(Context context) {
        String config = Common.loadJSONFromAsset(context, "Config.json");
        ConfigResponse configResponse = (ConfigResponse) Common.parseJSONToObject(config, TypeToken.get(ConfigResponse.class));
        for (FoodTopping topping : configResponse.mFoodToppings) {
            if (topping.getType() == FoodTopping.TYPE_REFRESHMENT) {
                refreshments.add(topping);
            }else {
                foodToppings.add(topping);
            }
        }

    }
}
