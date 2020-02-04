package com.triceratops.dinocityserver.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BuildingType {
    TOILETS("TOILETS",1000, 0.01, 20),
    MERCHANDISE_SHOP("MERCHANDISING SHOP",3000, 0.10, 200),
    BAR("BAR",1000, 0.05, 100),
    RESTAURANT("RESTAURANT",2000, 0.8, 150);

    private String name;
    private double price;
    private double reputationMultiplier;
    private double cost;

    BuildingType(String name, double price, double reputationMultiplier, double cost) {
        this.name = name;
        this.price = price;
        this.reputationMultiplier = reputationMultiplier;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getReputationMultiplier() {
        return reputationMultiplier;
    }

    public void setReputationMultiplier(double reputationMultiplier) {
        this.reputationMultiplier = reputationMultiplier;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
