package com.example.mylenovo.filemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    private ListView listView;
    private String[] topics;

    int img[]={R.drawable.ic_image_black_24dp, R.drawable.ic_video_library_black_24dp, R.drawable.ic_library_music_black_24dp,
            R.drawable.ic_description_black_24dp, R.drawable.ic_file_download_black_24dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView)findViewById(R.id.listView);
        topics = getResources().getStringArray(R.array.topic);

        CustomAdaptar adaptar = new CustomAdaptar(this, topics, img);
        listView.setAdapter(adaptar);


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
