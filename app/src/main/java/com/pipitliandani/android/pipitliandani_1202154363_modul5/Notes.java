package com.pipitliandani.android.pipitliandani_1202154363_modul5;

/**
 * Created by User on 25/03/2018.
 */

//membuat class Notes
public class Notes {
    String todo, desc, prior;       //mendefiniikan variabel to do, desc, prior

//membuat constructor
    public Notes(String todo, String desc, String prior) {
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }

//membuat method setter getter
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}

