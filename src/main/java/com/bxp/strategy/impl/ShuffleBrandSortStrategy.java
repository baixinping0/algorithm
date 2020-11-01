package com.bxp.strategy.impl;

import com.bxp.Brand;
import com.bxp.strategy.BrandSortStrategy;

import java.util.Collections;
import java.util.List;

public class ShuffleBrandSortStrategy implements BrandSortStrategy {

    @Override
    public void strategy(List<Brand> brandList) {
        Collections.shuffle(brandList);
    }
}
