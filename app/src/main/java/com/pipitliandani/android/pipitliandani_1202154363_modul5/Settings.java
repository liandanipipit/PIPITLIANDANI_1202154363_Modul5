package com.pipitliandani.android.pipitliandani_1202154363_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    TextView shapeColor;        //mendeklarasikan variabel TextView
    int colorId;                //mendeklarasikan id color
    AlertDialog.Builder alert;  //mendeklarasikan AlertDialog.Builder
    SharedPreferences.Editor shape; //mendeklarasikan SharedPreferences.Editor shape

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings"); //mengatur judul halaman

        alert = new AlertDialog.Builder(this); //membuat objek AlertDialog.Builder
        //mendeklarasikan objek SharedPreferences
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);                                                                                                           //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        //sharedPreferences.edit()
        shape = sharedPreferences.edit();
        //mendapatkan nilai int ColorGround
        colorId = sharedPreferences.getInt("ColorGround", R.color.white);
        //mencari TextView dengan id shapeColor
        shapeColor = (TextView)findViewById(R.id.shapecolor);
        //mengatur textView dengan nama berdasarkan id
        shapeColor.setText(getShapeColor(colorId));
    }

//method untuk mendefinisikan perilaku ketikan tombol back diklik
    @Override
    public void onBackPressed() {
        //intent baru dari pengaturan menuju list to do
        Intent intent = new Intent(Settings.this, MainActivity.class);
        //memulai intent
        startActivity(intent);
        //menutup activity setelah intent di jalanlan
        finish();
    }

    //method yang dijalankan ketika pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){ //jika item.getItemId()==android.R.id.home
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        //mengembalikan nilai true
        return true;
    }
    //method untuk memberi nama color berdasarkan id
    public String getShapeColor(int i){
        if (i==R.color.red){        //jika R.color.red
            return "Red";           //maka akan mengembalikan nilai string red dan seterusnya
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else {
            return "Default";
        }

    }
    //method untuk mengatur id warna
    public int getColorId (int i){
        if (i==R.color.red){                //jika R.color.red
            return R.id.red;                //maka akan mengembalikan id red
        } else if (i==R.color.green){       //jika R.color.green
            return R.id.green;              //maka akan mengembalikan id green
        } else if (i==R.color.blue){        //jika R.color.blue
            return R.id.blue;               //maka akan mengembalikan id blue
        }else {                             //jika R.color.white
            return R.id.white;              //maka akan mengembalikan id white
        }
    }

    //method untuk memilih warna
    public void chooseColor(View view){
        alert.setTitle("Choose Background Color");      //membuat judul pada alert
        //mendeklarasikan objek view yang mengambil layout settings_color
        View v = getLayoutInflater().inflate(R.layout.settings_color, null);
        //mengatur setView
        alert.setView(v);

        //mencari RadioGroup dengan id radiogroup
        final RadioGroup radioGroup = v.findViewById(R.id.radiogroup);
        //menandai ketika salah satu radio button dipilih
        radioGroup.check(getColorId(colorId));

        //membuat setPositiveButton pada alert
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //menandai radio button yang di klik kedalam int
                int cek = radioGroup.getCheckedRadioButtonId();
                switch (cek){
                    case R.id.red:                  //jika id red
                        colorId = R.color.red;      //maka color id diset ke R.color.red
                        break;
                    case R.id.green:                //jika id green
                        colorId = R.color.green;    //maka color id diset ke R.color.green
                        break;
                    case R.id.blue:                 //jika id blue
                        colorId = R.color.blue;     //maka color id diset ke R.color.blue
                        break;
                    case R.id.white:                //jika id white
                        colorId = R.id.white;       //maka color id diset ke R.color.white
                        break;
                }
                //mengatur teks pada shapecolor
                shapeColor.setText(getShapeColor(colorId));
                //mengatur warna yang dipilih
                shape.putInt("ColorGround", colorId);
                //melakukan commit
                shape.commit();
            }
        });
        //membuat setNegativeButton pada alert
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //menghilangkan dialog alert
            }
        });
        alert.create().show();  //menampilkan alert
    }

}
