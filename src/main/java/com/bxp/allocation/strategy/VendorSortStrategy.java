package com.bxp.allocation.strategy;

import com.bxp.allocation.Vendor;

import java.util.List;

public interface VendorSortStrategy {
    void strategy(List<Vendor> vendorList);
}
