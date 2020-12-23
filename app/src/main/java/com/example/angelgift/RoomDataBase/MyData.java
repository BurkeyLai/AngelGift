package com.example.angelgift.RoomDataBase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyTable")//這邊要先取好table的名字，稍後的table設置必須與他相同

public class MyData {
    @PrimaryKey(autoGenerate = true)//設置是否使ID自動累加
    private int id;
    private String date;
    private int class_id;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;

    public MyData(String date, int class_id, int number1, int number2,
                  int number3, int number4, int number5) {
        this.date = date;
        this.class_id = class_id;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
    }
    /*
    @Ignore//如果要使用多形的建構子，必須加入@Ignore
    public MyData(int daily_id, String date, int class_id, int number1, int number2,
                  int number3, int number4, int number5) {
        this.daily_id = daily_id;
        this.date = date;
        this.class_id = class_id;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumber4() {
        return number4;
    }

    public void setNumber4(int number4) {
        this.number4 = number4;
    }

    public int getNumber5() {
        return number5;
    }

    public void setNumber5(int number5) {
        this.number5 = number5;
    }
}
