package com.bxp.strategy.impl;

import com.bxp.Vendor;
import com.bxp.strategy.VendorSortStrategy;

import java.util.Collections;
import java.util.List;

public class ShuffleVendorSortStrategy implements VendorSortStrategy {
    @Override
    public void strategy(List<Vendor> vendorList) {
        Collections.shuffle(vendorList);
    }
}
