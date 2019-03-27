package com.example.mylenovo.filemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdaptar extends BaseAdapter  {


    String[] topics;
    int[] img;
    Context context;
    private LayoutInflater inflater;

    CustomAdaptar(Context context, String[] topics, int[] img)
    {
        this.context = context;
        this.topics = topics;
        this.img = img;
    }

    public int getCount() {
        return topics.length;
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

        if(convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_view, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        TextView txt = (TextView)convertView.findViewById(R.id.toppical);

        imageView.setImageResource(img[position]);
        txt.setText(topics[position]);

        return convertView;
    }
}

