package com.example.mylenovo.filemanager;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;



public class StorageActivity extends AppCompatActivity {

    private ListView listView;
    private long backPressedTime;
    String f_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        listView = findViewById(R.id.list);

        /*String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();

        File file = new File(path);

        String[] list=file.list();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view, R.id.textView,list);
        listView.setAdapter(adapter);//*/

        runtimePermission();
    }

    public void runtimePermission()
    {
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //Toast.makeText(Documents.this, "Yess", Toast.LENGTH_SHORT).show();
                        // String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                        display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        //Toast.makeText(Documents.this, "nope", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    void  display()
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File file = new File(path);

        final File[] list=file.listFiles();
        String[] f_name = file.list();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view1, R.id.textView,f_name);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                File f_name = list[position];
                String pth = f_name.getAbsolutePath();
                f_path=pth;

                File fl = new File(pth);
                File[] lst = fl.listFiles();
                String[] file_name = fl.list();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.sample_view1, R.id.textView,file_name);
                listView.setAdapter(adapter);
            }
        });
    }

    /*public void onBackPressed()
    {
        //super.onBackPressed();
        if(backPressedTime+2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else {
            display();
            //Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }*/

    public void onBackPressed() {

            //super.onBackPressed();
            //return;

            finish();
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);


    }
}
