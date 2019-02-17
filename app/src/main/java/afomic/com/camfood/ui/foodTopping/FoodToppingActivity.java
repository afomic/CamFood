package afomic.com.camfood.ui.foodTopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.helper.FoodToppingListAdapter;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.orderCheckout.OrderCheckoutActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodToppingActivity extends AppCompatActivity implements FoodToppingView {
    @BindView(R.id.imv_food)
    ImageView foodImageView;
    @BindView(R.id.rv_food_topping)
    RecyclerView foodToppingRecyclerView;

    private FoodToppingPresenter mFoodToppingPresenter;
    private FoodToppingListAdapter mFoodToppingListAdapter;
    private List<Object> mToppings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_topping);
        ButterKnife.bind(this);
        Food food = getIntent().getParcelableExtra(Constants.EXTRA_FOOD);
        mFoodToppingPresenter = new FoodToppingPresenter(this, food,
                new SharedPreferenceHelper(FoodToppingActivity.this),
                ToppingHelper.getInstance(FoodToppingActivity.this));
        mFoodToppingPresenter.loadView();
    }

    @Override
    public void setup() {
        foodToppingRecyclerView.setLayoutManager(new LinearLayoutManager(FoodToppingActivity.this));
        foodToppingRecyclerView.addItemDecoration(new DividerItemDecoration(FoodToppingActivity.this,
                DividerItemDecoration.VERTICAL));
        mToppings = new ArrayList<>();
        mFoodToppingListAdapter = new FoodToppingListAdapter(mToppings);
        mFoodToppingListAdapter.setFoodToppingSelectedListener(new FoodToppingListAdapter.FoodToppingSelectedListener() {
            @Override
            public void onSelect(FoodTopping foodTopping) {
                mFoodToppingPresenter.handleToppingSelected(foodTopping);
            }

            @Override
            public void onRemove(FoodTopping foodTopping) {
                mFoodToppingPresenter.handleToppingRemoved(foodTopping);
            }
        });
        foodToppingRecyclerView.setAdapter(mFoodToppingListAdapter);

    }

    @Override
    public void showOrderCheckoutView(Order order) {
        Intent intent = new Intent(FoodToppingActivity.this, OrderCheckoutActivity.class);
        intent.putExtra(Constants.EXTRA_ORDER, order);
        startActivity(intent);
    }

    @Override
    public void showFood(Food food) {
        GlideApp.with(FoodToppingActivity.this)
                .load(food.getPictureUrl())
                .placeholder(R.drawable.preview)
                .into(foodImageView);
    }

    @Override
    public void showTopping(List<Object> toppings) {
        mToppings.clear();
        mToppings.addAll(toppings);
        mFoodToppingListAdapter.notifyDataSetChanged();
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

    @OnClick(R.id.btn_create_order)
    public void createOrder() {
        mFoodToppingPresenter.handleCreateOrder();
    }
}
