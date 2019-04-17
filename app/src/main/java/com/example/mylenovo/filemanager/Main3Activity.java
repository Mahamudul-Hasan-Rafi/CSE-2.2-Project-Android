package com.example.mylenovo.filemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Main3Activity extends AppCompatActivity {

    private ListView listView;
    private String[] topics;

    int img[]={R.drawable.ic_image_black_24dp, R.drawable.ic_video_library_black_24dp, R.drawable.ic_library_music_black_24dp,
            R.drawable.ic_description_black_24dp, R.drawable.ic_file_download_black_24dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView = (ListView)findViewById(R.id.listView);
        topics = getResources().getStringArray(R.array.topic);

        CustomAdaptar adaptar = new CustomAdaptar(this, topics, img);
        listView.setAdapter(adaptar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = topics[position];

                //Intent myFile = new Intent(Intent.ACTION_GET_CONTENT);

                //if(value.equals("Downloads"))
                   // myFile.setType("*/*");

                if(value.equals("Images")) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), ImageAcyivity.class));
                }

                else if(value.equals("Videos"))
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                }
                  //  myFile.setType("video/*");

                else if(value.equals("Audios"))
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(), MusicActivity.class));
                }


                else if(value.equals("Documents")) {

                    finish();
                    startActivity(new Intent(getApplicationContext(), DocumentActivity.class));
                }

                else if(value.equals("Storage"))
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(), StorageActivity.class));
                }


                //myFile.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

                //startActivity(myFile);
            }
        });


    }

    public void onBackPressed() {

        //super.onBackPressed();
        finish();
        openActivity1();

    }
    public void openActivity1()
    {
        finish();
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}

