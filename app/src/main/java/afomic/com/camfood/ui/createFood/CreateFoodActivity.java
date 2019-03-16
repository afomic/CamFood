package afomic.com.camfood.ui.createFood;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iceteck.silicompressorr.SiliCompressor;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.DataSource;
import afomic.com.camfood.data.FoodDataSource;
import afomic.com.camfood.helper.GlideApp;
import afomic.com.camfood.helper.ToppingHelper;
import afomic.com.camfood.model.Food;
import afomic.com.camfood.model.FoodTopping;
import afomic.com.camfood.ui.base.BaseActivity;
import afomic.com.camfood.viewHelper.CreateFoodToppingAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateFoodActivity extends BaseActivity implements CreateFoodView {
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
    @BindView(R.id.progress)
    RelativeLayout progressLayout;

    private CreateFoodPresenter mCreateFoodPresenter;
    private CreateFoodToppingAdapter mAdapter;
    private List<FoodTopping> mFoodToppings = new ArrayList<>();
    private Uri imageUri;
    private String compressedPath;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 20;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        ButterKnife.bind(this);
        AuthManager authManager = AuthManager.getInstance();
        DataSource<Food> dataSource = new FoodDataSource(this);
        mCreateFoodPresenter = new CreateFoodPresenter(this, ToppingHelper.getInstance(CreateFoodActivity.this), authManager);
        mCreateFoodPresenter.setFoodDataSource(dataSource);
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
    public void showProgressView() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_create_food)
    public void createFood() {
        showProgressView();
        if (imageUri != null) {
            if (compressedPath == null) {
                String compressPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/camfood/images";
                new ImageCompressionAsyncTask(CreateFoodActivity.this).execute(imageUri.getPath(), compressPath);
            } else {
                startFoodUpload(compressedPath);
            }

        } else {
            hideProgressView();
            showMessage("Please select food image");
        }


    }

    @Override
    public void showHome() {
        finish();
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
                imageUri = result.getUri();
                compressedPath = null;
                GlideApp.with(CreateFoodActivity.this)
                        .load(imageUri)
                        .into(foodImageView);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                error.printStackTrace();
            }
        }
    }

    public class ImageCompressionAsyncTask extends AsyncTask<String, Void, String> {

        final AtomicReference<Context> mContext = new AtomicReference<Context>();

        public ImageCompressionAsyncTask(Context context) {
            mContext.set(context);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return SiliCompressor.with(mContext.get()).compress(params[0], new File(params[1]), true);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.isEmpty()) {
                showMessage("Invalid Image");
                hideProgressView();
            } else {
                compressedPath = s;
                startFoodUpload(s);
            }

        }


    }

    private void startFoodUpload(String s) {
        String foodName = foodNameEditText.getText().toString();
        String foodAmount = foodAmountEditText.getText().toString();
        String foodCompletionTime = foodCompletionTimeEditText.getText().toString();
        Uri uri = Uri.fromFile(new File(s));
        mCreateFoodPresenter.createFood(foodName, foodAmount, foodCompletionTime, uri);
    }

}
