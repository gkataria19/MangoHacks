package soya.com.firebaseblind;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.*;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //make
    FirebaseStorage storage = FirebaseStorage.getInstance();
    //FirebaseStorage st2 = FirebaseStorage().getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Upload to Firebase
        StorageReference storageRef = storage.getReference();
        //StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

        Uri file = Uri.fromFile(new File("storage/emulated/0/pic.jpg"));
        StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }

            //https://firebasestorage.googleapis.com/v0/b/blindapp-c2794.appspot.com/o/images%2Fpic.jpg?alt=media&token=e728b855-3a56-4e0b-aa39-f7807c3f81df
        });

    }


}
