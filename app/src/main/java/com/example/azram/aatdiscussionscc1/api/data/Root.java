package com.example.azram.aatdiscussionscc1.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root {

    @SerializedName("non_root")
    @Expose
    private String nonRoot;

    public Root(String nonRoot) {
        this.nonRoot = nonRoot;
    }

    public String getNonRoot() {
        return nonRoot;
    }

    public void setNonRoot(String nonRoot) {
        this.nonRoot = nonRoot;
    }

}