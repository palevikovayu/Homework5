package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shop1 implements Shop {
    private List<Good> listOfGoodsInShop = new ArrayList<>();

    public List<Good> getList() {
        return listOfGoodsInShop;
    }

    public void setList(List<Good> goods) {
        this.listOfGoodsInShop = goods;
    }

    public void addGoodToShop(Good good) {
        if (good == null) {
            throw new IllegalArgumentException("Product shouldn't be null");
        } else {
            listOfGoodsInShop.add(good);
        }
    }

    public PayCheck createPayCheck(List<Long> listOfRequiredId) {
        PayCheck check = new PayCheck();
        for (int i = 0; i < listOfRequiredId.size(); i++) {
            for (int j = 0; j < listOfGoodsInShop.size(); j++) {
                if (listOfRequiredId.get(i) == listOfGoodsInShop.get(j).getId()) {
                    if (!listOfGoodsInShop.contains(listOfGoodsInShop.get(j))) {

                    } else {
                        check.addGoodToCheck(listOfGoodsInShop.get(j));
                        listOfGoodsInShop.remove(listOfGoodsInShop.get(j));
                        break;
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
        return "Goods in Shop1: " + listOfGoodsInShop;
    }

    public void addGoodToFileFromShop(Good product) {
        try (FileWriter fileWriter = new FileWriter("shop1.csv", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.append("\n" + product.getId() + ", \"" + product.getName() + "\", " + product.getCost());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
