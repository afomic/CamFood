package afomic.com.camfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Order implements Parcelable {
    private int amount;
    private String userId;
    private String location;
    private String userName;
    private String userPhoneNumber;
    private List<OrderItem> mOrderItems;
    private String restaurantId;
    private int status;
    private String name;
    private String foodId;


    public Order(){

    }

    protected Order(Parcel in) {
        amount = in.readInt();
        userId = in.readString();
        location = in.readString();
        userName = in.readString();
        userPhoneNumber = in.readString();
        restaurantId = in.readString();
        status = in.readInt();
        name = in.readString();
        foodId = in.readString();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(amount);
        parcel.writeString(userId);
        parcel.writeString(location);
        parcel.writeString(userName);
        parcel.writeString(userPhoneNumber);
        parcel.writeString(restaurantId);
        parcel.writeInt(status);
        parcel.writeString(name);
        parcel.writeString(foodId);
    }
}
