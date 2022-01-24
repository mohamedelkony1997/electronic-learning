package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class mainLearning extends AppCompatActivity {
    Spinner subject;
    String languages[] = {"Arabic", "English", "Math", "French", "Chemistery", "physics"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_learning);
        subject = (Spinner) findViewById(R.id.spinner_subject);
        ArrayAdapter<String> Adapter_subject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, languages);
        subject.setAdapter(Adapter_subject);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.messenger:
                        Toast.makeText(mainLearning.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
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

    void gosearch() {
        startActivity(new Intent(this, searchfriend.class));

    }

    void goaccount() {
        startActivity(new Intent(this, profileEdit.class));


    }

    void goawards() {
        startActivity(new Intent(this, awards.class));

    }

    public void visiteprofile(View view) {
        startActivity(new Intent(this, profileEdit.class));

    }

    public void chooseTeacher(View view) {
        startActivity(new Intent(this, videoteachers.class));

    }
}
