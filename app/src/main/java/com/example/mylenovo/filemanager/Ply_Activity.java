package com.example.mylenovo.filemanager;

import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.PorterDuff;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.SeekBar;
        import android.widget.TextView;

        import java.io.File;
        import java.util.ArrayList;

public class Ply_Activity extends AppCompatActivity {

    SeekBar seekBar;
    Button btn_prev, btn_next, btn_pause;
    TextView songName;

    static MediaPlayer myMediaPlayer;
    int position;
    ArrayList<File> mySongs;
    Thread updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ply_);

        //seekBar.getProgressDrawable().setColorFilter(Color.parseColor("ffffff"), PorterDuff.Mode.SRC_IN);

        btn_prev = (Button) findViewById(R.id.btn_prev);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_pause = (Button) findViewById(R.id.btn_pause);

        songName = (TextView)findViewById(R.id.marqueeText);

        seekBar = (SeekBar)findViewById(R.id.seekBar);

        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        updateSeekbar = new Thread()
        {
            @Override
            public void run() {
                int td = myMediaPlayer.getDuration();
                int cpos=0;

                while(cpos<td)
                {
                    try {
                        sleep(500);
                        cpos = myMediaPlayer.getCurrentPosition();
                        seekBar.setProgress(cpos);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        if(myMediaPlayer != null)
        {
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mySongs = (ArrayList)bundle.getParcelableArrayList("songs");

        position=bundle.getInt("pos", 0);

        String sname = mySongs.get(position).getName().toString();
        String sName = intent.getStringExtra("songname");

        songName.setText(sName);
        songName.setSelected(true);

        Uri u = Uri.parse(mySongs.get(position).toString());

        myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

        myMediaPlayer.start();

        seekBar.setMax(myMediaPlayer.getDuration());
        updateSeekbar.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(myMediaPlayer.getDuration());

                if(myMediaPlayer.isPlaying())
                {
                    btn_pause.setBackgroundResource(R.drawable.ic_play);
                    myMediaPlayer.pause();
                }
                else
                {
                    btn_pause.setBackgroundResource(R.drawable.ic_pause);
                    myMediaPlayer.start();
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posi;

                myMediaPlayer.stop();
                myMediaPlayer.release();

                position--;

                if(position<0){
                    position=(mySongs.size()-1);
                    posi=position;
                }
                else
                {
                    posi=position;
                }

                Uri u = Uri.parse(mySongs.get(posi).toString());

                myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);

                String snm=mySongs.get(posi).getName().toString();
                songName.setText(snm);

                myMediaPlayer.start();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posi;

                myMediaPlayer.stop();
                myMediaPlayer.release();

                position++;

                if(position<mySongs.size())
                {
                    posi=position;
                }
                else {
                    position=0;
                    posi=position;
                }

                Uri u = Uri.parse(mySongs.get(posi).toString());
                myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

                String snm = mySongs.get(posi).getName().toString();
                songName.setText(snm);

                myMediaPlayer.start();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home);
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
