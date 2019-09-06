package com.rohit.pg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rohit.pg.R;
import com.rohit.pg.sql.DataBaseHelper;

public class registration extends AppCompatActivity {

    Button login, registration;
    EditText edit_user,edit_pass,edit_cpass,edit_email;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = findViewById(R.id.login);
        registration = findViewById(R.id.registration);
        edit_user = findViewById(R.id.username);
        edit_email = findViewById(R.id.email);
        edit_pass = findViewById(R.id.password);
        edit_cpass = findViewById(R.id.cpassword);
        db = new DataBaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.rohit.pg.activity.registration.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {

            byte[] bitmap = (byte[]) b.get("image");
            //image = BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);
            //imageView.setImageBitmap(BitmapFactory.decodeByteArray(bitmap,0,bitmap.length));
        }


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edit_user.getText().toString().toLowerCase();
                String email = edit_email.getText().toString().toLowerCase();
                String pass = edit_pass.getText().toString();
                String cpass = edit_cpass.getText().toString();

                if((user.isEmpty()) || (email.isEmpty()) || (pass.isEmpty()) || (cpass.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(cpass))
                    {
                        Boolean chkmail = db.chkemail(email);
                        if(chkmail == true)
                        {
                            Boolean insert = db.insert(user,email,pass);
                            if(insert == true)
                            {
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(registration.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"There is problem in Registered",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email is already Registered",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password is Not matched",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
