package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class changepass2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass2);
    }

    public void changepass3(View view) {
        startActivity(new Intent(this,changepass3.class));

    }
}
