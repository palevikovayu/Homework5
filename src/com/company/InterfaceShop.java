package com.company;

import java.util.List;

public interface InterfaceShop {

    void addGoodToShop(Goods good);

    PayCheck createPayCheck (List<Long> list);
}
