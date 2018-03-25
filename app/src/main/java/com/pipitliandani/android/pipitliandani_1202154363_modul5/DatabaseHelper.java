package com.pipitliandani.android.pipitliandani_1202154363_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by User on 25/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context; //mendeklarasikan variabel context
    SQLiteDatabase database; //mendeklarasikan variabel SQLiteDatabase

    public static final String DB_NAME = "mobile.db";        //menginisiasi nilai DB_NAME

    public static final String TABLE_NAME = "Notes";         //menginisiasi nilai TABLE_NAME
    public static final String COL_TODO = "TODO";            //menginisiasi nilai COL_TODO
    public static final String COL_DESC = "DESCRIPTION";     //menginisiasi nilai COL_DESC
    public static final String COL_PRIOR = "PRIORITY";       //menginisiasi nilai COL_PRIOR


//Membuat Constructor dengan variabel Cotext
    public DatabaseHelper(Context context) {
        //membuat database dengan variabel ccontext, DB_NAME, factory: null, version:1
        super(context, DB_NAME, null, 1);
        this.context = context;     //this.context = context
        database = this.getWritableDatabase(); //memanggil method getWritableDatabase yang dimasukkan ke variabel database
        //mengeksekusi sql query untuk membuat tabel didalam database
        database.execSQL("create table if not exists " + TABLE_NAME + "(TODO varchar(50) primary key, DESCRIPTION varchar(50), PRIORITY varchar(3))");


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //membuat tabel jika tabel tersebut belum ada
        db.execSQL("create table if not exists "+ TABLE_NAME+"(TODO varchar(50) primary key, DESCRIPTION varchar(50), PRIORITY varchar(3))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //menghapus tabel lama untuk digantikan dengan tabel baru atau mereplace tabel
        db.execSQL("drop table if exists" + TABLE_NAME);
        //membuat tabel baru
        onCreate(db);
    }


//method untuk menjalankan penginputan data
    public boolean inputData(Notes lists){
        ContentValues values = new ContentValues(); //membuat objek ContentValues
        values.put(COL_TODO, lists.getTodo());  //menyisipkan data ke dalam kolom to do dari objek Notes
        values.put(COL_DESC, lists.getDesc());  //menyisipkan data ke dalam kolom desc Notes
        values.put(COL_PRIOR, lists.getPrior());    //menyisipkan data ke dalam kolom prior Notes
        //membuat variabel result bertipe long untuk melakukan penginputan data
        long result = database.insert(TABLE_NAME, null, values);
            if (result == -1){  //jika result sama dengan -1
                return false;   //mengembalikan nilai false
            } else {            //jika result tidak sama dengan -1
                return true;    //mengembalika nilai true
            }

    }
    //method untuk menjalankan penghapusan data
    public boolean removeData(String rem){
        //mengembalikan nilai database.delete                                                                                                                                                                           database.delete(TABLE_NAME, COL_TODO + "=\"" + rem + "\"", null) > 0;
        return database.delete(TABLE_NAME, COL_TODO + "=\"" + rem + "\"", null) > 0;
    }
    //method untuk menjalankan pembacaan data
    public void readData(ArrayList<Notes> data){ //menginisiasi ArrayList<Notes>
        //membuat objek cursor, didalamnya ada pemanggilan method getReadableDatabase().rawQuery(...SQL Query)
        Cursor cursor= this.getReadableDatabase().rawQuery("select TODO, DESCRIPTION, PRIORITY from " + TABLE_NAME, null );

        while (cursor.moveToNext()){ //while (cursor.moveToNext())
            //mengambil nilai string untuk dimasukkan kedalam class Notes dan menyesuaikan indexnya
            data.add(new Notes(cursor.getString(0), cursor.getString(1), cursor.getString(2)));

        }
    }
}
