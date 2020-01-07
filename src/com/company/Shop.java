package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Shop implements InterfaceShop {
    private List<Goods> listOfGoodsInShop = new ArrayList<>();
    private PayCheck check = new PayCheck();
    private double totalPrice = 0;

    public List<Goods> getList() {
        return listOfGoodsInShop;
    }

    public void setList(List<Goods> goods) {
        this.listOfGoodsInShop = goods;
    }

    public void addGoodToShop(Goods good) {
        if (good == null) {
            throw new IllegalArgumentException("Product shouldn't be null");
        } else {
            listOfGoodsInShop.add(good);
        }
    }

    public void addGoodToShopFromFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String goodString;
        do {
            goodString = bufferedReader.readLine();
            if (goodString == null) break;
            Goods good = createGood(goodString);
            listOfGoodsInShop.add(good);
        } while (goodString != null);
    }

    public static Goods createGood(String goodString) {
        String[] split = goodString.split(",");
        Goods good = new Goods(Long.parseLong(split[0]), split[1], Double.parseDouble(split[2]));
        return good;
    }

    public void addGoodToCheck(Goods good) {
        if (!listOfGoodsInShop.contains(good)) {

        } else {
            if (!check.getCheck().containsKey(good)) {
                check.getCheck().put(good, 1);
                totalPrice += good.getCost();
                listOfGoodsInShop.remove(good);
            } else {
                check.getCheck().replace(good, check.getCheck().get(good) + 1);
                totalPrice += good.getCost();
                listOfGoodsInShop.remove(good);
            }
        }
    }

    public PayCheck createPayCheck(List<Long> listOfRequiredId) {
        for (int i = 0; i < listOfRequiredId.size(); i++) {
            for (int j = 0; j < listOfGoodsInShop.size(); j++) {
                if (listOfRequiredId.get(i) == listOfGoodsInShop.get(j).getId()) {
                    addGoodToCheck(listOfGoodsInShop.get(j));
                    break;
                }
            }
        }
        return check;
    }

    public void printPayCheck() {
        for (Map.Entry<Goods, Integer> entry : check.getCheck().entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " un.");
        }
        System.out.printf("------------------%nTotal price: %f %n", totalPrice);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String toString() {
        return "Goods in Shop1: " + listOfGoodsInShop;
    }

    public void addGoodToFileFromShop(Goods product) throws IOException {
        FileWriter fileWriter = new FileWriter("shop1.csv", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append("\n" + product.getId() + ", \"" + product.getName() + "\", " + product.getCost());
        bufferedWriter.close();
    }
}
