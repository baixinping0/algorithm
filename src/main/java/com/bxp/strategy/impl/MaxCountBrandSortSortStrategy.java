package com.bxp.strategy.impl;

import com.bxp.Brand;
import com.bxp.strategy.BrandSortStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxCountBrandSortSortStrategy implements BrandSortStrategy {
    @Override
    public void strategy(List<Brand> brandList) {
        Collections.sort(brandList, new Comparator<Brand>() {
            @Override
            public int compare(Brand o1, Brand o2) {
                return o2.getCount() - o1.getCount();
            }
        });
    }
}
