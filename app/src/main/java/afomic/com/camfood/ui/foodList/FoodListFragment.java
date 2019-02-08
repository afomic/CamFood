package afomic.com.camfood.ui.foodList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.helper.FoodListAdapter;
import afomic.com.camfood.model.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListFragment extends Fragment implements FoodListView {
    private static final String BUNDLE_USER_TYPE = "user_type";
    private int userType;

    @BindView(R.id.rv_food)
    RecyclerView foodRecyclerView;

    private FoodListPresenter mFoodListPresenter;
    private View foodListView;
    private FoodListAdapter mFoodListAdapter;

    public static FoodListFragment newInstance(int userType) {
        Bundle args = new Bundle();
        args.putInt(BUNDLE_USER_TYPE, userType);
        FoodListFragment fragment = new FoodListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userType = getArguments().getInt(BUNDLE_USER_TYPE);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_food_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mFoodListPresenter = new FoodListPresenter(this, new SharedPreferenceHelper(getContext()), userType);
        mFoodListPresenter.loadView();
    }

    @Override
    public void setup() {
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        foodListView=getLayoutInflater().inflate(R.layout.item_food,null,false);
    }

    @Override
    public void showFood(List<Food> foodList) {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void hideEmptyView() {

    }


    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void hideProgressView() {

    }
}
