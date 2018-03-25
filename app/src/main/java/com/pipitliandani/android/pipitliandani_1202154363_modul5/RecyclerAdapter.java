package com.pipitliandani.android.pipitliandani_1202154363_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by User on 25/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    private Context context;        //mendefinisikan variabel Context
    private List<Notes> list;       //mendefinisikan variabel List<Notes>
    int color;                      //mendefinisikan variabel int color

//membuat constructor
    public RecyclerAdapter(Context context, List<Notes> list, int color) {
        this.context = context;
        this.list = list;
        this.color = color;
    }

//method untuk membuat view holder
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //memmbuat objek View yang mengambil layout to_do_list
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_list, parent, false);                                                                                                               //View view = LayoutInflater.from(context).inflate(R.layout.to_do_list, parent, false);
        //membuat Objek Holder (view)
        Holder holder = new Holder(view);
        //mengembalikan nilai holder
        return holder;
    }

    //methoduntuk membind ViewHolder
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        //membuat objek Notes untuk mengambil nilai position
        Notes data = list.get(position);
        holder.todo.setText(data.getTodo());//mengatur holder.to do dengan data.getTodo
        holder.desc.setText(data.getDesc());//mengatur holder.desc dengan data.getDesc
        holder.number.setText(data.getPrior());//mengatur holder.number dengan data.getPrior
        //mengatur backgroud cardView
        holder.cardView.setCardBackgroundColor(context.getResources().getColor(this.color));

    }
//method untuk mengambil ItemCount
    @Override
    public int getItemCount() {
        return list.size(); //mengembalikan nilai list.size()
    }
    //method untuk mengambil data dari class Notes
    public Notes getData (int position){
        //mengembalikan nilai list.get(position)
        return list.get(position);
    }
    //method untuk melakukan penghapusan data
    public void deleteData(int i){
        //melakukan penghapusan list
        list.remove(i);
        //melakukan notifyItemRemoved
        notifyItemRemoved(i);
        //melakukan notifyItemRangeChanged
        notifyItemRangeChanged(i, list.size());
    }

    //membuat class Holder
    class Holder extends RecyclerView.ViewHolder{
        public TextView todo, desc, number;     //mendeklarasikan variabel to do, desc, number
        public CardView cardView;                //mendeklarasikan variabel to do, desc, number
        //membuat constructor
        public Holder(View itemView){
            super(itemView);
            todo = (TextView)itemView.findViewById(R.id.headline);      //mencari nilai textView dengan id headline
            desc = (TextView)itemView.findViewById(R.id.description);   //mencari nilai textView dengan id description
            number = (TextView)itemView.findViewById(R.id.number);      //mencari nilai textView dengan id number
            cardView = (CardView)itemView.findViewById(R.id.cardView);  //mencari nilai cardview dengan id cardView
        }
    }
}
