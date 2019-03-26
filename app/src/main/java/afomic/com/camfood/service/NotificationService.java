package afomic.com.camfood.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import afomic.com.camfood.Constants;
import afomic.com.camfood.R;
import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.helper.NotificationHelper;
import afomic.com.camfood.model.Order;
import afomic.com.camfood.model.OrderStatus;
import afomic.com.camfood.model.User;

public class NotificationService extends IntentService {
    private static final String NAME = "NotificationService";

    public NotificationService() {
        super(NAME);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        AuthManager authManager = AuthManager.getInstance();
        authManager.getCurrentUser(new AuthManager.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                startNotificationService(user);
            }

            @Override
            public void onError(String reason) {

            }
        });


    }

    private void startNotificationService(final User user) {
        String orderBy = user.userType == Constants.RESTAURANT_ACCOUNT_TYPE ? "restaurantId" : "userId";
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIRE_BASE_FOOD_ORDER_NODE)
                .orderByChild(orderBy)
                .equalTo(user.id);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = dataSnapshot.getValue(Order.class);
                OrderStatus status = order.getStatus().get(0);
                sendNotification(status, user.userType);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = dataSnapshot.getValue(Order.class);
                OrderStatus status = order.getStatus().get(0);
                sendNotification(status, user.userType);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendNotification(OrderStatus status, int userType) {
        switch (status.getType()) {
            case Constants.ORDER_STATUS_CREATED:
                if (userType == Constants.RESTAURANT_ACCOUNT_TYPE) {
                    NotificationHelper.sendNotificationMessage(NotificationService.this,"You have Received New Order");
                }
                break;
            case Constants.ORDER_STATUS_ACCEPTED:
                if (userType == Constants.CUSTOMER_REGISTRATION_TYPE) {
                    NotificationHelper.sendNotificationMessage(NotificationService.this,"Your Order Has Been Accepted");
                }

                break;
            case Constants.ORDER_STATUS_DECLINED:
                if (userType == Constants.CUSTOMER_REGISTRATION_TYPE) {
                    NotificationHelper.sendNotificationMessage(NotificationService.this,"Your Order Has Been Declined");
                }
                break;
            case Constants.ORDER_STATUS_DELIVERED:
                if (userType == Constants.CUSTOMER_REGISTRATION_TYPE) {
                    NotificationHelper.sendNotificationMessage(NotificationService.this,"Your Order Has Been Delivered");
                }

                break;
            case Constants.ORDER_STATUS_PROCESSING:
                if (userType == Constants.CUSTOMER_REGISTRATION_TYPE) {
                    NotificationHelper.sendNotificationMessage(NotificationService.this,"Your Order is currently Been processed");
                }

                break;
            case Constants.ORDER_STATUS_FINISHED:
                if (userType == Constants.RESTAURANT_ACCOUNT_TYPE) {
                    NotificationHelper.sendNotificationMessage(NotificationService.this,"Customer Has Approved Order");
                }

                break;
        }
    }
}
