package com.rohit.pg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rohit.pg.R;
import com.rohit.pg.model.renti_model;
import com.rohit.pg.sql.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class renti_list_view extends AppCompatActivity {

    List<renti_model> list;
    ListView listView;
    ArrayList<String> theList;
    renti_model fname;
    String last_name,gender,father_name,mobile,p_mobile,occupation,permanent_add,working_add,pg_num,room_num,bed_num;
    byte[] id_image,profile_image;
    FloatingActionButton floatingActionButton;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renti_list_view);

        listView = findViewById(R.id.list);
        dataBaseHelper = new DataBaseHelper(this);
        floatingActionButton = findViewById(R.id.fab_Note);
        theList = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),rentee_registration.class);
                startActivity(i);
            }
        });

        retive();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                renti_model Renti_model = list.get(i);
                Intent intent = new Intent(getApplicationContext(),show_details.class);
                intent.putExtra("renti",Renti_model);
                startActivity(intent);
            }
        });
    }
    public void retive()
    {
        list = dataBaseHelper.getDetails();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }
}
