package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SizeType {
    LARGE("LARGE",25,3000.00),
    MEDIUM("MEDIUM",15,6000.00),
    SMALL("SMALL",10,10000.00);

    private int size;
    private double price;

    private String name;


    SizeType(String name,int size, double price) {
        this.size = size;
        this.price = price;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
