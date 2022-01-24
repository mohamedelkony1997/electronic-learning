package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileEdit extends AppCompatActivity {
    private FirebaseAuth mAuth;
    CircleImageView image;
    TextView name, phone, email, pass;
    Button logout;
    GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "AndroidClarified";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        image = (CircleImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        pass = (TextView) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        loadUserInformation();
        logout = (Button) findViewById(R.id.logout);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(profileEdit.this, "Signed out Completed !", Toast.LENGTH_SHORT).show();
                        go();
                    }
                });
            }
        });
    }

    public void go() {
        Intent intent = new Intent(this, introduction.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(image);

            }
            if (user.getDisplayName() != null) {
                name.setText(user.getDisplayName());

            }
            if (user.getEmail() != null) {
                email.setText(user.getEmail());

            }
            if (user.getPhoneNumber() != null) {
                phone.setText(user.getPhoneNumber());

            }

        }

    }


}
