package afomic.com.camfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodTopping implements Parcelable {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("price")
    private int price;
    @Expose
    @SerializedName("picture_url")
    private String pictureUrl;
    @Expose
    @SerializedName("type")
    private int type;
    public boolean selected;
    public static final int TYPE_REFRESHMENT = 1;
    public static final int TYPE_TOPPING = 0;


    public FoodTopping() {

    }

    protected FoodTopping(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readInt();
        pictureUrl = in.readString();
        type = in.readInt();
        selected = in.readByte() != 0;
    }

    public static final Creator<FoodTopping> CREATOR = new Creator<FoodTopping>() {
        @Override
        public FoodTopping createFromParcel(Parcel in) {
            return new FoodTopping(in);
        }

        @Override
        public FoodTopping[] newArray(int size) {
            return new FoodTopping[size];
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeString(pictureUrl);
        parcel.writeInt(type);
        parcel.writeByte((byte) (selected ? 1 : 0));
    }
}

