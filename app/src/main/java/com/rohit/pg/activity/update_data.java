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

public class update_data extends AppCompatActivity {

    EditText fname,lname,father_name,mobile,parents_mobile,occupation,permanent_address,working_address,pg_name,room_name,bed_number,whatsapp_number1;
    Button update_button,clear_button,id_button;
    RadioButton male,female;
    RadioGroup gender;
    ImageView id_proof,back;
    private static int RESULT_LOAD_IMAGE_ID = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;

    DataBaseHelper dataBaseHelper;

    byte[] id_image;
    String picpath_profile;


    String renti_fname,renti_lname,renti_gender = "male",renti_father_name, renti_ocuupation,renti_permanent_address,renti_work_address,renti_pg_name,renti_room_name,renti_bed_number;
    String renti_mobile,renti_parents_mobile,renti_whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dataBaseHelper = new DataBaseHelper(this);

        //edit Text
        fname = findViewById(R.id.renti_fname);
        lname = findViewById(R.id.renti_lastname);
        father_name = findViewById(R.id.renti_father_name);
        mobile = findViewById(R.id.renti_mobile_number);
        whatsapp_number1 = findViewById(R.id.renti_whatsapp_number);
        parents_mobile = findViewById(R.id.renti_parents_mobile);
        occupation = findViewById(R.id.renti_occupation);
        permanent_address = findViewById(R.id.renti_permanet_address);
        working_address = findViewById(R.id.renti_office_address);
        pg_name = findViewById(R.id.renti_pg_name);
        room_name = findViewById(R.id.renti_room_number);
        bed_number = findViewById(R.id.renti_bed_number);

        //buttons
        update_button = findViewById(R.id.renti_update);
        clear_button = findViewById(R.id.renti_clear);
        id_button = findViewById(R.id.renti_id_button);

        //checkBoxes
        male = findViewById(R.id.renti_male_gender);
        female = findViewById(R.id.renti_fmale_gender);
        gender = findViewById(R.id.renti_gender);

        //image_view
        id_proof = findViewById(R.id.renti_profile);
        back = findViewById(R.id.back);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {

            String fname1 = (String) b.get("fname");
            String lname1 = (String) b.get("lname");
            final String gender1 = (String) b.get("gender");
            String father1 = (String) b.get("father");
            String pnone1 = (String) b.get("phone");
            String w_phone1 = (String) b.get("w_phone");
            String f_mobile1 = (String) b.get("f_mobile");
            String occu1 = (String) b.get("occu");
            String p_address1 = (String) b.get("p_address");
            String c_address1 = (String) b.get("c_address");
            String pg1 = (String) b.get("pg");
            String room1 = (String) b.get("room");
            String bed1 = (String) b.get("bed");
            byte[] id_img = (byte[]) b.get("id_img");

            father_name.setText(father1);
            mobile.setText(pnone1);
            whatsapp_number1.setText(w_phone1);
            parents_mobile.setText(f_mobile1);
            occupation.setText(occu1);
            permanent_address.setText(p_address1);
            working_address.setText(c_address1);
            pg_name.setText(pg1);
            room_name.setText(room1);
            bed_number.setText(bed1);

            id_proof.setImageBitmap(BitmapFactory.decodeByteArray(id_img,0,id_img.length));

            fname.setText(fname1);
            lname.setText(lname1);
            gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(gender1 == "male")
                    {

                    }
                }
            });
        }


        //setting image
        id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission())
                {
                    Intent id_button = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(id_button,RESULT_LOAD_IMAGE_ID);
                }
                else
                {
                    requestPermission();
                }
            }
        });


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                renti_fname = fname.getText().toString().trim();
                renti_lname = lname.getText().toString().trim();
                renti_father_name = father_name.getText().toString().trim();
                renti_mobile = "+91 "+mobile.getText().toString();
                renti_whatsapp = whatsapp_number1.getText().toString();
                renti_parents_mobile = "+91"+parents_mobile.getText().toString();
                renti_ocuupation = occupation.getText().toString().trim();
                renti_permanent_address = permanent_address.getText().toString().trim();
                renti_work_address = working_address.toString().trim();
                renti_pg_name = pg_name.getText().toString().trim();
                renti_bed_number = bed_number.getText().toString().trim();
                renti_room_name = room_name.getText().toString().trim();
                id_image =  imageViewToByte(id_proof);


                boolean chk = dataBaseHelper.update_rentee(renti_fname,renti_lname,renti_gender,renti_father_name,renti_mobile,
                        renti_whatsapp,renti_parents_mobile,renti_ocuupation, renti_permanent_address,renti_work_address,
                        renti_pg_name,renti_room_name, renti_bed_number,id_image);
                if(chk)
                {
                    Toast.makeText(getApplicationContext(),"Hurry Data Updated.....:)",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),renti_list_view.class);
                    startActivity(intent);
                    clear_data();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"There is Problem in Updating data:(",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(update_data.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(update_data.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(update_data.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(update_data.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(update_data.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
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
            picpath_profile = cursor.getString(columeIndex);
            cursor.close();
            id_proof.setImageBitmap(BitmapFactory.decodeFile(picpath_profile));
        }
    }

    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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
    }
}
