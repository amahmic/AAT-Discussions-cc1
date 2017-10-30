package com.example.azram.aatdiscussionscc1;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SecondActivityResult extends RealmObject {

    public static final int INVALID_ID = -1;

    @PrimaryKey
    private int id;
    private String message;
    private boolean valid;

    public SecondActivityResult() {
        this.id = INVALID_ID;
        this.message = null;
        this.valid = false;
    }

    public SecondActivityResult(int id, String message, boolean valid) {
        this.id = id;
        this.message = message;
        this.valid = valid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
