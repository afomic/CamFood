package afomic.com.camfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ConfigResponse {
    @Expose
    @SerializedName("food_topping")
    public List<FoodTopping> mFoodToppings = new ArrayList<>();
}
