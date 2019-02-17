package afomic.com.camfood.model;

public class OrderStatus {
    private int type;
    private long time;
    private String orderId;

    public OrderStatus(int type, long time, String orderId) {
        this.type = type;
        this.time = time;
        this.orderId = orderId;
    }

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
