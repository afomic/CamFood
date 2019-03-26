package afomic.com.camfood.ui.createFood;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.ResponseCallback;
import afomic.com.camfood.helper.FirebaseFileService;
import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BasePresenter;

public class CreateFoodPresenter extends BasePresenter<CreateFoodView> {
    private ToppingHelper mToppingHelper;
    private List<FoodTopping> selectedFoodTopping;
    private AuthManager mAuthManager;
    private DataSource<Food> mFoodDataSource;
    private FirebaseFileService firebaseFileService;

    public CreateFoodPresenter(CreateFoodView view, ToppingHelper toppingHelper, AuthManager authManager) {
        super(view);
        mToppingHelper = toppingHelper;
        selectedFoodTopping = new ArrayList<>();
        mAuthManager = authManager;
    }

    public void setFoodDataSource(DataSource<Food> foodDataSource) {
        mFoodDataSource = foodDataSource;
    }

    public void setFirebaseFileService(FirebaseFileService firebaseFileService) {
        this.firebaseFileService = firebaseFileService;
    }

    @Override
    public void loadView() {
        view.showFoodTopping(mToppingHelper.foodToppings);

    }

    public void handleToppingSelected(FoodTopping topping) {
        selectedFoodTopping.add(topping);
    }

    public void handleToppingRemoved(FoodTopping topping) {
        selectedFoodTopping.remove(topping);
    }

    public void createFood(String foodName, String foodAmount, String foodTime, final Uri imageUri) {
        if (foodName.isEmpty()) {
            view.showMessage(R.string.empty_food_name_error);
            view.hideProgressView();
            return;
        }
        if (selectedFoodTopping.isEmpty()) {
            view.showMessage(R.string.empty_topping_error);
            view.hideProgressView();
            return;
        }
        if (foodAmount.isEmpty()) {
            view.showMessage(R.string.empty_food_price_error);
            view.hideProgressView();
            return;
        }
        if (foodTime.isEmpty()) {
            view.showMessage(R.string.empty_food_completion_time_error);
            view.hideProgressView();
            return;
        }

        final Food food = new Food();
        food.setAmount(Integer.parseInt(foodAmount));
        food.setName(foodName);
        food.setFoodPreparationTime(foodTime);
        food.setRating(5.0f);
        food.setFoodToppings(selectedFoodTopping);
        mAuthManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                food.setRestaurantId(user.id);
                food.setRestaurantName(user.name);
                food.setRestaurantAddress(user.address);
                uploadImage(imageUri, food);
            }

            @Override
            public void onError(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });

    }

    private void saveFood(Food food) {
        mFoodDataSource.save(food, new ResponseCallback() {
            @Override
            public void onSuccess() {
                view.hideProgressView();
                view.showMessage(R.string.food_sucessfully_added);
                view.showHome();
            }

            @Override
            public void onFailure(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });
    }

    public void uploadImage(@NonNull Uri imageUri, final Food food) {
        firebaseFileService.uploadFile(imageUri, new FirebaseFileService.UploadServiceCallback() {
            @Override
            public void onSuccess(String downloadUrl) {
                food.setPictureUrl(downloadUrl);
                saveFood(food);
            }

            @Override
            public void onFailure(String reason) {
                view.hideProgressView();
                view.showMessage(reason);
            }
        });
    }

}
