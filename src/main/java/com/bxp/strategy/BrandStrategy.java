package com.bxp.strategy;

import com.bxp.Brand;
import com.bxp.Vendor;

import java.util.List;

public interface BrandStrategy {
    void strategy(List<Brand> brandList);
}
