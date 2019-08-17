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

public class MainActivity extends AppCompatActivity{

    Button login,registratiin;
    EditText text_user, text_pass;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        registratiin = findViewById(R.id.registration);
        db = new DataBaseHelper(this);
        text_user = findViewById(R.id.username);
        text_pass = findViewById(R.id.password);

        registratiin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = text_user.getText().toString().toLowerCase();
                String pass = text_pass.getText().toString();

                if(username.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean chk = db.emailpass(username,pass);
                    if(chk == true)
                    {
                        Toast.makeText(getApplicationContext(),"Login Successfuly",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,rentee_registration.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No such Details are found please register your self",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }


}
