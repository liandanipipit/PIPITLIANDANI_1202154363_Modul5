package com.pipitliandani.android.pipitliandani_1202154363_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertNote extends AppCompatActivity {
    EditText todo, desc, prior;  //mendeklarasikan variabel to do, desc, prior
    DatabaseHelper databaseHelper;  //mendeklarasikan DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        setTitle("Add Notes");  //mengatur judul halaman
        todo = (EditText) findViewById(R.id.todo);  //mencari EditText dengan id todo
        desc = (EditText) findViewById(R.id.desc);  //mencari EditText dengan id desc
        prior = (EditText) findViewById(R.id.prior);    //mencari EditText dengan id prior
        databaseHelper = new DatabaseHelper(this);  //membuat objek databaseHelper

    }

    //method untuk mendefinisikan perilaku ketika tombol back diklik
    @Override
    public void onBackPressed() {
        //membuat intent yang akan berpindah ke MainActivity.class
        Intent intent = new Intent(InsertNote.this, MainActivity.class);
        //memulai intent
        startActivity(intent);
        //mengakhiri intent
        this.finish();
    }
//method untuk mendefinisikan perilaku ketika note berhasil ditambahkan
    public void add(View view) {
        //jika input data berhasil
        if (databaseHelper.inputData(new Notes(todo.getText().toString(), desc.getText().toString(), prior.getText().toString()))) {                                                                                                                        //databaseHelper.inputData(new Notes(todo.getText().toString(), desc.getText().toString(), prior.getText().toString()))
            //maka akan menapilkan toast Notes Successfully Added
            Toast.makeText(InsertNote.this, "Notes Succesfully Added", Toast.LENGTH_LONG).show();
            //Membuat intent
            Intent i = new Intent(InsertNote.this, MainActivity.class);
            //memulai intent
            startActivity(i);
            //mengakhiri intent
            this.finish();
        } else {    //jika inout data tidak berhasil
            //maka akan menampilkan toast Cannot Add Notes
            Toast.makeText(InsertNote.this, "Cannot Add Notes", Toast.LENGTH_LONG).show();
            //membuat field kosong
            todo.setText(null);
            desc.setText(null);
            prior.setText(null);
        }

    }
}
