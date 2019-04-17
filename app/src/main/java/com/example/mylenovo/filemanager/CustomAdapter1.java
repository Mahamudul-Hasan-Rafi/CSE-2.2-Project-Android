package com.example.mylenovo.filemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter1 extends BaseAdapter {

    String[] docs;
    Context context;
    int img;
    private LayoutInflater inflater;
    //Spinner spinner;
    String[] op={"Copy", "Paste", "Move"};

    public CustomAdapter1(Context context, String[] songs, int img) {
        this.context=context;
        this.docs=songs;
        this.img=img;
        //this.spinner=spinner;
    }

    @Override
    public int getCount() {
        return docs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override


    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null)
        {
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_view, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        TextView txt = (TextView)convertView.findViewById(R.id.textt);

        //Spinner spin = (Spinner)convertView.findViewById(R.id.spin);

        imageView.setImageResource(img);
        txt.setText(docs[position]);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.sample_view, R.id.textView, op);
        //spin.setAdapter(arrayAdapter);

        return convertView;
    }
}
