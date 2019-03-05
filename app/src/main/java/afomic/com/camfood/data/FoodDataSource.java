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
import afomic.com.camfood.model.Food;

public class FoodDataSource implements DataSource<Food> {
    private DatabaseReference foodReference;
    private SharedPreferenceHelper mSharedPreferenceHelper;

    public FoodDataSource(Context context) {
        mSharedPreferenceHelper = new SharedPreferenceHelper(context);
        foodReference = FirebaseDatabase.getInstance().getReference(Constants.FIRE_BASE_FOOD_NODE);
    }

    @Override
    public void getData(int type, final DataSourceCallback<Food> callback) {
        if (type == Constants.USER_ACCOUNT_TYPE) {
            foodReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<Food> foods = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Food food = snapshot.getValue(Food.class);
                        foods.add(food);
                    }
                    callback.onSuccess(foods);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    callback.onFailure(databaseError.getMessage());
                }
            });
        } else {
            foodReference.
                    orderByChild("restaurantId")
                    .equalTo(mSharedPreferenceHelper.getStringPref(SharedPreferenceHelper.PREF_USER_ID))
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<Food> foods = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Food food = snapshot.getValue(Food.class);
                                foods.add(food);
                            }
                            callback.onSuccess(foods);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            callback.onFailure(databaseError.getMessage());
                        }
                    });
        }
    }

    @Override
    public void save(Food data, final ResponseCallback callback) {
        String foodId = foodReference.push().getKey();
        data.setId(foodId);
        foodReference.child(foodId)
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
    public void update(Food data, final ResponseCallback callback) {
        foodReference.child(data.getId())
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
    public void delete(Food data, final ResponseCallback callback) {
        foodReference.child(data.getId())
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
