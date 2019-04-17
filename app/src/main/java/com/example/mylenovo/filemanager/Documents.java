package com.example.mylenovo.filemanager;


        import android.Manifest;
        import android.content.ActivityNotFoundException;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Environment;
        import android.support.v4.content.FileProvider;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.ContextMenu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.karumi.dexter.Dexter;
        import com.karumi.dexter.PermissionToken;
        import com.karumi.dexter.listener.PermissionDeniedResponse;
        import com.karumi.dexter.listener.PermissionGrantedResponse;
        import com.karumi.dexter.listener.PermissionRequest;
        import com.karumi.dexter.listener.single.PermissionListener;

        import java.io.File;
        import java.net.URLConnection;
        import java.util.ArrayList;

public class Documents extends AppCompatActivity {

    ListView list;
    Spinner spinner;
    int posit, i=0;
    String filename;
    //String f_name, fn;
    String[] op={"Copy", "Paste", "Move"};
    final ArrayList<String> arl=new ArrayList<>();
    int img = R.drawable.pdf_img;
    int pimg = R.drawable.ppt_im;
    int wd = R.drawable.mword;
    int mexc = R.drawable.mexcel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_document);

        list = (ListView)findViewById(R.id.myList);
        registerForContextMenu(list);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        posit = bundle.getInt("pos");

        runtimePermission();
        //display();
        //dispaly();
    }

    public void runtimePermission()
    {
        Dexter.withActivity(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //Toast.makeText(Documents.this, "Yess", Toast.LENGTH_SHORT).show();
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

    public ArrayList<File> findSong(File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();

        File files[] = file.listFiles();


        if(posit==0) {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden())
                    arrayList.addAll(findSong(singleFile));
                else {
                    if (singleFile.getName().endsWith(".pdf")) {
                        arrayList.add(singleFile);
                        filename=singleFile.getAbsolutePath();
                        arl.add(filename);
                        // System.out.println(filename[i]);
                        i++;
                    }
                }
            }
        }

        else if(posit==1)
        {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden())
                    arrayList.addAll(findSong(singleFile));
                else {
                    if (singleFile.getName().endsWith(".docx")) {
                        arrayList.add(singleFile);
                        filename=singleFile.getAbsolutePath();
                        arl.add(filename);
                        i++;
                    }
                }
            }
        }
        else if(posit==2)
        {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden())
                    arrayList.addAll(findSong(singleFile));
                else {
                    if (singleFile.getName().endsWith(".ppt")) {
                        arrayList.add(singleFile);
                        filename=singleFile.getAbsolutePath();
                        arl.add(filename);
                        i++;
                    }
                }
            }
        }
        else if(posit==3)
        {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden())
                    arrayList.addAll(findSong(singleFile));
                else {
                    if (singleFile.getName().endsWith(".xslx")) {
                        arrayList.add(singleFile);
                        filename=singleFile.getAbsolutePath();
                        arl.add(filename);
                        i++;
                    }
                }
            }
        }
        else
        {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden())
                    arrayList.addAll(findSong(singleFile));
                else {
                    if (singleFile.getName().endsWith(".txt")) {
                        arrayList.add(singleFile);
                        filename=singleFile.getAbsolutePath();
                        arl.add(filename);
                        i++;
                    }
                }
            }
        }


        return arrayList;
    }

    public void display()
    {
        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());

        final String[] songs = new String[mySongs.size()];

        for(int i=0; i<mySongs.size(); i++)
        {

                songs[i] = mySongs.get(i).getName().toString().replace(".pdf", " ").replace(".docx", " ")
                        .replace(".ppt", " ").replace(".xslx", " ").replace(".txt", " ");
            //songs[i]=arl.get(i);
        }
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.sample_view, R.id.textView, songs);
        //list.setAdapter(arrayAdapter);


        if(posit==0) {
            //CustomAdapter adapter = new CustomAdapter(this, songs, img, spinner);
            CustomAdapter1 adapter = new CustomAdapter1(this, songs, img);
            list.setAdapter(adapter);
        }
        else if(posit==1)
        {
            CustomAdapter1 adapter = new CustomAdapter1(this, songs, wd);
            list.setAdapter(adapter);
        }
        else if(posit==2)
        {
            CustomAdapter1 adapter = new CustomAdapter1(this, songs, pimg);
            list.setAdapter(adapter);
        }
        else if(posit==3)
        {
            CustomAdapter1 adapter = new CustomAdapter1(this, songs, mexc);
            list.setAdapter(adapter);
        }
        else
        {
            CustomAdapter1 adapter = new CustomAdapter1(this, songs, img);
            list.setAdapter(adapter);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //registerForContextMenu(view);
                String f_name = arl.get(position);

                File file = new File(f_name);
                Uri uri;

                if(posit==0){
                    uri = Uri.parse("content://"+"com.example.mylenovo.filemanager"+"/" +f_name);
                }
                else
                {
                    uri = Uri.parse(f_name);
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);

               // Toast.makeText(getApplicationContext(), uri.getPath(), Toast.LENGTH_SHORT).show();
                //Uri uri = Uri.parse(path);
                intent.setDataAndType(uri, URLConnection.guessContentTypeFromName(f_name));
                Intent intentChooser = Intent.createChooser(intent, "Choose Application");
                startActivity(intentChooser);


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
}


