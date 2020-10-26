package com.bxp.strategy.impl;

import com.bxp.Brand;
import com.bxp.Vendor;
import com.bxp.strategy.BrandStrategy;
import com.bxp.strategy.VendorStrategy;

import java.util.Collections;
import java.util.List;

public class ShuffleBrandStrategy implements BrandStrategy {

    @Override
    public void strategy(List<Brand> brandList) {
        Collections.shuffle(brandList);
    }
}
