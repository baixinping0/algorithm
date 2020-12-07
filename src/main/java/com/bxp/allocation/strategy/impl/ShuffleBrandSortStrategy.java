package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.Brand;
import com.bxp.allocation.strategy.BrandSortStrategy;

import java.util.Collections;
import java.util.List;

public class ShuffleBrandSortStrategy implements BrandSortStrategy {

    @Override
    public void strategy(List<Brand> brandList) {
        Collections.shuffle(brandList);
    }
}
