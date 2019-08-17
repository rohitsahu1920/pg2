package com.rohit.pg.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.rohit.pg.R;
import com.rohit.pg.sql.DataBaseHelper;

import java.io.ByteArrayOutputStream;

import static com.rohit.pg.R.drawable.ic_id_card;
import static com.rohit.pg.R.drawable.ic_man;

public class rentee_registration extends AppCompatActivity {

    EditText fname,lname,father_name,mobile,parents_mobile,occupation,permanent_address,working_address,pg_name,room_name,bed_number;
    Button save_button,clear_button,id_button,profile_button;
    RadioButton male,female;
    RadioGroup gender;
    ImageView id_proof,profile;
    private static int RESULT_LOAD_IMAGE_ID = 1;
    private static int RESULT_LOAD_IMAGE_PROFILE = 2;
    private static final int PERMISSION_REQUEST_CODE = 1;

    DataBaseHelper dataBaseHelper;

    byte[] id_image,profile_image;
    String picpath_id,picpath_profile;


    String renti_fname,renti_lname,renti_gender = "male",renti_father_name, renti_ocuupation,renti_permanent_address,renti_work_address,renti_pg_name,renti_room_name,renti_bed_number;
    String renti_mobile,renti_parents_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentee_registration);

        dataBaseHelper = new DataBaseHelper(this);

        //edit Text
        fname = findViewById(R.id.renti_fname);
        lname = findViewById(R.id.renti_lastname);
        father_name = findViewById(R.id.renti_father_name);
        mobile = findViewById(R.id.renti_mobile_number);
        parents_mobile = findViewById(R.id.renti_parents_mobile);
        occupation = findViewById(R.id.renti_occupation);
        permanent_address = findViewById(R.id.renti_permanet_address);
        working_address = findViewById(R.id.renti_office_address);
        pg_name = findViewById(R.id.renti_pg_name);
        room_name = findViewById(R.id.renti_room_number);
        bed_number = findViewById(R.id.renti_bed_number);

        //buttons
        save_button = findViewById(R.id.renti_submit);
        clear_button = findViewById(R.id.renti_clear);
        id_button = findViewById(R.id.renti_id_proof_button);
        profile_button = findViewById(R.id.renti_profile_button);

        //checkBoxes
        male = findViewById(R.id.renti_male_gender);
        female = findViewById(R.id.renti_fmale_gender);
        gender = findViewById(R.id.renti_gender);

        //image_view
        id_proof = findViewById(R.id.renti_id_proof);
        profile = findViewById(R.id.renti_profile);



        clear_function();
        //taking permission and image button funtinality
       id_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (checkPermission())
               {
                   Intent id_button = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                   startActivityForResult(id_button,RESULT_LOAD_IMAGE_ID);
               }
               else
               {
                   requestPermission();
               }
           }
       });


       profile_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (checkPermission())
               {
                   Intent id_button = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                   startActivityForResult(id_button,RESULT_LOAD_IMAGE_PROFILE);
               }
               else
               {
                   requestPermission();
               }
           }
       });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.renti_male_gender:
                        renti_gender = "male";
                        break;
                    case R.id.renti_fname:
                        renti_gender = "female";
                        break;
                }
            }
        });


       //Insert button
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                renti_fname = fname.getText().toString().trim();
                renti_lname = lname.getText().toString().trim();
                renti_father_name = father_name.getText().toString().trim();
                renti_mobile = mobile.getText().toString();
                renti_parents_mobile = parents_mobile.getText().toString();
                renti_ocuupation = occupation.getText().toString().trim();
                renti_permanent_address = permanent_address.getText().toString().trim();
                renti_work_address = working_address.toString().trim();
                renti_pg_name = pg_name.getText().toString().trim();
                renti_bed_number = bed_number.getText().toString().trim();
                renti_room_name = room_name.getText().toString().trim();
                id_image =  imageViewToByte(id_proof);
                profile_image = imageViewToByte(profile);

                boolean chk = dataBaseHelper.insert_rentee(renti_fname,renti_lname,renti_gender,renti_father_name,renti_mobile,renti_parents_mobile,renti_ocuupation, renti_permanent_address,renti_work_address,renti_pg_name,renti_room_name, renti_bed_number,id_image,profile_image);
                if(chk)
                {
                    Toast.makeText(getApplicationContext(),"Hurry Data Saved.....:)",Toast.LENGTH_LONG).show();
                    clear_data();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Problem :)",Toast.LENGTH_LONG).show();
                }




                /*try
                {



                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Thire is error in Saving data "+ e,Toast.LENGTH_LONG).show();
                }*/
            }
        });

    }

    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    public void clear_function()
    {
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clear_data();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE_ID && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            String[] path = {MediaStore.Images.Media.DATA};
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,path,null,null,null);
            assert cursor != null;
            cursor.moveToFirst();
            int columeIndex = cursor.getColumnIndex(path[0]);
            picpath_id = cursor.getString(columeIndex);
            cursor.close();
            id_proof.setImageBitmap(BitmapFactory.decodeFile(picpath_id));
        }
        if(requestCode == RESULT_LOAD_IMAGE_PROFILE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            String[] path = {MediaStore.Images.Media.DATA};
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,path,null,null,null);
            assert cursor != null;
            cursor.moveToFirst();
            int columeIndex = cursor.getColumnIndex(path[0]);
            picpath_profile = cursor.getString(columeIndex);
            cursor.close();
            profile.setImageBitmap(BitmapFactory.decodeFile(picpath_profile));
        }
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(rentee_registration.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(rentee_registration.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(rentee_registration.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(rentee_registration.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(rentee_registration.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    public void clear_data()
    {
        fname.setText("");
        lname.setText("");
        father_name.setText("");
        mobile.setText("");
        parents_mobile.setText("");
        occupation.setText("");
        permanent_address.setText("");
        working_address.setText("");
        pg_name.setText("");
        room_name.setText("");
        bed_number.setText("");

        id_proof.setImageResource(ic_id_card);
        profile.setImageResource(ic_man);
    }

}
