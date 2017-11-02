package com.example.azram.aatdiscussionscc1.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Obj {

    @SerializedName("obj2")
    @Expose
    private List<Obj2> obj2 = null;

    public Obj(List<Obj2> listaObj2) {
        this.obj2 = listaObj2;
    }

    public List<Obj2> getObj2() {
        return obj2;
    }

    public void setObj2(List<Obj2> obj2) {
        this.obj2 = obj2;
    }

}