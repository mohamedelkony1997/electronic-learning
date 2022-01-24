package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email, pass;
    private FirebaseAuth mAuth;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bar = (ProgressBar) findViewById(R.id.progress);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
    }


    public void signup(View view) {
        startActivity(new Intent(this, signup.class));
        finish();
    }

    public void checkpermissions(final View view) {

        String email_txt = email.getText().toString().trim();
        String pass_txt = pass.getText().toString().trim();
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
        bar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email_txt, pass_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    bar.setVisibility(view.GONE);
                    finish();
                    Intent intent = new Intent(login.this, mainStudent.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    bar.setVisibility(view.GONE);

                    Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    public void forgetpass(View view) {
        startActivity(new Intent(this, changepass1.class));
        finish();
    }
}
