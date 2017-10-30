package com.example.azram.aatdiscussionscc1.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjAnswer {

    @SerializedName("obj2")
    @Expose
    private Obj2 obj2;

    public ObjAnswer(Obj2 obj2) {
        this.obj2 = obj2;
    }

    public Obj2 getObj2() {
        return obj2;
    }

    public void setObj2(Obj2 obj2) {
        this.obj2 = obj2;
    }
}
