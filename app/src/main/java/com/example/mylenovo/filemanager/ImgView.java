package com.example.mylenovo.filemanager;


        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.media.MediaPlayer;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.ImageView;
        import android.widget.MediaController;
        import android.widget.VideoView;

        import java.io.File;
        import java.util.ArrayList;

public class ImgView extends AppCompatActivity {

    static MediaPlayer myMediaPlayer;
    ImageView imageView;
    int position;
    ArrayList<File> images;
    //Thread updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.activity_main2);
        setContentView(R.layout.activity_img_view);

        imageView = (ImageView)findViewById(R.id.imge);

        getSupportActionBar().setTitle("Now showing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        images = (ArrayList)bundle.getParcelableArrayList("songs");

        position=bundle.getInt("pos", 0);

        Uri u = Uri.parse(images.get(position).getAbsolutePath());
        String imgPath = images.get(position).getAbsolutePath();

        imageView.setImageURI(u);

        File imgFile = new  File(imgPath);
        if(imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }

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
