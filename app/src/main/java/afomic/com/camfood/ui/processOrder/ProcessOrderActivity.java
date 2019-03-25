package afomic.com.camfood.ui.processOrder;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.FoodOrderDataSource;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.helper.OrderListAdapter;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProcessOrderActivity extends BaseActivity implements ProcessOrderView {
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
    @BindView(R.id.progress)
    RelativeLayout progressLayout;
    @BindView(R.id.btn_deliver_order)
    Button deliverOrderButton;
    @BindView(R.id.btn_process_order)
    Button processOrderTextView;


    private ProcessOrderPresenter mProcessOrderPresenter;
    private OrderListAdapter mOrderTimeAdapter;
    private List<OrderItem> mOrderItems = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_order);
        ButterKnife.bind(this);
        Order order = getIntent().getParcelableExtra(Constants.EXTRA_ORDER);
        DataSource<Order> orderDataSource = new FoodOrderDataSource(ProcessOrderActivity.this);
        mProcessOrderPresenter = new ProcessOrderPresenter(this, order, orderDataSource);
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
        OrderStatus status = order.getStatus().get(0);
        switch (status.getType()) {
            case Constants.ORDER_STATUS_CREATED:
                acceptLayout.setVisibility(View.VISIBLE);
                deliverOrderButton.setVisibility(View.GONE);
                processOrderTextView.setVisibility(View.GONE);
                break;
            case Constants.ORDER_STATUS_ACCEPTED:
                acceptLayout.setVisibility(View.GONE);
                deliverOrderButton.setVisibility(View.GONE);
                processOrderTextView.setVisibility(View.VISIBLE);
                break;
            case Constants.ORDER_STATUS_DELIVERED:

            case Constants.ORDER_STATUS_DECLINED:
                acceptLayout.setVisibility(View.GONE);
                deliverOrderButton.setVisibility(View.GONE);
                processOrderTextView.setVisibility(View.GONE);
                break;
            case Constants.ORDER_STATUS_PROCESSING:
                acceptLayout.setVisibility(View.GONE);
                deliverOrderButton.setVisibility(View.VISIBLE);
                processOrderTextView.setVisibility(View.GONE);
                break;

        }

        GlideApp.with(ProcessOrderActivity.this)
                .load(foodOrder.getPictureUrl())
                .placeholder(R.drawable.preview)
                .into(foodImageView);
    }

    @Override
    public void showOrderList(List<OrderItem> orderItems) {
        mOrderItems.clear();
        mOrderItems.addAll(orderItems);
        mOrderTimeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressView() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressLayout.setVisibility(View.GONE);
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

    @OnClick(R.id.btn_process_order)
    public void processOrder() {
        mProcessOrderPresenter.handleStatProcessing();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
