package afomic.com.camfood.ui.foodOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.FoodOrderDataSource;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.helper.FoodOrderListAdapter;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.ui.orderDetail.OrderDetailActivity;
import afomic.com.camfood.ui.processOrder.ProcessOrderActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodOrderFragment extends Fragment implements FoodOrderView {
    @BindView(R.id.rv_food_order)
    RecyclerView foodOrderRecyclerView;
    @BindView(R.id.empty_layout)
    LinearLayout emptyLayout;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    private FoodOrderPresenter mFoodOrderPresenter;
    private FoodOrderListAdapter mFoodOrderListAdapter;
    private List<Order> mOrderList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        FoodOrderDataSource dataSource = new FoodOrderDataSource(getContext());
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(getContext());
        mFoodOrderPresenter = new FoodOrderPresenter(this, dataSource, sharedPreferenceHelper);
        mFoodOrderPresenter.loadView();
    }

    @Override
    public void setup() {
        foodOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFoodOrderListAdapter = new FoodOrderListAdapter(mOrderList);
        foodOrderRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        foodOrderRecyclerView.setAdapter(mFoodOrderListAdapter);
        mFoodOrderListAdapter.setListener(new FoodOrderListAdapter.FoodOrderListener() {
            @Override
            public void onClick(Order foodOrder) {
                mFoodOrderPresenter.handleFoodOrderClicked(foodOrder);
            }
        });
    }


    @Override
    public void showFoodOrder(List<Order> orders) {
        mOrderList.clear();
        mOrderList.addAll(orders);
        mFoodOrderListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFoodOrderDetailView(Order order) {
        Intent intent;
        if (mFoodOrderPresenter.isRestaurantAccount()) {
            intent = new Intent(getContext(), ProcessOrderActivity.class);
        } else {
            intent = new Intent(getContext(), OrderDetailActivity.class);
        }
        intent.putExtra(Constants.EXTRA_ORDER, order);
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
    public void showMessage(String message) {

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
