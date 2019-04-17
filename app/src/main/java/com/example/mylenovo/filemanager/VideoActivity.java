package com.example.mylenovo.filemanager;



        import android.Manifest;
        import android.content.Intent;
        import android.os.Environment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.ContextMenu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.karumi.dexter.Dexter;
        import com.karumi.dexter.PermissionToken;
        import com.karumi.dexter.listener.PermissionDeniedResponse;
        import com.karumi.dexter.listener.PermissionGrantedResponse;
        import com.karumi.dexter.listener.PermissionRequest;
        import com.karumi.dexter.listener.single.PermissionListener;

        import java.io.File;
        import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_video);

        //getSupportActionBar().setTitle("Now showing");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = (ListView)findViewById(R.id.myList);
        registerForContextMenu(list);

        runtimePermission();
        //dispaly();
    }

    public void runtimePermission()
    {
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //Toast.makeText(MainActivity.this, "Yess", Toast.LENGTH_SHORT).show();
                        display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        //Toast.makeText(MainActivity.this, "nope", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findVideo(File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();

        File files[] = file.listFiles();

        for(File singleFile:files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
                arrayList.addAll(findVideo(singleFile));
            else
            {
                if(singleFile.getName().endsWith(".mp4") || singleFile.getName().endsWith(".mkv")||singleFile.getName().endsWith(".webm"))
                {
                    arrayList.add(singleFile);
                }
            }
        }

        return arrayList;
    }

    public void display()
    {
        final ArrayList<File> myVideos = findVideo(Environment.getExternalStorageDirectory());

        String[] videos = new String[myVideos.size()];

        for(int i=0; i<myVideos.size(); i++)
        {
            videos[i] = myVideos.get(i).getName().toString().replace(".mp4", " ").replace(".mkv", " ")
                    .replace(".webm", " ");

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.sample_view1, R.id.textView, videos);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String videoName = list.getItemAtPosition(position).toString();

                startActivity(new Intent(getApplicationContext(), Plyr_Activity.class).putExtra("videos",myVideos).putExtra("videoname",videoName).putExtra("pos", position));
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.example_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.copy:
                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.move:
                Toast.makeText(this, "Move", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.paste:
                Toast.makeText(this, "Paste", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                return  true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
