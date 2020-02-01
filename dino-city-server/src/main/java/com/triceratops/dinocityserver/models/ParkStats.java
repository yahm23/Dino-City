package com.triceratops.dinocityserver.models;

public class ParkStats {

    private double money;
    private double income;
    private int population;

    public ParkStats(double money, double income, int population) {
        this.money = money;
        this.income = income;
        this.population = population;
    }

    public double getMoney() {
        return money;
    }

    public double getIncome() {
        return income;
    }

    public int getPopulation() {
        return population;
    }
}
