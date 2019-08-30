package com.rohit.pg.activity;

import android.Manifest;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.rohit.pg.R;
import com.rohit.pg.model.renti_model;

public class show_details extends AppCompatActivity {

    ImageView logo, whatsapp, call, delete, id, back;
    TextView first_name, last_name, gender, father_name, mobile,whatsapp_no, parents_mobile, occupation, p_add, c_add, pg_name, room_number, bed_number;
    Button update, pdf, alert;
    private static final int REQUEST_PHONE_CALL = 1;
    Bitmap id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

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

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),image_show.class);
                intent.putExtra("image",id1);
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
        id1 = BitmapFactory.decodeByteArray(Renti_model.getId_image(),0,Renti_model.getId_image().length);
        id.setImageBitmap(id1);
    }

}
