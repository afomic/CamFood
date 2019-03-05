package afomic.com.camfood.data;

public interface ResponseCallback {
    void onSuccess();

    void onFailure(String reason);
}
