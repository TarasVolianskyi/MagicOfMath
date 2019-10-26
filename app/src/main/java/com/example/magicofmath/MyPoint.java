package com.example.magicofmath;

import java.util.List;

public class MyPoint  {

    private int number;
    private int pointXcoordinate;
    private int pointYcoordinate;

    public MyPoint() {
    }

    public MyPoint(int number, int pointXcoordinate, int pointYcoordinate) {
        this.number = number;
        this.pointXcoordinate = pointXcoordinate;
        this.pointYcoordinate = pointYcoordinate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPointXcoordinate() {
        return pointXcoordinate;
    }

    public void setPointXcoordinate(int pointXcoordinate) {
        this.pointXcoordinate = pointXcoordinate;
    }

    public int getPointYcoordinate() {
        return pointYcoordinate;
    }

    public void setPointYcoordinate(int pointYcoordinate) {
        this.pointYcoordinate = pointYcoordinate;
    }
}
