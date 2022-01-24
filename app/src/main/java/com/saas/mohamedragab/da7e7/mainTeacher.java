package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mainTeacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
    }

    public void visiteteacherprofile(View view) {

        startActivity(new Intent(this, profileEdit.class));

    }
}
