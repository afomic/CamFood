package afomic.com.camfood.helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    private View mView;
    private FoodCLickListener mFoodCLickListener;

    public FoodListAdapter(View view, List<Food> foods) {
        mFoods = foods;
        mView = view;
    }

    public void setFoodCLickListener(FoodCLickListener foodCLickListener) {
        mFoodCLickListener = foodCLickListener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 2;
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

        }

        public void bind(Food food) {
            mRatingBar.setRating(food.getRating());
            foodTimeTextView.setText(food.getFoodPreparationTime());
            restaurantAddressTextView.setText(food.getRestaurantAddress());
            foodPriceTextView.setText(food.getAmount());
        }
    }

    public interface FoodCLickListener {
        void onClick(Food food);
    }

}
