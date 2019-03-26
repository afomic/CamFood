package afomic.com.camfood.ui.home;

import android.content.Intent;
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
import android.view.View;
import android.widget.RelativeLayout;

import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.User;
import afomic.com.camfood.ui.base.BaseActivity;
import afomic.com.camfood.ui.createFood.CreateFoodActivity;
import afomic.com.camfood.ui.foodList.FoodListFragment;
import afomic.com.camfood.ui.foodOrder.FoodOrderFragment;
import afomic.com.camfood.ui.profile.ProfileFragment;
import afomic.com.camfood.viewHelper.fundWallet.FundWalletDialog;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView, FundWalletDialog.FundWalletInteractor {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.progress)
    RelativeLayout progressLayout;
    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(HomeActivity.this);
        mHomePresenter = new HomePresenter(this, sharedPreferenceHelper);
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
        FoodListFragment fragment = new FoodListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, fragment).commit();
    }

    @Override
    public void showOrderView() {
        FoodOrderFragment fragment = new FoodOrderFragment();
        displayFragment(fragment);
    }

    @Override
    public void showProfileView() {
        ProfileFragment fragment = new ProfileFragment();
        displayFragment(fragment);
    }

    @Override
    public void showAddFoodView() {
        Intent intent = new Intent(HomeActivity.this, CreateFoodActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgressView() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void onFundWallet(final int amount) {
        showProgressView();
        final AuthManager authManager = AuthManager.getInstance();
        authManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                user.accountBalance = user.accountBalance + amount;
                authManager.updateUser(user, new AuthManager.AuthCallback() {
                    @Override
                    public void onSuccess(User user) {
                        hideProgressView();
                        showMessage(R.string.fund_wallet_sucess);
                        showProfileView();
                    }

                    @Override
                    public void onError(String reason) {
                        hideProgressView();
                        showMessage(reason);
                    }
                });
            }

            @Override
            public void onError(String reason) {
                hideProgressView();
                showMessage(reason);
            }
        });
    }

    @Override
    public void onWithdraw(final int amount) {
        showProgressView();
        final AuthManager authManager = AuthManager.getInstance();
        authManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                if (user.accountBalance > amount) {
                    user.accountBalance = user.accountBalance - amount;
                    authManager.updateUser(user, new AuthManager.AuthCallback() {
                        @Override
                        public void onSuccess(User user) {
                            hideProgressView();
                            showMessage(R.string.withdraw_success);
                            showProfileView();
                        }

                        @Override
                        public void onError(String reason) {
                            hideProgressView();
                            showMessage(reason);
                        }
                    });
                } else {
                    showMessage(R.string.insufficient_balance);
                }

            }

            @Override
            public void onError(String reason) {
                hideProgressView();
                showMessage(reason);
            }
        });
    }

    private void displayFragment(Fragment fragment) {
        hideNavigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showNavigationDrawer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNavigationDrawer() {
        mDrawerLayout.openDrawer(Gravity.START);
    }

    private void hideNavigationDrawer() {
        mDrawerLayout.closeDrawers();
    }
}
