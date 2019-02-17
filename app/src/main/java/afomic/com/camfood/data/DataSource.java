package afomic.com.camfood.data;

import java.util.List;

public interface DataSource<T> {
    void getData(int pageNumber, DataSourceCallback<T> callback);

    void save(List<T> data);

    void save(T data);

    void update(T data);

    void delete(T data);
}
