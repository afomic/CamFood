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
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.ResponseCallback;
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

    public CreateFoodPresenter(CreateFoodView view, ToppingHelper toppingHelper, AuthManager authManager) {
        super(view);
        mToppingHelper = toppingHelper;
        selectedFoodTopping = new ArrayList<>();
        mAuthManager = authManager;
    }

    public void setFoodDataSource(DataSource<Food> foodDataSource) {
        mFoodDataSource = foodDataSource;
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
            view.showMessage("Food name cannot be empty");
            return;
        }
        if (selectedFoodTopping.isEmpty()) {
            view.showMessage("Kindly add one ore or topping");
            return;
        }
        if (foodAmount.isEmpty()) {
            view.showMessage("Food Price cannot be empty");
            return;
        }
        if (foodTime.isEmpty()) {
            view.showMessage("Food time to Completion cannot be empty");
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
                view.showMessage("Food Successfully added");
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
        final StorageReference foodImageRef = FirebaseStorage.getInstance().getReference(Constants.FIRE_BASE_FOOD_IMAGE_NODE)
                .child(imageUri.getLastPathSegment());
        final UploadTask uploadTask = foodImageRef
                .putFile(imageUri);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return foodImageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    food.setPictureUrl(downloadUri.toString());
                    saveFood(food);

                } else {
                    view.hideProgressView();
                    view.showMessage(task.getException().getMessage());
                }
            }
        });
    }

}
