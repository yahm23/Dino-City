package com.triceratops.dinocityserver.models.enums;

public enum SizeType {
    LARGE(10,3000.00),
    MEDIUM(15,6000.00),
    SMALL(25,10000.00);

    private int size;
    private double price;

    SizeType(int size, double price) {
        this.size = size;
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
