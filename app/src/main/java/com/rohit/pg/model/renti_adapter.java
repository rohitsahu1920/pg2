package com.rohit.pg.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class renti_adapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<renti_model> renti_List;

    public renti_adapter(Context context, int layout, ArrayList<renti_model> renti_List) {
        this.context = context;
        this.layout = layout;
        this.renti_List = renti_List;
    }


    @Override
    public int getCount() {
        return renti_List.size();
    }

    @Override
    public Object getItem(int i) {
        return renti_List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    private class ViewHolder
    {
        ImageView imageView;
        TextView First_name,Lastname,pg_name;
    }

}
