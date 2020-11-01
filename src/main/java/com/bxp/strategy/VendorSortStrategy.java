package com.bxp.strategy;

import com.bxp.Vendor;

import java.util.List;

public interface VendorSortStrategy {
    void strategy(List<Vendor> vendorList);
}
