package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.Vendor;
import com.bxp.allocation.strategy.VendorSortStrategy;

import java.util.Collections;
import java.util.List;

public class ShuffleVendorSortStrategy implements VendorSortStrategy {
    @Override
    public void strategy(List<Vendor> vendorList) {
        Collections.shuffle(vendorList);
    }
}
