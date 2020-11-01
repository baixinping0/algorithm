package com.bxp.strategy;

import com.bxp.Brand;
import com.bxp.Vendor;

import java.util.List;

public interface BrandSortStrategy {
    void strategy(List<Brand> brandList);
}
