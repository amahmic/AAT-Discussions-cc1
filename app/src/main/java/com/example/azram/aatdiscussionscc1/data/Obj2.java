package com.example.azram.aatdiscussionscc1.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Obj2 {

    @SerializedName("obj3")
    @Expose
    private String obj3;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private double age;

    public Obj2(String obj3, String name, double age) {
        this.obj3 = obj3;
        this.name = name;
        this.age = age;
    }

    public String getObj3() {
        return obj3;
    }

    public void setObj3(String obj3) {
        this.obj3 = obj3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

}