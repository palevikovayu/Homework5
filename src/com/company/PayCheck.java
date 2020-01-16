package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayCheck {
    private Map<Good, Integer> check = new HashMap<>();
    private double totalPrice = 0;

    public Map<Good, Integer> getCheck() {
        return check;
    }

    public  void createCheck(){
        for (Map.Entry<Good, Integer> map: check.entrySet()){
            System.out.printf("%s - %d un.", map.getKey().toString(), map.getValue().toString());
        }
        System.out.printf("-------------------------%nTotal price: %f %n", totalPrice);
    }

    public void addGoodToCheck1(Good good) {
        Shop1 shop1 = new Shop1();
        if (!shop1.getList().contains(good)) {

        } else {
            if (!check.containsKey(good)) {
                check.put(good, 1);
                totalPrice += good.getCost();
                shop1.getList().remove(good);
            } else {
                check.replace(good, check.get(good) + 1);
                totalPrice += good.getCost();
                shop1.getList().remove(good);
            }
        }
    }

    public void addGoodToCheck2(Good good) {
        Shop2 shop2 = new Shop2();
        if (!shop2.getShop2GoodToAmount().containsKey(good)) {

        } else {
            if (!check.containsKey(good)) {
                check.put(good, 1);
                totalPrice += good.getCost();
            } else {
                check.replace(good, check.get(good) + 1);
                totalPrice += good.getCost();
            }
            if (shop2.getShop2GoodToAmount().get(good) > check.get(good)) {
               shop2.getShop2GoodToAmount().replace(good, shop2.getShop2GoodToAmount().get(good) - 1);
            } else {
               shop2.getShop2GoodToAmount().remove(good);
            }
        }
    }


}
