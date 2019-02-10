package afomic.com.camfood.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.model.OrderItem;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {
    private List<OrderItem> mOrderItems;
    private OrderItemListener mOrderItemListener;

    public OrderListAdapter(List<OrderItem> orderItems) {
        mOrderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order, viewGroup, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        OrderItem item = mOrderItems.get(i);
        orderViewHolder.bind(item);

    }

    @Override
    public int getItemCount() {
        return mOrderItems.size();
    }

    public void setOrderItemListener(OrderItemListener orderItemListener) {
        mOrderItemListener = orderItemListener;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView orderNameTextView, orderQuantityTextView, orderPriceTextView;
        private ImageView orderImageView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImageView = itemView.findViewById(R.id.imv_order);
            orderPriceTextView = itemView.findViewById(R.id.tv_order_price);
            orderQuantityTextView = itemView.findViewById(R.id.tv_order_quantity);
            orderNameTextView = itemView.findViewById(R.id.tv_order_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OrderItem orderItem = mOrderItems.get(getAdapterPosition());
                    mOrderItemListener.onClick(orderItem);
                }
            });
        }

        public void bind(OrderItem orderItem) {
            orderNameTextView.setText(orderItem.getName());
            String amount = orderItem.getAmount() == 0 ? "Free" : "₦" + getTotalAmount(orderItem);
            orderPriceTextView.setText(amount);
            String quantity = "X" + orderItem.getQuantity();
            orderQuantityTextView.setText(quantity);

        }

        private String getTotalAmount(OrderItem orderItem) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            return numberFormat.format(orderItem.getAmount() * orderItem.getQuantity());
        }
    }

    public interface OrderItemListener {
        void onClick(OrderItem orderItem);
    }
}
