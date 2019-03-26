package afomic.com.camfood.data;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import afomic.com.camfood.Constants;
import afomic.com.camfood.model.User;

public class AuthManager {
    private static AuthManager mAuthManager;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference userReference;

    public static AuthManager getInstance() {
        if (mAuthManager == null) {
            mAuthManager = new AuthManager();
        }
        return mAuthManager;
    }

    public AuthManager() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        userReference = FirebaseDatabase.getInstance().getReference(Constants.FIRE_BASE_USER_NODE);
    }

    public void login(String email, String password, final AuthCallback callback) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = task.getResult().getUser().getUid();
                    userReference.child(userId)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    callback.onSuccess(user);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    callback.onError(databaseError.getMessage());
                                }
                            });
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    public void createUser(final User user, String password, final AuthCallback callback) {
        mFirebaseAuth.createUserWithEmailAndPassword(user.email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = task.getResult().getUser().getUid();
                    user.id = userId;
                    userReference.child(userId)
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    callback.onSuccess(user);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            callback.onError(e.getMessage());
                        }
                    });
                } else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });
    }

    public void getCurrentUser(final AuthCallback authCallback) {
        String userId = mFirebaseAuth.getCurrentUser().getUid();
        getUser(userId, authCallback);
    }

    public void getUser(String userId, final AuthCallback authCallback) {
        userReference.child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        authCallback.onSuccess(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        authCallback.onError(databaseError.getMessage());
                    }
                });
    }

    public void updateUser(User user, final AuthCallback authCallback) {
        String userId = user.id;
        userReference.child(userId)
                .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        authCallback.onSuccess(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        authCallback.onError(e.getMessage());
                    }
                });
    }

    public interface AuthCallback {
        void onSuccess(User user);

        void onError(String reason);
    }
}
