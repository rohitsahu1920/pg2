package com.rohit.pg.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rohit.pg.model.renti_model;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "PgManagement2.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create table user(UserName text primary key,email text, password text)");

        sqLiteDatabase.execSQL("Create table rentee(first_name text, last_name text,gender text,father_name text," +
                "mobile text,whatsapp_mobile text,p_mobile text,occupation text,permanent_address text,working_address text," +
                " pg_number text,room_number text,bed_number text,id BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
        sqLiteDatabase.execSQL("drop table if exists rentee");
    }

    public boolean insert(String UserName, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",UserName);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean chkuser(String UserName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where UserName = ?",new String[]{UserName});

        if(cursor.getCount() > 0){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?",new String[]{email});

        if(cursor.getCount() > 0){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean emailpass(String user, String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where UserName = ? and password = ? ", new String[]{user,pass});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean delete(String fname,String lname,String phone,String w_phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long delete = db.delete("rentee", "first_name = ? and last_name = ? and mobile = ? and whatsapp_mobile = ?",new String[]{fname,lname,phone,w_phone});
        if(delete == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insert_rentee(String firt_name, String last_name, String gender, String father_name, String mobile,String whatsapp_mobile, String p_mobile, String ocupation,
                                 String permanent_address, String working_address, String pg_number, String room_number, String bed_number,
                                 byte[] id_image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name",firt_name);
        contentValues.put("last_name",last_name);
        contentValues.put("gender",gender);
        contentValues.put("father_name",father_name);
        contentValues.put("mobile",mobile);
        contentValues.put("whatsapp_mobile",whatsapp_mobile);
        contentValues.put("p_mobile",p_mobile);
        contentValues.put("occupation",ocupation);
        contentValues.put("permanent_address",permanent_address);
        contentValues.put("working_address",working_address);
        contentValues.put("pg_number",pg_number);
        contentValues.put("room_number",room_number);
        contentValues.put("bed_number",bed_number);
        contentValues.put("id",id_image);

        long ins = db.insert("rentee",null,contentValues);
        if(ins == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public List<renti_model> getDetails()
    {
        List<renti_model> renti_model_List = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from rentee",null);
        if(cursor.moveToFirst())
        {
           do
           {
               String first_name = cursor.getString(0);
               String last_name = cursor.getString(1);
               String gender = cursor.getString(2);
               String father_name = cursor.getString(3);
               String mobile = cursor.getString(4);
               String whatsapp_mobile = cursor.getString(5);
               String p_mobile = cursor.getString(6);
               String occupation = cursor.getString(7);
               String permanent_add = cursor.getString(8);
               String working_add = cursor.getString(9);
               String pg_num = cursor.getString(10);
               String room_num = cursor.getString(11);
               String bed_num = cursor.getString(12);
               byte[] id_image = cursor.getBlob(13);

               renti_model renti_model = new renti_model(first_name,last_name,gender,father_name,mobile,whatsapp_mobile,p_mobile,
                       occupation,permanent_add,working_add,pg_num,room_num,bed_num,id_image);
               renti_model_List.add(renti_model);
           }
           while (cursor.moveToNext());
        }
        return renti_model_List;
    }

}
