package com.example.azram.aatdiscussionscc1.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMePleaseWrapper {

    @SerializedName("obj")
    @Expose
    private Obj obj;

    public GetMePleaseWrapper(Obj obj) {
        this.obj = obj;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }
}