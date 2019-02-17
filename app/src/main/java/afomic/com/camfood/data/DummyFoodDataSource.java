package afomic.com.camfood.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.Food;

public class DummyFoodDataSource implements DataSource<Food> {
    private Context mContext;

    public DummyFoodDataSource(Context context) {
        mContext = context;
    }

    @Override
    public void getData(int pageNumber, DataSourceCallback<Food> callback) {
        List<Food> foodList = new ArrayList<>();
        Food food = new Food();
        food.setAmount(500);
        food.setId("jdjdjdjdjdj");
        food.setName("Amala Dudu");
        food.setPictureUrl("https://i0.wp.com/www.mydiasporakitchen.com/wp-content/uploads/2018/09/savingpng-233.jpg");
        food.setFoodToppings(ToppingHelper.getInstance(mContext).foodToppings);
        food.setRating(4.5f);
        food.setFoodPreparationTime("20 min");
        food.setRestaurantId("yeybshshs");
        food.setRestaurantName("Feast");
        food.setRestaurantPictureUrl("https://i.pinimg.com/originals/c1/c8/89/c1c889a43c974f0c88f70ff87bd1bfc5.jpg");
        food.setRestaurantAddress("Block 2, New Market OAU Campus, Ile-Ife");

        foodList.add(food);

        Food food1 = new Food();

        food1.setName("Fried Rice with Shrimp");
        food1.setRating(5.0f);
        food1.setRestaurantName("SOGBU");
        food1.setPictureUrl("https://www.melaniecooks.com/wp-content/uploads/2009/07/shrimp-fried-rice1.jpg");
        food1.setAmount(1000);
        food1.setRestaurantPictureUrl("https://marketplace.canva.com/MACP0--HhzM/1/0/thumbnail_large/canva-black-circle-with-utensils-restaurant-logo-MACP0--HhzM.jpg");
        food1.setFoodToppings(ToppingHelper.getInstance(mContext).foodToppings);
        food1.setRestaurantAddress("Block 3, New Market OAU Campus, Ile-Ife");
        food1.setRestaurantId("kdkd");
        food1.setId("jdjdjdj");
        food1.setFoodPreparationTime("20 min");

        foodList.add(food1);
        callback.onSuccess(foodList);
    }

    @Override
    public void save(List<Food> data) {

    }

    @Override
    public void save(Food data) {

    }

    @Override
    public void update(Food data) {

    }

    @Override
    public void delete(Food data) {

    }
}
