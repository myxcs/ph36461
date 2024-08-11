package com.ph36461.thi_thu_1.list;

public class Student {

    //id ,name, age, mssv
    private int id;
    private String name;
    private int age;
    private String mssv;

    public Student(int id, String name, int age, String mssv) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mssv = mssv;
    }
    public Student(String name, int age, String mssv) {
        this.name = name;
        this.age = age;
        this.mssv = mssv;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
}

