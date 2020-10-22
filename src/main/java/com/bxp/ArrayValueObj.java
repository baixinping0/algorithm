package com.bxp;


public class ArrayValueObj{
    private Integer index;
    private Integer value;

    ArrayValueObj(Integer index, Integer value){
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}