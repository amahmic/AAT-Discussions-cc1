package com.example.azram.aatdiscussionscc1.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("root")
    @Expose
    private Root root;

    public Example(Root root) {
        this.root = root;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

}