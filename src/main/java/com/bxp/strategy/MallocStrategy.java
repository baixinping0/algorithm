package com.bxp.strategy;

import com.bxp.AllocationResult;
import com.bxp.Brand;
import com.bxp.Vendor;

import java.util.List;

public interface MallocStrategy {
    public AllocationResult execMalloc(List<Vendor> vendorList, int startVendor, List<Brand> brandList, int startBrand);
}
