package afomic.com.camfood.ui.orderCheckout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.FoodOrderDataSource;
import afomic.com.camfood.helper.OrderListAdapter;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.ui.home.HomeActivity;
import afomic.com.camfood.viewHelper.createFood.UpdateFoodItemDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderCheckoutActivity extends AppCompatActivity implements OrderCheckoutView {
    @BindView(R.id.rv_order_item)
    RecyclerView orderItemRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.spn_location)
    Spinner pickUpLocationSpinner;
    @BindView(R.id.tv_total_amount)
    TextView totalAmountTextView;
    @BindView(R.id.progress)
    RelativeLayout progressLayout;


    private OrderCheckoutPresenter mOrderCheckoutPresenter;
    private OrderListAdapter mOrderListAdapter;
    private List<OrderItem> mOrderItems;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_checkout);
        ButterKnife.bind(this);
        Order order = getIntent().getParcelableExtra(Constants.EXTRA_ORDER);
        mOrderCheckoutPresenter = new OrderCheckoutPresenter(this, order);
        mOrderCheckoutPresenter.setAuthManager(AuthManager.getInstance());
        mOrderCheckoutPresenter.setOrderDataSource(new FoodOrderDataSource(OrderCheckoutActivity.this));
        mOrderCheckoutPresenter.loadView();
    }

    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Checkout");

        orderItemRecyclerView.setLayoutManager(new LinearLayoutManager(OrderCheckoutActivity.this));
        mOrderItems = new ArrayList<>();
        mOrderListAdapter = new OrderListAdapter(mOrderItems);
        orderItemRecyclerView.setAdapter(mOrderListAdapter);
        mOrderListAdapter.setOrderItemListener(new OrderListAdapter.OrderItemListener() {
            @Override
            public void onClick(OrderItem orderItem) {
                mOrderCheckoutPresenter.handleOrderItemClick(orderItem);
            }
        });
        pickUpLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String[] locations = getResources().getStringArray(R.array.pick_up_locations);
                    mOrderCheckoutPresenter.handleLocationSelected(locations[i]);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showTotalAmount(String totalAmount) {
        totalAmountTextView.setText(totalAmount);
    }

    @Override
    public void showOrderItem(List<OrderItem> orderItems) {
        mOrderItems.clear();
        mOrderItems.addAll(orderItems);
        mOrderListAdapter.notifyDataSetChanged();

    }

    @Override
    public void showOrderItemQuantityDialog(OrderItem orderItem) {
        UpdateFoodItemDialog dialog = UpdateFoodItemDialog.getInstance(orderItem);
        dialog.setUpdateOrderItemListener(new UpdateFoodItemDialog.UpdateOrderItemListener() {
            @Override
            public void onDelete(OrderItem orderItem) {
                mOrderCheckoutPresenter.handleOrderItemDelete(orderItem);
            }

            @Override
            public void onUpdate(OrderItem orderItem) {
                mOrderCheckoutPresenter.handleOrderItemUpdate();
            }
        });
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(OrderCheckoutActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressView() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showHome() {
        Intent intent = new Intent(OrderCheckoutActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_checkout_order)
    public void checkoutOrder() {
        mOrderCheckoutPresenter.handleCheckoutOrder();
    }

    @Override
    public void showPaymentView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
