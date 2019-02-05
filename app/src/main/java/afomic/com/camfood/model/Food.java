package afomic.com.camfood.model;

import java.util.List;

public class Food {
    private List<Topping> mToppings;
    private String name;
    private String id;
    private String restaurantId;
    private String pictureUrl;
    private int amount;
    private String restaurantName;
    private String restaurantPictureUrl;
    private double rating;

    public List<Topping> getToppings() {
        return mToppings;
    }

    public void setToppings(List<Topping> toppings) {
        mToppings = toppings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPictureUrl() {
        return restaurantPictureUrl;
    }

    public void setRestaurantPictureUrl(String restaurantPictureUrl) {
        this.restaurantPictureUrl = restaurantPictureUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
