package com.example.azram.aatdiscussionscc1.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostRootWrapper {

    @SerializedName("root")
    @Expose
    private Root root;

    public PostRootWrapper(Root root) {
        this.root = root;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

}