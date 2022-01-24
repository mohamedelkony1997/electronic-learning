package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class loginTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupteacher1();
            }
        });
    }

    private void signupteacher1() {
        startActivity(new Intent(this, SignupTeacher.class));

    }

    public void checkpermissions(View view) {

        startActivity(new Intent(this, mainTeacher.class));
    }


    public void changepass1(View view) {
        startActivity(new Intent(this, changepass1.class));

    }
}
