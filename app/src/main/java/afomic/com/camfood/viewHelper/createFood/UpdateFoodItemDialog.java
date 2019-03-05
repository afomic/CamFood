package afomic.com.camfood.viewHelper.createFood;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import afomic.com.camfood.R;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.model.OrderItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateFoodItemDialog extends DialogFragment {
    @BindView(R.id.edt_quantity)
    EditText quantityEditText;
    @BindView(R.id.tv_food_name)
    TextView foodNameTextView;
    @BindView(R.id.imv_food_item)
    ImageView foodItemImageView;

    private static final String BUNDLE_ORDER_TIME = "order_item";
    private OrderItem currentOrderItem;
    private UpdateOrderItemListener mUpdateOrderItemListener;


    public static UpdateFoodItemDialog getInstance(OrderItem orderItem) {
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_ORDER_TIME, orderItem);
        UpdateFoodItemDialog dialog = new UpdateFoodItemDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrderItem = getArguments().getParcelable(BUNDLE_ORDER_TIME);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_update_food_item, null, false);
        ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        quantityEditText.setText(String.valueOf(currentOrderItem.getQuantity()));
        foodNameTextView.setText(currentOrderItem.getName());

        GlideApp.with(getContext())
                .load(currentOrderItem.getPictureUrl())
                .placeholder(R.drawable.preview)
                .into(foodItemImageView);

        builder.setPositiveButton(R.string.save_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newQuantity = quantityEditText.getText().toString();
                if (!newQuantity.isEmpty()) {
                    int quantity = Integer.parseInt(newQuantity);
                    currentOrderItem.setQuantity(quantity);
                    mUpdateOrderItemListener.onUpdate(currentOrderItem);
                }

            }
        });
        builder.setNegativeButton(R.string.delete_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mUpdateOrderItemListener.onDelete(currentOrderItem);
            }
        });
        builder.setNeutralButton(R.string.cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    public void setUpdateOrderItemListener(UpdateOrderItemListener updateOrderItemListener) {
        mUpdateOrderItemListener = updateOrderItemListener;
    }


    public interface UpdateOrderItemListener {
        void onDelete(OrderItem orderItem);

        void onUpdate(OrderItem orderItem);
    }
}
