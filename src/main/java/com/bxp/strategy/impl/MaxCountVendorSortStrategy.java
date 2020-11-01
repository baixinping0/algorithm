package com.bxp.strategy.impl;

import com.bxp.Vendor;
import com.bxp.strategy.VendorSortStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxCountVendorSortStrategy implements VendorSortStrategy {
    @Override
    public void strategy(List<Vendor> vendorList) {
        Collections.sort(vendorList, new Comparator<Vendor>() {
            @Override
            public int compare(Vendor o1, Vendor o2) {
                return o2.getCount() - o1.getCount();
            }
        });
    }
}
