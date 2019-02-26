package afomic.com.camfood.ui.processOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.helper.OrderListAdapter;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProcessOrderActivity extends AppCompatActivity implements ProcessOrderView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_name)
    TextView customerNameTextView;
    @BindView(R.id.tv_phone_number)
    TextView customerPhoneTextView;
    @BindView(R.id.tv_order_location)
    TextView deliveryLocationTextView;
    @BindView(R.id.imv_food)
    ImageView foodImageView;
    @BindView(R.id.accept_layout)
    LinearLayout acceptLayout;
    @BindView(R.id.rv_order_item)
    RecyclerView orderItemRecyclerView;


    private ProcessOrderPresenter mProcessOrderPresenter;
    private OrderListAdapter mOrderTimeAdapter;
    private List<OrderItem> mOrderItems = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_order);
        ButterKnife.bind(this);
        Order order = getIntent().getParcelableExtra(Constants.EXTRA_ORDER);
        mProcessOrderPresenter = new ProcessOrderPresenter(this, order);
        mProcessOrderPresenter.loadView();
    }

    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        setTitle("Process Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderItemRecyclerView.setLayoutManager(new LinearLayoutManager(ProcessOrderActivity.this));
        mOrderTimeAdapter = new OrderListAdapter(mOrderItems);
        mOrderTimeAdapter.setOrderItemListener(new OrderListAdapter.OrderItemListener() {
            @Override
            public void onClick(OrderItem orderItem) {

            }
        });
        orderItemRecyclerView.setAdapter(mOrderTimeAdapter);

    }

    @Override
    public void showOrder(Order order) {
        customerNameTextView.setText(order.getUserName());
        customerPhoneTextView.setText(order.getUserPhoneNumber());
        deliveryLocationTextView.setText(order.getLocation());
        OrderItem foodOrder = order.getOrderItems().get(0);
        GlideApp.with(ProcessOrderActivity.this)
                .load(foodOrder.getPictureUrl())
                .placeholder(R.drawable.preview)
                .into(foodImageView);
    }

    @Override
    public void showOrderList(List<OrderItem> orderItems) {
        mOrderItems.clear();
        ;
        mOrderItems.addAll(orderItems);
        mOrderTimeAdapter.notifyDataSetChanged();
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

    @OnClick(R.id.btn_accept_order)
    public void acceptOrder() {
        mProcessOrderPresenter.handleOrderAccepted();
    }

    @OnClick(R.id.btn_cancel_order)
    public void cancelOrder() {
        mProcessOrderPresenter.handleOrderCancelled();
    }

    @OnClick(R.id.btn_deliver_order)
    public void deliverOrder() {
        mProcessOrderPresenter.handleOrderDelivered();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
