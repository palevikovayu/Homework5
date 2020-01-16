package com.company;

import java.io.*;
import java.util.ArrayList;
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

    public void addGoodToShop2FromFile(String fileName) throws IOException {
        String accumulator = "";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        while (s != null) {
            accumulator += s;
            s = bufferedReader.readLine();
        }
        accumulator = accumulator.replace("[", "");
        accumulator = accumulator.replace("]", "");
        accumulator = accumulator.replaceAll("\\s+", "");
        String[] split = accumulator.split("},\\{");
        List<String> listOfGoodsString = new ArrayList<>();
        for (String s1 : split) {
            String s2 = s1.replaceAll("\\{", "").replaceAll("}", "");
            listOfGoodsString.add(s2);
        }
        List<Good> listOfGoodsFromJson = new ArrayList<>();
        for (String s3 : listOfGoodsString) {
            listOfGoodsFromJson.add(createGoodFromJSON(s3));
        }
        for (int i = 0; i < listOfGoodsFromJson.size(); i++) {
            shop2IdToGood.put(listOfGoodsFromJson.get(i).getId(), listOfGoodsFromJson.get(i));
            if (!shop2GoodToAmount.containsKey(listOfGoodsFromJson.get(i))) {
                shop2GoodToAmount.put(listOfGoodsFromJson.get(i), 1);
            } else {
                shop2GoodToAmount.put(listOfGoodsFromJson.get(i), shop2GoodToAmount.get(listOfGoodsFromJson.get(i)) + 1);
            }
        }
    }

    public static Good createGoodFromJSON(String goodFromString) {
        String[] pairs = goodFromString.split(",");
        String classString = pairs[0].split(":")[1];
        String idString = pairs[1].split(":")[1];
        String nameString = pairs[2].split(":")[1];
        String costString = pairs[3].split(":")[1];
        return new Good(Long.parseLong(idString), nameString, Double.parseDouble(costString));
    }

    @Override
    public PayCheck createPayCheck(List<Long> listOfRequiredId) {
        PayCheck check = new PayCheck();
        for (int i = 0; i < listOfRequiredId.size(); i++) {
            if (shop2IdToGood.containsKey(listOfRequiredId.get(i))) {
               check.addGoodToCheck2(shop2IdToGood.get(listOfRequiredId.get(i)));
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
