package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class individual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progress);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i <= 100; i++) {
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bar.setProgress(i);
                    i = i + 2;
                }
            }
        };
        thread.start();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.messenger:
                        Toast.makeText(individual.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
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
}
