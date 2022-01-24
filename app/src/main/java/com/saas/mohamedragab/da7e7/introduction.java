package com.saas.mohamedragab.da7e7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

public class introduction extends AppCompatActivity {
    private static final int RC_SIGN_IN = 101;
    public String description[] = {
            "     Welcome to our Competive Educational Application ,you can learn from than one sourse and enter competions with friends ,please login to access our application . ",
            "  In our application students can learn online from electronic books and teachers videos students an ertifies from teachers by passing their Exams ,please login to access our application .",
            "  Also students can enter competions with their friends or foriegn people from application ,Students Collects Points from competions and enter high levels to certifies from our application ,please login to access our application . ",
            "  After Pass High Levels Excellent students certifies from our application and get access to courses free , why you wait ! Gaet access now to Learn and compete Free . "
    };
    public String identities[] = {"Null", "Student", "Teacher"};
    Spinner identity;
    GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "AndroidClarified";
    private SignInButton googleSignInButton;

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        identity = (Spinner) findViewById(R.id.spinner_identity);
        ArrayAdapter<String> Adapter_identity = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, identities);
        identity.setAdapter(Adapter_identity);


        googleSignInButton = findViewById(R.id.google);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

    }



    public void login(View view) {
        if (identity.getSelectedItemId() == 1) {
            startActivity(new Intent(this, login.class));
        } else if (identity.getSelectedItemId() == 2) {
            startActivity(new Intent(this, loginTeacher.class));
        } else {
            Toast.makeText(introduction.this, "You must choose correct identity !", Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, mainStudent.class);
        intent.putExtra(mainStudent.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
    }


}
