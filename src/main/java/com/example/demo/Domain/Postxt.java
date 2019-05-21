package com.example.demo.Domain;

import javax.persistence.Entity;

public class Postxt {
    private int minx;
    private int miny;
    private int width;
    private int height;
    private int bordersize;

    public Postxt(){};
    public Postxt(int x,int y,int w,int h,int b){
        this.minx=x;
        this.miny=y;
        this.height=h;
        this.width=w;
        this.bordersize=b;
    };

    public int getBordersize() {
        return bordersize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinx() {
        return minx;
    }

    public int getMiny() {
        return miny;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMinx(int minx) {
        this.minx = minx;
    }

    public void setMiny(int miny) {
        this.miny = miny;
    }


    public void setBordersize(int bordersize) {
        this.bordersize = bordersize;
    }
}
