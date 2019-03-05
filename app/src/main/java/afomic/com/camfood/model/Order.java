package afomic.com.camfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {
    private String id;
    private int amount;
    private String userId;
    private String location;
    private String userName;
    private String userPhoneNumber;
    private List<OrderItem> mOrderItems;
    private String restaurantId;
    private List<OrderStatus> mOrderStatuses = new ArrayList<>();
    private String name;
    private String foodId;


    public Order() {

    }

    protected Order(Parcel in) {
        id = in.readString();
        amount = in.readInt();
        userId = in.readString();
        location = in.readString();
        userName = in.readString();
        userPhoneNumber = in.readString();
        mOrderItems = in.createTypedArrayList(OrderItem.CREATOR);
        restaurantId = in.readString();
        mOrderStatuses = in.createTypedArrayList(OrderStatus.CREATOR);
        name = in.readString();
        foodId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(amount);
        dest.writeString(userId);
        dest.writeString(location);
        dest.writeString(userName);
        dest.writeString(userPhoneNumber);
        dest.writeTypedList(mOrderItems);
        dest.writeString(restaurantId);
        dest.writeTypedList(mOrderStatuses);
        dest.writeString(name);
        dest.writeString(foodId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<OrderItem> getOrderItems() {
        return mOrderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        mOrderItems = orderItems;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderStatus> getStatus() {
        return mOrderStatuses;
    }

    public void addStatus(OrderStatus status) {
        mOrderStatuses.add(0, status);
    }

    public List<OrderStatus> getOrderStatuses() {
        return mOrderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        mOrderStatuses = orderStatuses;
    }
}
