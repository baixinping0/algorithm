package com.bxp.allocation.strategy;

import com.bxp.allocation.AllocationResult;
import com.bxp.allocation.Brand;
import com.bxp.allocation.Vendor;

import java.util.List;

public interface MallocStrategy {
    public AllocationResult execMalloc(List<Vendor> vendorList, int startVendor, List<Brand> brandList, int startBrand);
}
