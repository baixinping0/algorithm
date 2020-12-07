package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.Brand;
import com.bxp.allocation.strategy.BrandSortStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxLastCountBrandSortStrategy implements BrandSortStrategy {
    @Override
    public void strategy(List<Brand> brandList) {
        Collections.sort(brandList, new Comparator<Brand>() {
            @Override
            public int compare(Brand o1, Brand o2) {
                return o2.getLastCount() - o1.getLastCount();
            }
        });
    }
}
