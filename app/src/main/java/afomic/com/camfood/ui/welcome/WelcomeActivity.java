package afomic.com.camfood.ui.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import afomic.com.camfood.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_customer_register)
    public void registerCustomerClick(){

    }
    @OnClick(R.id.btn_restaurant_register)
    public void registerRestaurantClick(){

    }
}
