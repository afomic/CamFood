package afomic.com.camfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Food implements Parcelable {
    private List<FoodTopping> mFoodToppings;
    private String name;
    private String id;
    private String restaurantId;
    private String pictureUrl;
    private int amount;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPictureUrl;
    private float rating;
    private String foodPreparationTime;

    public Food() {

    }


    protected Food(Parcel in) {
        mFoodToppings = in.createTypedArrayList(FoodTopping.CREATOR);
        name = in.readString();
        id = in.readString();
        restaurantId = in.readString();
        pictureUrl = in.readString();
        amount = in.readInt();
        restaurantName = in.readString();
        restaurantAddress = in.readString();
        restaurantPictureUrl = in.readString();
        rating = in.readFloat();
        foodPreparationTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mFoodToppings);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(restaurantId);
        dest.writeString(pictureUrl);
        dest.writeInt(amount);
        dest.writeString(restaurantName);
        dest.writeString(restaurantAddress);
        dest.writeString(restaurantPictureUrl);
        dest.writeFloat(rating);
        dest.writeString(foodPreparationTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public List<FoodTopping> getFoodToppings() {
        return mFoodToppings;
    }

    public void setFoodToppings(List<FoodTopping> foodToppings) {
        mFoodToppings = foodToppings;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFoodPreparationTime() {
        return foodPreparationTime;
    }

    public void setFoodPreparationTime(String foodPreparationTime) {
        this.foodPreparationTime = foodPreparationTime;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }



}
