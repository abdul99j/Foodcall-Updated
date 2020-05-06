package com.example.foodcall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class add_Image extends AppCompatActivity {

    ImageView image;
    String image_name = "header";
    ProgressDialog load;
    Button load_image;
    Button upload_image;

    Uri imageUri = Uri.EMPTY;
    String img_64 = null;
    private static final int PICK_IMAGE = 100;

    Integer iWid;
    Integer iLen;

    private StorageReference sRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__image);

        image = findViewById(R.id.selected_image);
        load_image = findViewById(R.id.open_gallery);
        upload_image = findViewById(R.id.upload_img);

        load = new ProgressDialog(add_Image.this);
        mAuth = FirebaseAuth.getInstance();
        sRef = FirebaseStorage.getInstance().getReference();

        load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load.setMessage("Uploading Image to Server.");
                load.show();

                String uid = mAuth.getCurrentUser().getUid();

                StorageReference ref = sRef.child("images/users/" + uid + "/" + image_name + ".jpg");
                ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri done = taskSnapshot.getUploadSessionUri();
                        //Toast.makeText(getApplicationContext(), done.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Image uploaded successfully!"
                                , Toast.LENGTH_SHORT).show();
                        load.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Image upload failed!"
                                , Toast.LENGTH_SHORT).show();
                        load.dismiss();
                    }
                });
            }
        });

    }

    void openGallery() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            Toast toast = Toast.makeText(getApplicationContext(), "Picture read from Gallery", Toast.LENGTH_SHORT);
            toast.show();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            File f = new File(imageStream.toString(), "");

            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            image.setImageBitmap(selectedImage);

            //img_64 = encodeImage(selectedImage);
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
}
