package afomic.com.camfood.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderItem;

public class FoodOrderListAdapter extends RecyclerView.Adapter<FoodOrderListAdapter.FoodOrderViewHolder> {
    private List<Order> mFoodOrders;
    private FoodOrderListener mListener;

    public FoodOrderListAdapter(List<Order> foodOrders) {
        this.mFoodOrders = foodOrders;
    }

    public void setListener(FoodOrderListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_order, viewGroup, false);
        return new FoodOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderViewHolder foodOrderViewHolder, int i) {
        foodOrderViewHolder.bind(mFoodOrders.get(i));
    }

    @Override
    public int getItemCount() {
        return mFoodOrders.size();
    }

    public class FoodOrderViewHolder extends RecyclerView.ViewHolder {
        private ImageView foodImageView;
        private TextView foodNameTextView, orderAmountTextView, orderTimeTextView, orderStatusTextView;
        private Context mContext;

        public FoodOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(mFoodOrders.get(getAdapterPosition()));
                }
            });
            mContext = itemView.getContext();
        }

        public void bind(Order order) {
            String amount = mContext.getString(R.string.naira) + StringUtil.getFormattedSting(order.getAmount());
            OrderItem foodItem = order.getOrderItems().get(0);
            orderAmountTextView.setText(amount);
            foodNameTextView.setText(foodItem.getName());
            GlideApp.with(itemView)
                    .load(foodItem.getPictureUrl())
                    .placeholder(R.drawable.preview)
                    .into(foodImageView);
            switch (order.getStatus()) {
                case Constants.ORDER_STATUS_ACCEPTED:
                    orderStatusTextView.setBackground(mContext.getResources().getDrawable(R.drawable.delivered_status_background));
                    orderStatusTextView.setText(R.string.status_accepted);
                    break;
                case Constants.ORDER_STATUS_DECLINED:
                    orderStatusTextView.setBackground(mContext.getResources().getDrawable(R.drawable.declined_status_background));
                    orderStatusTextView.setText(R.string.status_declined);
                    break;
                case Constants.ORDER_STATUS_DELIVERED:
                    orderStatusTextView.setBackground(mContext.getResources().getDrawable(R.drawable.delivered_status_background));
                    orderStatusTextView.setText(R.string.status_delivered);
                    break;
                case Constants.ORDER_STATUS_PROCESSING:
                    orderStatusTextView.setBackground(mContext.getResources().getDrawable(R.drawable.processing_status_background));
                    orderStatusTextView.setText(R.string.status_processing);
                    break;
            }
        }
    }

    public interface FoodOrderListener {
        void onClick(Order foodOrder);
    }
}
