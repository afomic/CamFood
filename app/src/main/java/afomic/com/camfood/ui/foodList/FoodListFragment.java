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
import android.widget.LinearLayout;

import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.helper.FoodListAdapter;
import afomic.com.camfood.model.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListFragment extends Fragment implements FoodListView {
    private static final String BUNDLE_USER_TYPE = "user_type";

    @BindView(R.id.rv_food)
    RecyclerView foodRecyclerView;
    @BindView(R.id.empty_layout)
    LinearLayout emptyLayout;

    private FoodListPresenter mFoodListPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        mFoodListPresenter = new FoodListPresenter(this, new SharedPreferenceHelper(getContext()));
        mFoodListPresenter.loadView();
    }

    @Override
    public void setup() {
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showFood(List<Food> foodList) {
        FoodListAdapter adapter = new FoodListAdapter(foodList);
        foodRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showEmptyView() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyLayout.setVisibility(View.GONE);
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
