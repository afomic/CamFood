package afomic.com.camfood.Ui.base;

public class BasePresenter<V extends BaseView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }
}
