package com.rohit.pg.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "PgManagement1.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create table user(UserName text primary key,email text, password text)");

        sqLiteDatabase.execSQL("Create table rentee(first_name text, last_name text,gender text,father_name text," +
                "mobile text,p_mobile text,occupation text,permanent_address text,working_address text," +
                " pg_number text,room_number text,bed_number text,id BLOB,profile BLOB)");

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

    public boolean insert_rentee(String firt_name, String last_name, String gender, String father_name, String mobile, String p_mobile, String ocupation,
                                 String permanent_address, String working_address, String pg_number, String room_number, String bed_number,
                                 byte[] id_image, byte[] profile_image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name",firt_name);
        contentValues.put("last_name",last_name);
        contentValues.put("gender",gender);
        contentValues.put("father_name",father_name);
        contentValues.put("mobile",mobile);
        contentValues.put("p_mobile",p_mobile);
        contentValues.put("occupation",ocupation);
        contentValues.put("permanent_address",permanent_address);
        contentValues.put("working_address",working_address);
        contentValues.put("pg_number",pg_number);
        contentValues.put("room_number",room_number);
        contentValues.put("bed_number",bed_number);
        contentValues.put("id",id_image);
        contentValues.put("profile",profile_image);

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


    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}
