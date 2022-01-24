package com.saas.mohamedragab.da7e7;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class videoteachers extends AppCompatActivity {
    String names[] = {"ahmed mahmoud", "mohamed khaled", "mahmoud ali", "eslam mohamed", "osama ali"};
    int rating[] = {5, 4, 4, 3, 3};
    int salary[] = {7, 5, 4, 6, 2};
    int images[] = {R.drawable.professor, R.drawable.teacher, R.drawable.teacher, R.drawable.professor, R.drawable.teacher,};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoteachers);
        listView = (ListView) findViewById(R.id.listview);
        MyAdapter2 adapter = new MyAdapter2(this, names, rating, images, salary);
        listView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.messenger:
                        Toast.makeText(videoteachers.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
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

    public void golessons(View view) {
        startActivity(new Intent(this, teacherCourses.class));

    }

    void goawards() {
        startActivity(new Intent(this, awards.class));

    }
}

class MyAdapter2 extends ArrayAdapter {
    public int images[];
    public int salarys[];
    public String[] names;
    public int[] rates;

    public MyAdapter2(Context context, String[] names, int[] rate, int[] images, int[] salary) {
        super(context, R.layout.teacherslistview, R.id.name, names);

        this.names = names;
        this.salarys = salary;
        this.images = images;
        this.rates = rate;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.teacherslistview, parent, false);
        ImageView imageView = (ImageView) row.findViewById(R.id.teacher);
        TextView name = (TextView) row.findViewById(R.id.name);
        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.rating);
        TextView salary = (TextView) row.findViewById(R.id.salary);

        imageView.setImageResource(images[position]);
        name.setText(names[position]);
        salary.setText("" + salarys[position]);
        ratingBar.setRating(Float.parseFloat("" + rates[position]));

        return row;
    }
}