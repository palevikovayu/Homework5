package com.company;

import java.util.HashMap;
import java.util.Map;

public class PayCheck {
    private Map<Good, Integer> check = new HashMap<>();
    private double totalPrice = 0;

    public Map<Good, Integer> getCheck() {
        return check;
    }

    public  void createCheck(){
        for (Map.Entry<Good, Integer> map: check.entrySet()){
            System.out.printf("%s - %d un.%n", map.getKey().toString(), map.getValue());
        }
        System.out.printf("-------------------------%nTotal price: %f %n", totalPrice);
    }

    public void addGoodToCheck(Good good) {

            if (!check.containsKey(good)) {
                check.put(good, 1);
                totalPrice += good.getCost();
            } else {
                check.replace(good, check.get(good) + 1);
                totalPrice += good.getCost();
            }

    }

}
