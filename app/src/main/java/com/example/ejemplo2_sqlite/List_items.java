package com.example.ejemplo2_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class List_items extends BaseAdapter {
    private Context context;
    private int layout;
    private List<String> names;
    private int c;

    public List_items(Context context, int layout, List<String> names){
     this.context=context;
     this.layout=layout;
     this.names=names;
     this.c=0;
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int postion) {
        return names.get(postion);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup viewGroup) {
        View v=ConvertView;
        LayoutInflater inflater=LayoutInflater.from(this.context);
        v=inflater.inflate(R.layout.activity_list_items,null);
        String actualNombre=names.get(position);
        TextView textView=(TextView)v.findViewById(R.id.articulo);
        textView.setText(actualNombre);
        TextView t2=(TextView)v.findViewById(R.id.Contador);
        t2.setText(String.valueOf(c));
        c++;
        return v;
    }
}
