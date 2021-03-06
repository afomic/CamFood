package afomic.com.camfood.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.model.Food;
import de.hdodenhof.circleimageview.CircleImageView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private List<Food> mFoods;
    private FoodCLickListener mFoodCLickListener;

    public FoodListAdapter(List<Food> foods) {
        mFoods = foods;
    }

    public void setFoodCLickListener(FoodCLickListener foodCLickListener) {
        mFoodCLickListener = foodCLickListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food, viewGroup, false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {
        foodViewHolder.bind(mFoods.get(i));
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView restaurantLogoImageView;
        private TextView restaurantNameTextView, restaurantAddressTextView, foodPriceTextView, foodNameTextView, foodTimeTextView;
        private MaterialRatingBar mRatingBar;
        private ImageView foodImageView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantLogoImageView = itemView.findViewById(R.id.imv_restaurant_logo);
            restaurantAddressTextView = itemView.findViewById(R.id.tv_restaurant_address);
            restaurantNameTextView = itemView.findViewById(R.id.tv_restaurant_name);
            foodPriceTextView = itemView.findViewById(R.id.tv_food_price);
            foodNameTextView = itemView.findViewById(R.id.tv_food_name);
            foodImageView = itemView.findViewById(R.id.imv_food);
            mRatingBar = itemView.findViewById(R.id.rating);
            foodTimeTextView = itemView.findViewById(R.id.tv_food_preparation_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Food food = mFoods.get(getAdapterPosition());
                    mFoodCLickListener.onClick(food);
                }
            });

        }

        public void bind(Food food) {
            mRatingBar.setRating(food.getRating());
            String preparationTime = food.getFoodPreparationTime() + " Away";
            foodTimeTextView.setText(preparationTime);
            restaurantAddressTextView.setText(food.getRestaurantAddress());
            String amount = itemView.getContext().getString(R.string.naira) + StringUtil.getFormattedSting(food.getAmount());
            foodPriceTextView.setText(amount);
            GlideApp.with(itemView)
                    .load(food.getPictureUrl())
                    .placeholder(R.drawable.preview)
                    .into(foodImageView);
            GlideApp.with(itemView)
                    .load(food.getRestaurantPictureUrl())
                    .placeholder(R.drawable.preview)
                    .into(restaurantLogoImageView);
            restaurantNameTextView.setText(food.getRestaurantName());
            foodNameTextView.setText(food.getName());

        }
    }

    public interface FoodCLickListener {
        void onClick(Food food);
    }

}
