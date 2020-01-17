package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Shop1 shop1 = new Shop1();
        ProductFileReader fileCSV = new ProductFileReader();
        try {
            shop1.setList(fileCSV.addGoodToShop1FromCSV("shop1.csv"));
        } catch (IOException e) {
            shop1.addGoodToShop(new Mouse(1, "mouse Logitech", 21));
            shop1.addGoodToShop(new Mouse(3, "mouse Genius", 30));
            shop1.addGoodToShop(new Mouse(3, "mouse Genius", 30));
            shop1.addGoodToShop(new Mouse(3, "mouse Genius", 30));
            shop1.addGoodToShop(new Keyboard(6, "keyboard HP", 43));
        }
        System.out.println(shop1.toString());
        System.out.println("");
        List<Long> listOfRequiredId = Arrays.asList(1L, 3L, 3L, 4L);

        shop1.printPayCheck(shop1.createPayCheck(listOfRequiredId));
        System.out.println("");
        shop1.addGoodToFileFromShop(new Mouse(2, "mouse DELL", 25));
        System.out.println(shop1.toString());
        System.out.println("");
        Shop2 shop2 = new Shop2();
        ProductFileReader fileJSON = new ProductFileReader();
        try {
            shop2.setShop2GoodToAmount(fileJSON.addGoodToShop2FromJSON("shop2.json"));
        } catch (IOException e) {
            shop2.addGoodToShop(new Mouse(1, "mouse Logitech", 21));
            shop2.addGoodToShop(new Mouse(2, "mouse DELL", 25));
            shop2.addGoodToShop(new Monitor(4, "monitor BenQ", 71));
            shop2.addGoodToShop(new Monitor(5, "monitor LG", 84));
            shop2.addGoodToShop(new Keyboard(6, "keyboard HP", 43));
            shop2.addGoodToShop(new Keyboard(7, "keyboard Razer", 47));
        }
        System.out.println(shop2.toString());
        System.out.println("");
        List<Long> listOfRequiredId2 = Arrays.asList(2L, 3L, 3L, 5L);
        shop2.printPayCheck(shop2.createPayCheck(listOfRequiredId2));
        System.out.println("");
        System.out.println(shop2.toString());
        System.out.println("");
    }
}
