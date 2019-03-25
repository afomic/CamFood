package afomic.com.camfood.helper;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import afomic.com.camfood.Constants;

public class FirebaseFileService {
    public void uploadFile(Uri uri, final UploadServiceCallback callback) {
        final StorageReference foodImageRef = FirebaseStorage.getInstance().getReference(Constants.FIRE_BASE_FOOD_IMAGE_NODE)
                .child(uri.getLastPathSegment());
        final UploadTask uploadTask = foodImageRef
                .putFile(uri);
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
                    callback.onSuccess(downloadUri.toString());

                } else {
                    callback.onFailure(task.getException().getMessage());
                }
            }
        });
    }

    public interface UploadServiceCallback {
        void onSuccess(String downloadUrl);

        void onFailure(String reason);
    }
}
