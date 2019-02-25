package afomic.com.camfood.ui.createFood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.R;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.viewHelper.CreateFoodToppingAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateFoodActivity extends AppCompatActivity implements CreateFoodView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.edt_food_amount)
    EditText foodAmountEditText;
    @BindView(R.id.edt_food_completion_time)
    EditText foodCompletionTimeEditText;
    @BindView(R.id.edt_food_name)
    EditText foodNameEditText;
    @BindView(R.id.rv_food_topping)
    RecyclerView foodToppingRecyclerView;
    @BindView(R.id.imv_food)
    ImageView foodImageView;

    private CreateFoodPresenter mCreateFoodPresenter;
    private CreateFoodToppingAdapter mAdapter;
    private List<FoodTopping> mFoodToppings = new ArrayList<>();

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 20;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        ButterKnife.bind(this);
        mCreateFoodPresenter = new CreateFoodPresenter(this, ToppingHelper.getInstance(CreateFoodActivity.this));
        mCreateFoodPresenter.loadView();

    }


    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.create_food_title);

        mAdapter = new CreateFoodToppingAdapter(mFoodToppings);
        foodToppingRecyclerView.setAdapter(mAdapter);

        mAdapter.setListener(new CreateFoodToppingAdapter.FoodToppingListener() {
            @Override
            public void onSelect(FoodTopping foodTopping) {
                mCreateFoodPresenter.handleToppingSelected(foodTopping);
            }

            @Override
            public void onRemove(FoodTopping foodTopping) {
                mCreateFoodPresenter.handleToppingRemoved(foodTopping);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(CreateFoodActivity.this, getRowCount());
        foodToppingRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private int getRowCount() {
        int screenWidth = getResources().getConfiguration().screenWidthDp;
        return screenWidth / 90;
    }

    @Override
    public void showFoodTopping(List<FoodTopping> foodToppings) {
        mFoodToppings.clear();
        mFoodToppings.addAll(foodToppings);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectImageFromPhone() {
        if (hasPermission()) {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(4, 3)
                    .start(this);
        } else {
            requestPermission();
        }


    }

    public boolean hasPermission() {
        return ActivityCompat.checkSelfPermission(CreateFoodActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(CreateFoodActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateFoodActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImageFromPhone();
        }
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressView() {

    }

    @Override
    public void hideProgressView() {

    }

    @OnClick(R.id.btn_create_food)
    public void createFood() {

    }

    @OnClick(R.id.imv_food)
    public void foodToppingImageClicked() {
        selectImageFromPhone();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                GlideApp.with(CreateFoodActivity.this)
                        .load(resultUri)
                        .into(foodImageView);
                mCreateFoodPresenter.handleImageViewSelected(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
