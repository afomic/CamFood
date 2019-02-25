package afomic.com.camfood.viewHelper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.model.FoodTopping;

public class CreateFoodToppingAdapter extends RecyclerView.Adapter<CreateFoodToppingAdapter.CreateFoodToppingViewHolder> {
    private List<FoodTopping> mFoodToppings;
    private FoodToppingListener mListener;

    public CreateFoodToppingAdapter(List<FoodTopping> foodToppings) {
        mFoodToppings = foodToppings;
    }

    public void setListener(FoodToppingListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public CreateFoodToppingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_topping, viewGroup, false);
        return new CreateFoodToppingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateFoodToppingViewHolder createFoodToppingViewHolder, int i) {
        createFoodToppingViewHolder.bind(mFoodToppings.get(i));
    }

    @Override
    public int getItemCount() {
        return mFoodToppings.size();
    }

    public class CreateFoodToppingViewHolder extends RecyclerView.ViewHolder {
        private TextView foodToppingNameTextView, foodToppingAmountTextView;
        private ImageView foodToppingImageView;
        private LinearLayout selectedLayout;

        public CreateFoodToppingViewHolder(@NonNull View itemView) {
            super(itemView);
            foodToppingAmountTextView = itemView.findViewById(R.id.tv_food_topping_amount);
            foodToppingImageView = itemView.findViewById(R.id.imv_food_topping);
            foodToppingNameTextView = itemView.findViewById(R.id.tv_food_topping_name);
            selectedLayout = itemView.findViewById(R.id.selected_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodTopping topping = mFoodToppings.get(getAdapterPosition());
                    if (topping.selected) {
                        topping.selected = false;
                        selectedLayout.setVisibility(View.GONE);
                        mListener.onRemove(topping);
                    } else {
                        topping.selected = true;
                        selectedLayout.setVisibility(View.VISIBLE);
                        mListener.onSelect(topping);
                    }
                }
            });

        }

        public void bind(FoodTopping foodTopping) {
            foodToppingNameTextView.setText(foodTopping.getName());
            String price = foodTopping.getPrice() == 0 ? "Free" : itemView.getContext().getString(R.string.naira) + foodTopping.getPrice();
            foodToppingAmountTextView.setText(price);
            GlideApp.with(itemView.getContext())
                    .load(foodTopping.getPictureUrl())
                    .into(foodToppingImageView);
        }
    }

    public interface FoodToppingListener {
        void onSelect(FoodTopping foodTopping);

        void onRemove(FoodTopping foodTopping);
    }
}
