package com.bxp.allocation.strategy;

import com.bxp.allocation.Brand;

import java.util.List;

public interface BrandSortStrategy {
    void strategy(List<Brand> brandList);
}
