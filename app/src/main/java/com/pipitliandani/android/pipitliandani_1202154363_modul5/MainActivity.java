package com.pipitliandani.android.pipitliandani_1202154363_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;  //mendeklarasikan DatabaseHelper
    RecyclerView rView;             //mendeklarasikan RecyclerVier
    RecyclerAdapter adapter;        //mendeklarasikan RecyclerAdapter
    ArrayList<Notes> notes;         //mendeklarasikan ArrayList<Notes>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("List To do");     //Mengatur judul halaman
        rView = (RecyclerView)findViewById(R.id.rView);     //mencari Recyclerview dengan id rView
        notes = new ArrayList<>();                          //membuat objek ArrayList<>()
        databaseHelper = new DatabaseHelper(this);  //membuat objek Database Helper(this)
        databaseHelper.readData(notes);                     //memanggil method readData dari Database Helper

        //membuat objek SharedPreferences
        SharedPreferences shared = this.getApplicationContext().getSharedPreferences("Preferences", 0);                                                                                                                         //SharedPreferences shared = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        //mendeklarasikan variabel color bertipe int
        int color = shared.getInt("ColorGround", R.color.white);
        //membuat adapter
        adapter = new RecyclerAdapter(this, notes, color);
        //mendefinisikan FixedSized
        rView.setHasFixedSize(true);
        //mengatur LayoutManager
        rView.setLayoutManager(new LinearLayoutManager(this));
        //mengatur adapter
        rView.setAdapter(adapter);
        //memanggil method SwipeRemoved
        SwipeRemoved();
        }

        //method untuk menjalankan penghapusan data ketika mengusap layar
        public void SwipeRemoved(){
        //membuat objek ItemTouchHelper.SimpleCallback dengan dragDirs 0 dan touch ke kanan
        ItemTouchHelper.SimpleCallback touch = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            //method untuk mendefinikan action swiped untuk penghapusan data
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();         //membuat variabel int dari viewHolder.getAdapterPosition
                Notes current = adapter.getData(position);              //mendapatkan data posisi bertipe class Notes

                if (direction == ItemTouchHelper.RIGHT){                //jika arah usap layar ke kanan
                    if (databaseHelper.removeData(current.getTodo())){  //dan jika database menghapus data
                        adapter.deleteData(position);                   //maka adapter akan menghapus data berdasarkan posisi
                        //membuat notifikasi snackbar Note Deleted
                        Snackbar.make(findViewById(R.id.coordinator), "Note Deleted",1000).show();
                    }
                }

            }
        };
        //membuat objek ItemTouchHelper(touch)
        ItemTouchHelper helper = new ItemTouchHelper(touch);
        //mengatach ke RecyclerView
       helper.attachToRecyclerView(rView);



    }
    //method untuk membuat menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            //get menu inflater dari menu.menu
        getMenuInflater().inflate(R.menu.menu, menu);
        //mengembalikan nilai true
        return true;

    }
    //method untuk mendefinisikan perilaku ketika option menu diklik

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();      //membuat variabel id yang menampung item.id
        if (id==R.id.action_settings){  //jika id==R.id.action_settings
            //membuat intent ke kelas Settings
            Intent in = new Intent(MainActivity.this, Settings.class);
            //memulai intent
            startActivity(in);
            //mengakhiri intent
            finish();
        }
        //mengembalikan nlai true
        return true;
    }
    //membuat method untuk mendefinisikan perilaku ketika button add di klik
    public void add(View view){
            //membuat intent ke InsertNote
        Intent intent = new Intent(MainActivity.this, InsertNote.class);
        //memulai intent
        startActivity(intent);

    }
}
