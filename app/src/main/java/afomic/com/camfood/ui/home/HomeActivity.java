package afomic.com.camfood.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import afomic.com.camfood.R;
import afomic.com.camfood.ui.foodList.FoodListFragment;
import afomic.com.camfood.ui.foodOrder.FoodOrderFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.loadView();

    }

    @Override
    public void setup() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.home_activity_title);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze);
        }

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                hideNavigationDrawer();
                mHomePresenter.handleNavItemSelected(menuItem.getItemId());
                return false;
            }
        });
    }

    @Override
    public void showFoodView() {
        FoodListFragment fragment=new FoodListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragment).commit();
    }

    @Override
    public void showOrderView() {
        FoodOrderFragment fragment=new FoodOrderFragment();
        displayFragment(fragment);
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
    private void displayFragment(Fragment fragment){
        hideNavigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            showNavigationDrawer();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showNavigationDrawer(){
        mDrawerLayout.openDrawer(Gravity.START);
    }
    private void hideNavigationDrawer(){
        mDrawerLayout.closeDrawers();
    }
}
