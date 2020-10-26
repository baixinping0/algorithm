package com.bxp.strategy.impl;

import com.bxp.Vendor;
import com.bxp.strategy.VendorStrategy;

import java.util.Collections;
import java.util.List;

public class ShuffleVendorStrategy implements VendorStrategy {
    @Override
    public void strategy(List<Vendor> vendorList) {
        Collections.shuffle(vendorList);
    }
}
