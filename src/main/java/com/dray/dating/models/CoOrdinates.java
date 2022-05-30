package com.dray.dating.models;

public class CoOrdinates {
    int xCoOrdinate;
    int yCoOrdinate;

    public CoOrdinates() {
    }

    public CoOrdinates(int xCoOrdinate, int yCoOrdinate) {
        this.xCoOrdinate = xCoOrdinate;
        this.yCoOrdinate = yCoOrdinate;
    }

    public int getxCoOrdinate() {
        return xCoOrdinate;
    }

    public void setxCoOrdinate(int xCoOrdinate) {
        this.xCoOrdinate = xCoOrdinate;
    }

    public CoOrdinates withxCoOrdinate(int xCoOrdinate) {
        this.xCoOrdinate = xCoOrdinate;
        return this;
    }

    public int getyCoOrdinate() {
        return yCoOrdinate;
    }

    public CoOrdinates withyCoOrdinate(int yCoOrdinate) {
        this.yCoOrdinate = yCoOrdinate;
        return this;
    }

    public void setyCoOrdinate(int yCoOrdinate) {
        this.yCoOrdinate = yCoOrdinate;
    }


}
