package com.rohit.pg.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                renti_model Renti_model = list.get(i);
                final String fname = Renti_model.getFirst_name();
                final String lname = Renti_model.getLast_name();
                final String phone = Renti_model.getMobile();
                final String w_phone = Renti_model.getWhatsapp();

                builder.setMessage("Note:- Data will be permanently delete..!")
                        .setCancelable(true)
                        .setTitle("Do you really want to Delete ?")
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Boolean delete = dataBaseHelper.delete(fname,lname,phone,w_phone);
                                if(delete == true)
                                {
                                    Toast.makeText(getApplicationContext(),"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                                    retive();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Problem in deleting Record",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert11 = builder.create();
                alert11.show();
                return false;
            }
        });

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
