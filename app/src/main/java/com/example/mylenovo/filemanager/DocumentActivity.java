package com.example.mylenovo.filemanager;


import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

public class DocumentActivity extends AppCompatActivity {

    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        //getSupportActionBar().setTitle("Now showing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String[] docs=getResources().getStringArray(R.array.documents);

        myList=(ListView)findViewById(R.id.myList);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(DocumentActivity.this, R.layout.sample_view1, R.id.textView, docs);
        myList.setAdapter(arrayAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Documents.class).putExtra("pos", position));
            }
        });

    }

    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
