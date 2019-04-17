package com.example.mylenovo.filemanager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

public class Plyr_Activity extends AppCompatActivity {

    SeekBar seekBar;
    Button btn_prev, btn_next, btn_pause;
    TextView songName;

    static MediaPlayer myMediaPlayer;
    VideoView videoView;
    int position;
    ArrayList<File> myVideos;
    //Thread updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_plyr_);

        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        videoView = (VideoView)findViewById(R.id.videoView);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        myVideos = (ArrayList)bundle.getParcelableArrayList("videos");

        position=bundle.getInt("pos", 0);


        Uri u = Uri.parse(myVideos.get(position).toString());

        videoView.setVideoURI(u);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home);
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}

