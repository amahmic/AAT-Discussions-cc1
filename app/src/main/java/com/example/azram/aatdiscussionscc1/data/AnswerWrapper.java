package com.example.azram.aatdiscussionscc1.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerWrapper {

    @SerializedName("obj")
    @Expose
    private ObjAnswer obj;

    public AnswerWrapper(ObjAnswer obj) {
        this.obj = obj;
    }

    public ObjAnswer getObj() {
        return obj;
    }

    public void setObj(ObjAnswer obj) {
        this.obj = obj;
    }
}
