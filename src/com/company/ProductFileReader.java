package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductFileReader {

    public List<Good> addGoodToShop1FromCSV(String fileName) throws IOException {
        List<Good> listOfGoodsInShop = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String goodString;
            do {
                goodString = bufferedReader.readLine();
                if (goodString == null) break;
                Good good = createGoodFromCSV(goodString);

                listOfGoodsInShop.add(good);
            } while (goodString != null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listOfGoodsInShop;
    }

    public static Good createGoodFromCSV(String goodString) {
        String[] split = goodString.split(",");
        Good good = new Good(Long.parseLong(split[0]), split[1], Double.parseDouble(split[2]));
        return good;
    }


    public Map<Good, Integer> addGoodToShop2FromJSON(String fileName) throws IOException {
        Map<Good, Integer> shop2GoodToAmount = new HashMap<>();
        Map<Long, Good> shop2IdToGood = new HashMap<>();
        List<String> listOfGoodsString = parseAccumulator(fileName);
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
        return shop2GoodToAmount;
    }

    public static List<String> parseAccumulator (String fileName){
        String accumulator = readFromJSON(fileName);
        accumulator = accumulator.replace("[", "");
        accumulator = accumulator.replace("]", "");
        accumulator = accumulator.replaceAll("\\s+", "");
        String[] split = accumulator.split("},\\{");
        List<String> listOfGoodsString = new ArrayList<>();
        for (String s1 : split) {
            String s2 = s1.replaceAll("\\{", "").replaceAll("}", "");
            listOfGoodsString.add(s2);
        }
        return listOfGoodsString;
    }

    public static String readFromJSON (String fileName) {
        String accumulator = "";
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String s = bufferedReader.readLine();
            while (s != null) {
                accumulator += s;
                s = bufferedReader.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return accumulator;
    }

    public static Good createGoodFromJSON(String goodFromString) {
        String[] pairs = goodFromString.split(",");
        String classString = pairs[0].split(":")[1];
        String idString = pairs[1].split(":")[1];
        String nameString = pairs[2].split(":")[1];
        String costString = pairs[3].split(":")[1];
        return new Good(Long.parseLong(idString), nameString, Double.parseDouble(costString));
    }

}
