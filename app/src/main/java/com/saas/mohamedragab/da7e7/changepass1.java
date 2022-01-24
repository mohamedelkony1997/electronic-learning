package com.saas.mohamedragab.da7e7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class changepass1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass1);
    }

    public void introduction(View view) {
        startActivity(new Intent(this, introduction.class));

    }

    public void changepass2(View view) {
        startActivity(new Intent(this, changepass2.class));

    }
}
