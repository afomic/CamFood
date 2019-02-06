package afomic.com.camfood.ui.base;

public abstract class BasePresenter<V extends BaseView> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        view.setup();
    }
    public abstract void loadView();
}
