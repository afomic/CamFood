package afomic.com.camfood.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderStatus implements Parcelable {
    private int type;
    private long time;

    public OrderStatus() {

    }

    public OrderStatus(int type, long time) {
        this.type = type;
        this.time = time;
    }

    protected OrderStatus(Parcel in) {
        type = in.readInt();
        time = in.readLong();
    }

    public static final Creator<OrderStatus> CREATOR = new Creator<OrderStatus>() {
        @Override
        public OrderStatus createFromParcel(Parcel in) {
            return new OrderStatus(in);
        }

        @Override
        public OrderStatus[] newArray(int size) {
            return new OrderStatus[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeLong(time);
    }
}
