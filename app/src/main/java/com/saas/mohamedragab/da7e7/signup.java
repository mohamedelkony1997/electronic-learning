package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {
    Spinner spinner;
    EditText email, pass, repass;
    private FirebaseAuth mAuth;
    CheckBox agree;
    private static final int CHOOSE_IMAGE = 101;
    CircleImageView imageView;
    EditText username;
    Uri uriprofileImage;
    String profileImageUrl;
    ProgressBar bar;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        spinner = (Spinner) findViewById(R.id.spinner);
        String classes[] = {"First Year", "Second Year", "Third Year"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        spinner.setAdapter(Adapter);
        agree = (CheckBox) findViewById(R.id.checkbox);

        bar = (ProgressBar) findViewById(R.id.progress);
        signup = (Button) findViewById(R.id.signup);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repassword);
        imageView = (CircleImageView) findViewById(R.id.image);
        username = (EditText) findViewById(R.id.username);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriprofileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriprofileImage);
                imageView.setImageBitmap(bitmap);
                UploadImageToFireBaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void UploadImageToFireBaseStorage() {

        StorageReference profileimageref = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        if (uriprofileImage != null) {
            profileimageref.putFile(uriprofileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    profileImageUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void change(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select  profile image !"), CHOOSE_IMAGE);

    }

    public void login(View view) {
        startActivity(new Intent(this, login.class));
        finish();
    }


    public void RegisterUser(final View view) {
        final String email_txt = email.getText().toString().trim();
        String pass_txt = pass.getText().toString().trim();
        String re_pass_txt = repass.getText().toString().trim();

        String user = username.getText().toString().trim();
        if (user.isEmpty()) {
            username.setError("name is Required !");
            username.requestFocus();
            return;

        }
        if (email_txt.isEmpty()) {
            email.setError("Email is Required !");
            email.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_txt).matches()) {
            email.setError("Please Enter Valid Email !");
            email.requestFocus();
            return;
        }

        if (pass_txt.isEmpty()) {
            pass.setError("password is Required !");
            pass.requestFocus();
            return;

        }

        if (pass_txt.length() < 6) {
            pass.setError("Minimum length of password should be 6");
            pass.requestFocus();
            return;
        }
        if (!re_pass_txt.equals(pass_txt)) {
            repass.setError("Password and Confirm password not match !");
            repass.requestFocus();
            return;
        }
        if (!agree.isChecked()) {

            agree.setError("You should agree to Terms of services !");
            agree.requestFocus();
            return;
        }


        FirebaseUser user1 = mAuth.getCurrentUser();
        if (user1 != null && profileImageUrl != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(user)
                    .setPhotoUri(Uri.parse(profileImageUrl)).build();

            user1.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(signup.this, "profile Updated", Toast.LENGTH_SHORT).show();
                }
            });

        }

        bar.setVisibility(View.VISIBLE);
        signup.setEnabled(false);

        mAuth.createUserWithEmailAndPassword(email_txt, pass_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    bar.setVisibility(view.GONE);


                    Intent intent = new Intent(signup.this, mainStudent.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {

                    bar.setVisibility(view.GONE);

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(signup.this, "You are Already Registered ! ", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

        });

    }
}

