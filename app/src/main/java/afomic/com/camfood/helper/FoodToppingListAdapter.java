package afomic.com.camfood.helper;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.model.FoodToppingTitle;

public class FoodToppingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> mToppingList;
    private static final int TITLE_VIEW_TYPE = 101;
    private static final int TOPPING_VIEW_TYPE = 102;

    private FoodToppingSelectedListener mFoodToppingSelectedListener;

    public FoodToppingListAdapter(List<Object> toppingList) {
        mToppingList = toppingList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TITLE_VIEW_TYPE) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_topping_title, viewGroup, false);
            return new FoodToppingTitleViewHolder(view);
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_topping, viewGroup, false);
        return new FoodToppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object object = mToppingList.get(i);
        if (getItemViewType(i) == TITLE_VIEW_TYPE) {
            FoodToppingTitleViewHolder holder = (FoodToppingTitleViewHolder) viewHolder;
            holder.bind((FoodToppingTitle) object);
        } else {
            FoodToppingViewHolder holder = (FoodToppingViewHolder) viewHolder;
            holder.bind((FoodTopping) object);
        }

    }

    @Override
    public int getItemViewType(int position) {
        Object object = mToppingList.get(position);
        if (object instanceof FoodToppingTitle) {
            return TITLE_VIEW_TYPE;
        }
        return TOPPING_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (mToppingList != null) {
            return mToppingList.size();
        }
        return 0;
    }

    public void setFoodToppingSelectedListener(FoodToppingSelectedListener listener) {
        mFoodToppingSelectedListener = listener;
    }

    public class FoodToppingTitleViewHolder extends RecyclerView.ViewHolder {
        private TextView foodToppingTitleTextView;

        public FoodToppingTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            foodToppingTitleTextView = itemView.findViewById(R.id.tv_food_topping_title);
        }

        public void bind(FoodToppingTitle foodToppingTitle) {
            foodToppingTitleTextView.setText(foodToppingTitle.title);
        }
    }

    public class FoodToppingViewHolder extends RecyclerView.ViewHolder {
        private CheckBox foodToppingCheckBox;
        private TextView foodToppingNameTextView, foodToppingPriceTextView;
        private ImageView foodToppingImageView;

        public FoodToppingViewHolder(@NonNull final View itemView) {
            super(itemView);
            foodToppingCheckBox = itemView.findViewById(R.id.ch_topping);
            foodToppingImageView = itemView.findViewById(R.id.imv_topping);
            foodToppingNameTextView = itemView.findViewById(R.id.tv_topping_name);
            foodToppingPriceTextView = itemView.findViewById(R.id.tv_topping_price);
            foodToppingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    FoodTopping topping = (FoodTopping) mToppingList.get(getAdapterPosition());
                    topping.selected = b;
                    if (b) {
                        mFoodToppingSelectedListener.onSelect(topping);
                        itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.lightGray));
                    } else {
                        mFoodToppingSelectedListener.onRemove(topping);
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodTopping topping = (FoodTopping) mToppingList.get(getAdapterPosition());
                    if (topping.selected) {
                        foodToppingCheckBox.setChecked(false);
                    } else {
                        foodToppingCheckBox.setChecked(true);

                    }
                }
            });
        }

        public void bind(FoodTopping foodTopping) {
            foodToppingNameTextView.setText(foodTopping.getName());
            String price = foodTopping.getPrice() == 0 ? "Free" : "₦" + foodTopping.getPrice();
            foodToppingPriceTextView.setText(price);
        }
    }

    public interface FoodToppingSelectedListener {
        void onSelect(FoodTopping foodTopping);

        void onRemove(FoodTopping foodTopping);
    }
}
