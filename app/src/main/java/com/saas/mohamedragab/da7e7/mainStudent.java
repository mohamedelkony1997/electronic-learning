package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class mainStudent extends AppCompatActivity {
    private FirebaseAuth mAuth;
    CircleImageView imageView;
    TextView name;
    public static final String GOOGLE_ACCOUNT = "google_account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);

        imageView = (CircleImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        loadUserInformation();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.messenger:
                        Toast.makeText(mainStudent.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rewards:
                        goawards();
                        break;
                    case R.id.browse:
                        gosearch();
                        break;
                    case R.id.account:
                        goaccount();
                        break;

                }


                return true;
            }
        });

    }

    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imageView);

            }
            if (user.getDisplayName() != null) {
                name.setText(user.getDisplayName());

            }
        }

    }

    void gosearch() {
        startActivity(new Intent(this, searchfriend.class));

    }

    void goaccount() {
        startActivity(new Intent(this, profileEdit.class));


    }

    void goawards() {
        startActivity(new Intent(this, awards.class));

    }

    public void competionChooses(View view) {
        startActivity(new Intent(this, competionChooses.class));
    }

    public void learningChooses(View view) {
        startActivity(new Intent(this, mainLearning.class));
    }

    public void visiteprofile(View view) {
        startActivity(new Intent(this, profileEdit.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, login.class));
            finish();

        }
    }

}
