package com.triceratops.dinocityserver.models;

public class ParkStats {

    private double money;
    private double income;
    private int population;
    private double cost;
    private double rating;

    public ParkStats(double money, double income, int population, double cost, double rating) {
        this.money = money;
        this.income = income;
        this.population = population;
        this.cost =cost;
        this.rating=cost;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getIncome() {
        return income;
    }

    public int getPopulation() {
        return population;
    }
}
