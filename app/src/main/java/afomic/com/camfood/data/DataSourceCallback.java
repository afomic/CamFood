package afomic.com.camfood.data;

import java.util.List;

public interface DataSourceCallback<T> {
    void onSuccess(List<T> data);
    void onFailure(String reason);
}
