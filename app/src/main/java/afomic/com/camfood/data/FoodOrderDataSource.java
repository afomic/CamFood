package afomic.com.camfood.data;


import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import afomic.com.camfood.Constants;
import afomic.com.camfood.model.Order;

public class FoodOrderDataSource implements DataSource<Order> {
    private DatabaseReference foodOrderReference;
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public FoodOrderDataSource(Context context) {
        foodOrderReference = FirebaseDatabase.getInstance().getReference(Constants.FIRE_BASE_FOOD_ORDER_NODE);
        mSharedPreferenceHelper = new SharedPreferenceHelper(context);
    }

    @Override
    public void getData(int type, final DataSourceCallback<Order> callback) {
        if (type == Constants.USER_ACCOUNT_TYPE) {
            foodOrderReference.orderByChild("userId")
                    .equalTo(mSharedPreferenceHelper.getStringPref(SharedPreferenceHelper.PREF_USER_ID))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<Order> orders = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Order order = snapshot.getValue(Order.class);
                                orders.add(order);
                            }
                            callback.onSuccess(orders);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            callback.onFailure(databaseError.getMessage());
                        }
                    });
        } else {
            foodOrderReference.orderByChild("restaurantId")
                    .equalTo(mSharedPreferenceHelper.getStringPref(SharedPreferenceHelper.PREF_USER_ID))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<Order> orders = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Order order = snapshot.getValue(Order.class);
                                orders.add(order);
                            }
                            callback.onSuccess(orders);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            callback.onFailure(databaseError.getMessage());
                        }
                    });
        }
    }

    @Override
    public void save(Order data, final ResponseCallback callback) {
        String foodOrderId = foodOrderReference.push().getKey();
        data.setId(foodOrderId);
        foodOrderReference.child(foodOrderId)
                .setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    @Override
    public void update(Order data, final ResponseCallback callback) {
        foodOrderReference.child(data.getId())
                .setValue(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }

    @Override
    public void delete(Order data, final ResponseCallback callback) {
        foodOrderReference.child(data.getId())
                .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callback.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure(e.getMessage());
            }
        });
    }
}
