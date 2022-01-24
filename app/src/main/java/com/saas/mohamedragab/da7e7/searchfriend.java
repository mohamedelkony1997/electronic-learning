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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class searchfriend extends AppCompatActivity implements SearchView.OnQueryTextListener {
    String names[] = {"mohamed shawky", "ahmed ali", "mahmoud ragab", "eslam mohamed", "mohamed sabri"};
    String levels[] = {"level 7", "level 6", "level 4", "level 3", "level 2"};
    String points[] = {"1500", "1300", "1200", "1100", "1000"};
    int images[] = {R.drawable.student, R.drawable.user2, R.drawable.user2, R.drawable.student, R.drawable.user2,};
    ListView listView;
    SearchView searchView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfriend);
        listView = (ListView) findViewById(R.id.listview);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        adapter = new MyAdapter(this, names, points, images, levels);

        listView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.messenger:
                        Toast.makeText(searchfriend.this, "Messenger Still programming", Toast.LENGTH_SHORT).show();
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

    void goawards() {
        startActivity(new Intent(this, awards.class));

    }

    void goaccount() {
        startActivity(new Intent(this, profileEdit.class));


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.getFilter().filter(newText);

        return false;
    }
}

class MyAdapter extends ArrayAdapter {
    public int images[];
    public String points[];
    public String[] names;
    public String[] levels;

    public MyAdapter(Context context, String[] names, String[] points, int[] images, String[] levels) {
        super(context, R.layout.searchlistview, R.id.name, names);

        this.names = names;
        this.points = points;
        this.images = images;
        this.levels = levels;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.searchlistview, parent, false);
        ImageView imageView = (ImageView) row.findViewById(R.id.image_student);
        TextView name = (TextView) row.findViewById(R.id.name);
        TextView point = (TextView) row.findViewById(R.id.points);
        TextView level = (TextView) row.findViewById(R.id.levels);

        imageView.setImageResource(images[position]);
        name.setText(names[position]);
        point.setText(points[position]);
        level.setText(levels[position]);

        return row;
    }
}
