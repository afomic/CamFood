package afomic.com.camfood.ui.orderDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.FoodOrderDataSource;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.helper.OrderStatusAdapter;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderStatus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailView {
    @BindView(R.id.rv_order_status)
    RecyclerView statusRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.imv_food)
    ImageView foodImageView;
    @BindView(R.id.btn_approved)
    Button approveOrderButton;

    private OrderDetailPresenter mOrderDetailPresenter;
    private OrderStatusAdapter mOrderStatusAdapter;
    private List<OrderStatus> mOrderStatuses = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        Order order = getIntent().getParcelableExtra(Constants.EXTRA_ORDER);
        DataSource<Order> orderDataSource = new FoodOrderDataSource(OrderDetailActivity.this);
        mOrderDetailPresenter = new OrderDetailPresenter(this, order, orderDataSource);
        mOrderDetailPresenter.loadView();

    }

    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mOrderStatusAdapter = new OrderStatusAdapter(mOrderStatuses);
        statusRecyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        statusRecyclerView.setAdapter(mOrderStatusAdapter);

    }

    @Override
    public void showOrderImage(String pictureUrl) {
        GlideApp.with(OrderDetailActivity.this)
                .load(pictureUrl)
                .placeholder(R.drawable.preview)
                .into(foodImageView);
    }

    @Override
    public void showStatus(List<OrderStatus> statusList) {
        mOrderStatuses.clear();
        mOrderStatuses.addAll(statusList);
        Collections.sort(mOrderStatuses, new Comparator<OrderStatus>() {
            @Override
            public int compare(OrderStatus orderStatus, OrderStatus t1) {
                return (int)(orderStatus.getTime()-t1.getTime());
            }
        });
        mOrderStatusAdapter.notifyDataSetChanged();

    }

    @Override
    public void showFinishedButton() {
        approveOrderButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFinishedButton() {
        approveOrderButton.setVisibility(View.GONE);
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

    @OnClick(R.id.btn_approved)
    public void onApprove() {
        mOrderDetailPresenter.handleApproved();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
