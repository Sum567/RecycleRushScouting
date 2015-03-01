package com.example.sumeet.test;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Sumeet on 2/10/2015.
 */
public class Stack{
    private int top;
    private int bottom;
    private boolean hasBin;
    private boolean hasNoodle;
    private boolean fromFeeder;
    private boolean fromLandFill;

    public Stack(int top, int bottom, boolean hasBin, boolean hasNoodle, boolean fromFeeder, boolean fromLandFill){
        this.top = top;
        this.bottom = bottom;
        this.hasBin = hasBin;
        this.hasNoodle = hasNoodle;
        this.fromFeeder = fromFeeder;
        this.fromLandFill = fromLandFill;
    }

    public int getTop(){
        return top;
    }
    public int getBottom(){
        return bottom;
    }
    public boolean getHasBin(){
        return hasBin;
    }
    public boolean getHasNoodle(){
        return hasNoodle;
    }
    public boolean getFromLandFill(){return fromLandFill;}
    public boolean getfromFeeder(){return fromFeeder;}
}
