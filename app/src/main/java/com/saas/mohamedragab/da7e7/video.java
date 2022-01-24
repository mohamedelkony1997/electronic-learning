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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class video extends AppCompatActivity {
    String names[] = {"ahmed mahmoud", "mohamed khaled", "mahmoud ali", "eslam mohamed", "osama ali"};
    int images[] = {R.drawable.student, R.drawable.user2, R.drawable.user2, R.drawable.student, R.drawable.user2,};
    String comments[] = {"Good Teacher ♥", "keep going ", "good presentation", "عمل موفق ", "nice teacher"};
    String date[] = {"12/10/2018", "14/10/2018", "17/10/2018", "20/10/2018", "23/10/2018"};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        listView = (ListView) findViewById(R.id.listview);
        MyAdapter4 adapter = new MyAdapter4(this, names, comments, images, date);
        listView.setAdapter(adapter);

        VideoView video1 = (VideoView) findViewById(R.id.lesson_video);
        video1.setVideoURI(Uri.parse("android.resource://" + "com.saas.mohamedragab.da7e7" + "/" + R.raw.videoplayback));
        video1.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video1);
        video1.setMediaController(mediaController);
    }
}

class MyAdapter4 extends ArrayAdapter {
    public int images[];
    public String comments[];
    public String[] names;
    public String[] dates;

    public MyAdapter4(Context context, String[] names, String[] comment, int[] images, String[] date) {
        super(context, R.layout.comment, R.id.name, names);

        this.names = names;
        this.comments = comment;
        this.images = images;
        this.dates = date;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.comment, parent, false);

        ImageView imageView = (ImageView) row.findViewById(R.id.image_student);
        TextView name = (TextView) row.findViewById(R.id.name);
        TextView comment = (TextView) row.findViewById(R.id.comment);
        TextView date = (TextView) row.findViewById(R.id.date);

        imageView.setImageResource(images[position]);
        name.setText(names[position]);
        comment.setText("" + comments[position]);
        date.setText("" + dates[position]);


        return row;
    }
}
