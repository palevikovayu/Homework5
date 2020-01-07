package com.company;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Goods mouse1 = new Mouse(1, "mouse Logitech", 21);
        Goods mouse2 = new Mouse(2, "mouse DELL", 25);
        Goods mouse3 = new Mouse(3, "mouse Genius", 30);
        Goods mouse4 = new Mouse(3, "mouse Genius", 30);
        Goods monitor1 = new Monitor(4, "monitor BenQ", 71);
        Goods monitor2 = new Monitor(5, "monitor LG", 84);
        Goods keyboard1 = new Keyboard(6, "keyboard HP", 43);
        Goods keyboard2 = new Keyboard(7, "keyboard Razer", 47);
        Shop shop = new Shop();
        try {
            shop.addGoodToShopFromFile("shop1.csv");
        } catch (IOException e) {
            shop.addGoodToShop(mouse1);
            shop.addGoodToShop(mouse2);
            shop.addGoodToShop(mouse3);
            shop.addGoodToShop(mouse3);
            shop.addGoodToShop(mouse3);
            shop.addGoodToShop(mouse4);
            shop.addGoodToShop(monitor1);
            shop.addGoodToShop(monitor2);
            shop.addGoodToShop(keyboard1);
            shop.addGoodToShop(keyboard2);
        }
        System.out.println(shop.toString());
        System.out.println("");
        List<Long> listOfRequiredId = Arrays.asList(1L, 3L, 3L, 4L);
        shop.createPayCheck(listOfRequiredId);
        shop.printPayCheck();
        System.out.println("");
        shop.addGoodToFileFromShop(monitor2);
        System.out.println(shop.toString());
        System.out.println("");
        Shop2 shop2 = new Shop2();
        try {
            shop2.addGoodToShop2FromFile("shop2.json");
        } catch (IOException e) {
            shop2.addGoodToShop(mouse1);
            shop2.addGoodToShop(mouse2);
            shop2.addGoodToShop(monitor1);
            shop2.addGoodToShop(monitor2);
            shop2.addGoodToShop(keyboard1);
            shop2.addGoodToShop(keyboard2);
        }
        System.out.println(shop2.toString());
        System.out.println("");
        List<Long> listOfRequiredId2 = Arrays.asList(2L, 3L, 3L, 5L);
        shop2.createPayCheck(listOfRequiredId2);
        shop2.printPayCheck();
        System.out.println("");
        System.out.println(shop2.toString());
        System.out.println("");
    }
}
