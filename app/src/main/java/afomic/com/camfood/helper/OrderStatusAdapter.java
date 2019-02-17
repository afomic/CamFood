package afomic.com.camfood.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.model.OrderStatus;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.OrderStatusViewHolder> {
    private List<OrderStatus> mOrderStatuses;

    public OrderStatusAdapter(List<OrderStatus> statusList) {
        mOrderStatuses = statusList;
    }

    @NonNull
    @Override
    public OrderStatusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_status, viewGroup, false);
        return new OrderStatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderStatusViewHolder orderStatusViewHolder, int i) {
        orderStatusViewHolder.bind(mOrderStatuses.get(i));
    }

    @Override
    public int getItemCount() {
        return mOrderStatuses.size();
    }

    protected class OrderStatusViewHolder extends RecyclerView.ViewHolder {
        private TextView statusDetailTextView, statusTimeTextView;

        public OrderStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            statusDetailTextView = itemView.findViewById(R.id.tv_status_details);
            statusTimeTextView = itemView.findViewById(R.id.tv_status_time);
        }

        public void bind(OrderStatus status) {
            statusTimeTextView.setText(DateUtils.getRelativeTimeSpanString(status.getTime()));
            switch (status.getType()) {
                case Constants.ORDER_STATUS_CREATED:
                    statusDetailTextView.setText(R.string.order_created_status_detail);
                    break;
                case Constants.ORDER_STATUS_ACCEPTED:
                    statusDetailTextView.setText(R.string.order_accepted_status_detail);
                    break;
                case Constants.ORDER_STATUS_DECLINED:
                    statusDetailTextView.setText(R.string.order_declined_status_detail);
                    break;
                case Constants.ORDER_STATUS_DELIVERED:
                    statusDetailTextView.setText(R.string.order_delievered_status_detail);
                    break;
                case Constants.ORDER_STATUS_PROCESSING:
                    statusDetailTextView.setText(R.string.order_processed_status_detail);
                    break;
                case Constants.ORDER_STATUS_FINISHED:
                    statusDetailTextView.setText(R.string.order_finished_status_detail);
                    break;
            }
        }
    }
}
