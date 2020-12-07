package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.Vendor;
import com.bxp.allocation.strategy.VendorSortStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RateVendorSortStrategy implements VendorSortStrategy {

    @Override
    public void strategy(List<Vendor> vendorList) {
        Collections.sort(vendorList, new Comparator<Vendor>() {
            @Override
            public int compare(Vendor o1, Vendor o2) {
                return  o2.getRate() - o1.getRate();
            }
        });
    }
}
