package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SignupTeacher extends AppCompatActivity {
    String languages[] = {"Arabic", "English", "Math", "French", "Chemistery", "physics"};
    Spinner subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);
        subject = (Spinner) findViewById(R.id.spinner_subject);
        ArrayAdapter<String> Adapter_way = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, languages);
        subject.setAdapter(Adapter_way);

    }

    public void loginteacher(View view) {

        startActivity(new Intent(this, loginTeacher.class));
    }
}
