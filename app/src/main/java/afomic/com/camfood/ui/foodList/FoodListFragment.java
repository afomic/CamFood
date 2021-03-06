package afomic.com.camfood.ui.foodList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.FoodDataSource;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.helper.FoodListAdapter;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.ui.base.BaseFragment;
import afomic.com.camfood.ui.createFood.CreateFoodActivity;
import afomic.com.camfood.ui.foodTopping.FoodToppingActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListFragment extends BaseFragment implements FoodListView {

    @BindView(R.id.rv_food)
    RecyclerView foodRecyclerView;
    @BindView(R.id.empty_layout)
    LinearLayout emptyLayout;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    private FoodListPresenter mFoodListPresenter;
    private FoodListAdapter mFoodListAdapter;
    private List<Food> mFoodList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(getContext());
        DataSource<Food> dataSource = new FoodDataSource(getContext());

        mFoodListPresenter = new FoodListPresenter(this, sharedPreferenceHelper, dataSource);
        mFoodListPresenter.loadView();
    }

    @Override
    public void setup() {
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFoodList = new ArrayList<>();
        mFoodListAdapter = new FoodListAdapter(mFoodList);
        mFoodListAdapter.setFoodCLickListener(new FoodListAdapter.FoodCLickListener() {
            @Override
            public void onClick(Food food) {
                showFoodToppingView(food);
            }
        });
        foodRecyclerView.setAdapter(mFoodListAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mFoodListPresenter.isRestaurantAccount()) {
            inflater.inflate(R.menu.restaurant_home_menu, menu);
        } else {
            inflater.inflate(R.menu.customer_home_menu, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_food:
                Intent intent = new Intent(getContext(), CreateFoodActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFood(List<Food> foodList) {
        mFoodList.clear();
        mFoodList.addAll(foodList);
        mFoodListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFoodToppingView(Food food) {
        Intent intent = new Intent(getContext(), FoodToppingActivity.class);
        intent.putExtra(Constants.EXTRA_FOOD, food);
        startActivity(intent);
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
    public void showProgressView() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        mProgressBar.setVisibility(View.GONE);
    }
}
