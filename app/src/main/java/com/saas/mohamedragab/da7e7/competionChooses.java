package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class competionChooses extends AppCompatActivity {
    Spinner way, subject;
    String classes[] = {"Null", "Individual", "Random Way", "Invite Friend"};
    String languages[] = {"Arabic", "English", "Math", "French", "Chemistery", "physics"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competion_chooses);
        way = (Spinner) findViewById(R.id.spinner_way);
        ArrayAdapter<String> Adapter_way = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        way.setAdapter(Adapter_way);
        way.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (way.getSelectedItemId() == 3) {
                    gosearch();
                }
                if (way.getSelectedItemId() == 1) {
                    goindividual();
                }
                if (way.getSelectedItemId() == 2) {
                    Toast.makeText(competionChooses.this, "Random Still Programmed! ", Toast.LENGTH_SHORT).show();

                }
                if (way.getSelectedItemId() == 0) {
                    Toast.makeText(competionChooses.this, "please Choose a correct way ! ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
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
                        Toast.makeText(competionChooses.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
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

    void gosearch1() {
        startActivity(new Intent(this, searchfriend.class));

    }

    void goaccount() {
        startActivity(new Intent(this, profileEdit.class));


    }

    void gosearch() {
        startActivity(new Intent(this, searchfriend.class));

    }

    void goawards() {
        startActivity(new Intent(this, awards.class));

    }

    void goindividual() {
        startActivity(new Intent(this, individual.class));

    }

    public void visiteprofile(View view) {
        startActivity(new Intent(this, profileEdit.class));

    }

    public void competion(View view) {
        startActivity(new Intent(this, competion.class));

    }
}
