package com.company;

public class Good {
    private long id;
    private String name;
    private double cost;

    public Good(long id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        if (id != good.id) return false;
        if (cost != good.cost) return false;
        return name != null ? name.equals(good.name) : good.name == null;
    }

    @Override
    public int hashCode (){
        int result = (int) id;
        result = 31*result + (name!=null ? name.hashCode():0);
        result = 31*result + (int) cost;
        return result;
    }
}
