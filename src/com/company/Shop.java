package com.company;

import java.util.List;

public interface Shop {

    void addGoodToShop(Good good);

    PayCheck createPayCheck (List<Long> list);
}
