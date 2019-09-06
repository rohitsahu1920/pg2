package com.rohit.pg.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.rohit.pg.R;
import com.rohit.pg.model.renti_model;
import com.rohit.pg.sql.DataBaseHelper;

public class show_details extends AppCompatActivity {

    ImageView logo, whatsapp, call, delete, id, back;
    TextView first_name, last_name, gender, father_name, mobile,whatsapp_no, parents_mobile, occupation, p_add, c_add, pg_name, room_number, bed_number;
    String fname,lname,gender1,father,pnone,w_phone,f_mobile,occu,p_address,c_address,pg,room,bed;
    byte[] id_img;
    Button update, pdf, alert;
    private static final int REQUEST_PHONE_CALL = 1;
    Bitmap id1;
    int image_len;
    byte[] image;
    DataBaseHelper dataBaseHelper;

    String pdf_data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dataBaseHelper = new DataBaseHelper(this);

        //Class and object
        final PackageManager pm = getPackageManager();

        //image view
        logo = findViewById(R.id.logo);
        whatsapp = findViewById(R.id.whatsapp);
        call = findViewById(R.id.call);
        delete = findViewById(R.id.delete);
        id = findViewById(R.id.id);
        back = findViewById(R.id.back);

        //Text view
        first_name = findViewById(R.id.fname_result);
        last_name = findViewById(R.id.lname_result);
        gender = findViewById(R.id.gender_result);
        father_name = findViewById(R.id.father_result);
        mobile = findViewById(R.id.mobile_result);
        whatsapp_no = findViewById(R.id.whatsapp_mobile_result);
        parents_mobile = findViewById(R.id.parents_result);
        occupation = findViewById(R.id.occupation_result);
        p_add = findViewById(R.id.p_add_result);
        c_add = findViewById(R.id.current_result);
        pg_name = findViewById(R.id.pg_result);
        room_number = findViewById(R.id.room_result);
        bed_number = findViewById(R.id.bed_result);


        //button
        update = findViewById(R.id.update);
        pdf = findViewById(R.id.pdf);
        alert = findViewById(R.id.alert);

        //setting data
        setdata();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renti_model Renti_model = (renti_model)getIntent().getExtras().getSerializable("renti");
                fname = Renti_model.getFirst_name();
                lname = Renti_model.getLast_name();
                gender1 = Renti_model.getGender();
                father = Renti_model.getFather_name();
                pnone = Renti_model.getMobile();
                w_phone = Renti_model.getWhatsapp();
                f_mobile = Renti_model.getP_mobile();
                occu = Renti_model.getOccupation();
                p_address = Renti_model.getPermanent_address();
                c_address = Renti_model.getCurrent_address();
                pg = Renti_model.getPg_name();
                room = Renti_model.getRoom_no();
                bed = Renti_model.getBed_no();
                id_img = Renti_model.getId_image();

                Intent intent = new Intent(getApplicationContext(),update_data.class);
                intent.putExtra("fname",fname);
                intent.putExtra("lname",lname);
                intent.putExtra("gender",gender1);
                intent.putExtra("father",father);
                intent.putExtra("phone",pnone);
                intent.putExtra("w_phone",w_phone);
                intent.putExtra("f_mobile",f_mobile);
                intent.putExtra("occu",occu);
                intent.putExtra("p_address",p_address);
                intent.putExtra("c_address",c_address);
                intent.putExtra("pg",pg);
                intent.putExtra("room",room);
                intent.putExtra("bed",bed);
                intent.putExtra("id_img",id_img);

                startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fname = first_name.getText().toString();
                final String lname = last_name.getText().toString();
                final String phone = mobile.getText().toString();
                final String w_phone = whatsapp_no.getText().toString();


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
                                    Intent intent = new Intent(getApplicationContext(),renti_list_view.class);
                                    startActivity(intent);
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
            }
        });

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),image_show.class);
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });

        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), renti_list_view.class);
                startActivity(i);
            }
        });

        //whatsapp message
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    String number = "+91 "+ whatsapp_no.getText().toString();
                    Intent what = new  Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+""+number+"?body="+""));
                    what.setPackage("com.whatsapp");
                    startActivity(what);
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Calling to rentee
        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + mobile.getText().toString()));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(getApplicationContext(),"Please provide Calling Permission",Toast.LENGTH_LONG).show();
                    ActivityCompat.requestPermissions(show_details.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please provide Calling Permission",Toast.LENGTH_LONG).show();
                }
                startActivity(i);
            }
        });

        //send rent alert to rentee
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = "Hi, "+first_name.getText().toString()+"\n You have not sent Room rent of this month." +
                            "\n you can also send me through Paytm and UPI :)";
                    String toNumber = "+91 "+ whatsapp_no.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    public void setdata()
    {
        renti_model Renti_model = (renti_model)getIntent().getExtras().getSerializable("renti");
        first_name.setText(Renti_model.getFirst_name());
        last_name.setText(Renti_model.getLast_name());
        gender.setText(Renti_model.getGender());
        father_name.setText(Renti_model.getFather_name());
        mobile.setText(Renti_model.getMobile());
        whatsapp_no.setText(Renti_model.getWhatsapp());
        parents_mobile.setText(Renti_model.getP_mobile());
        occupation.setText(Renti_model.getOccupation());
        p_add.setText(Renti_model.getPermanent_address());
        c_add.setText(Renti_model.getCurrent_address());
        pg_name.setText(Renti_model.getPg_name());
        room_number.setText(Renti_model.getRoom_no());
        bed_number.setText(Renti_model.getBed_no());
        image_len = Renti_model.getId_image().length;
        image = Renti_model.getId_image();
        id1 = BitmapFactory.decodeByteArray(Renti_model.getId_image(),0,Renti_model.getId_image().length);
        id.setImageBitmap(id1);
    }
}
