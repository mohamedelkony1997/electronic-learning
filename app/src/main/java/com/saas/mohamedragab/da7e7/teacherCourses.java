package com.saas.mohamedragab.da7e7;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class teacherCourses extends AppCompatActivity {
    String titles[] = {"الدرس الاول ", "الدرس الثاني ", "الدرس الثالث", "الدرس الرابع", "الدرس الخامس"};
    int rating[] = {4, 3, 4, 5, 3};
    String description[] = {" مقدمه عن الفزياء", "الدوائر الكهربيه والتيار الكهربي ", "قوي الحركه المغناطيسيه", "الفزياء الكميائيه", "الفزياء الحديثه"};
    String date[] = {"12/10/2018", "14/10/2018", "17/10/2018", "20/10/2018", "23/10/2018"};

    ListView listView;
    VideoView video1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_courses);
        listView = (ListView) findViewById(R.id.listview);
        MyAdapter3 adapter = new MyAdapter3(this, titles, rating, description, date);
        listView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.messenger:
                        Toast.makeText(teacherCourses.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
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


    public void govideo(View view) {
        startActivity(new Intent(this, video.class));

    }
}

class MyAdapter3 extends ArrayAdapter {
    public String dates[];
    public String descriptions[];
    public String[] titles;
    public int[] rates;

    public MyAdapter3(Context context, String[] title, int[] rate, String[] description, String[] date) {
        super(context, R.layout.lessonslistview, R.id.title, title);

        this.dates = date;
        this.descriptions = description;
        this.titles = title;
        this.rates = rate;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.lessonslistview, parent, false);

        TextView title = (TextView) row.findViewById(R.id.lesson_title);
        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.lesson_rating);
        TextView date = (TextView) row.findViewById(R.id.lesson_date);
        TextView description = (TextView) row.findViewById(R.id.lesson_description);
        VideoView video1 = (VideoView) row.findViewById(R.id.lesson_video);
        video1.setVideoURI(Uri.parse("android.resource://" + "com.saas.mohamedragab.da7e7" + "/" + R.raw.videoplayback));
        video1.start();

        title.setText("" + titles[position]);
        date.setText("" + dates[position]);
        ratingBar.setRating(Float.parseFloat("" + rates[position]));
        description.setText("" + descriptions[position]);


        return row;
    }
}