package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop2 implements Shop {

    private Map<Good, Integer> shop2GoodToAmount = new HashMap<>();
    private Map<Long, Good> shop2IdToGood = new HashMap<>();

    public Map<Good, Integer> getShop2GoodToAmount() {
        return shop2GoodToAmount;
    }

    public void setShop2GoodToAmount(Map<Good, Integer> shop2GoodToAmount) {
        this.shop2GoodToAmount = shop2GoodToAmount;
    }

    public Map<Long, Good> getShop2IdToGood() {
        return shop2IdToGood;
    }

    public void setShop2IdToGood(Map<Long, Good> shop2IdToGood) {
        this.shop2IdToGood = shop2IdToGood;
    }

    @Override
    public void addGoodToShop(Good good) {
        if (good == null) {
            throw new IllegalArgumentException("Product shouldn't be null");
        } else {
            shop2IdToGood.put(good.getId(), good);
            if (!shop2GoodToAmount.containsKey(good)) {
                shop2GoodToAmount.put(good, 1);
            } else {
                shop2GoodToAmount.put(good, shop2GoodToAmount.get(good) + 1);
            }
        }
    }

    @Override
    public PayCheck createPayCheck(List<Long> listOfRequiredId) {
        PayCheck check = new PayCheck();
        for (int i = 0; i < listOfRequiredId.size(); i++) {
            if (shop2IdToGood.containsKey(listOfRequiredId.get(i))) {
                if (!shop2GoodToAmount.containsKey(shop2IdToGood.get(listOfRequiredId.get(i)))) {

                } else {
                    check.addGoodToCheck(shop2IdToGood.get(listOfRequiredId.get(i)));
                    if (shop2GoodToAmount.get(shop2IdToGood.get(listOfRequiredId.get(i))) > check.getCheck().get(shop2IdToGood.get(listOfRequiredId.get(i)))) {
                        shop2GoodToAmount.replace(shop2IdToGood.get(listOfRequiredId.get(i)), shop2GoodToAmount.get(shop2IdToGood.get(listOfRequiredId.get(i))) - 1);
                    } else {
                        shop2GoodToAmount.remove(shop2IdToGood.get(listOfRequiredId.get(i)));
                    }
                }
            }
        }
        return check;
    }

    public void printPayCheck(PayCheck check) {
        check.createCheck();
    }

    public String toString() {
        return "Goods in Shop2: " + shop2GoodToAmount;
    }

}
