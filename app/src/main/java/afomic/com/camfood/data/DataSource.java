package afomic.com.camfood.data;

public interface DataSource<T> {
    void getData(int type, DataSourceCallback<T> callback);

    void save(T data, ResponseCallback callback);

    void update(T data, ResponseCallback callback);

    void delete(T data, ResponseCallback callback);

}
