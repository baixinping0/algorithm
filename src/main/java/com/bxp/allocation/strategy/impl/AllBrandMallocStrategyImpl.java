package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.AllocationResult;
import com.bxp.allocation.Brand;
import com.bxp.allocation.Vendor;
import com.bxp.allocation.strategy.BrandSortStrategy;
import com.bxp.allocation.strategy.ConditionStrategy;
import com.bxp.allocation.strategy.MallocStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 保证每个品牌都能够分配到至少一个商家
 */
public class AllBrandMallocStrategyImpl implements MallocStrategy {

    private ConditionStrategy conditionStrategy = new ConditionStrategyImpl();

    private BrandSortStrategy brandSortStrategy = new MaxLastCountBrandSortStrategy();
    /**
     * 存放已经分配的品牌
     */
    private List<Brand> allocatedBrandList = new ArrayList<>();

    @Override
    public AllocationResult execMalloc(List<Vendor> vendorList, int startVendor, List<Brand> brandList, int startBrand) {

        int nonBrandIndex = startBrand;
        int vendorIndex = startVendor;
        while (true) {
            //所有商家都分配完成，不再继续分配
            if (vendorIndex >= vendorList.size()) {
                break;
            }
            ;
            Vendor vendor = vendorList.get(vendorIndex);

            Brand nonBrand = nonBrandIndex < brandList.size() ? brandList.get(nonBrandIndex) : null;
            Brand allocatedBrand = allocatedBrandList.size() != 0 ? allocatedBrandList.get(0) : null;

            //先判断未分配过的能不能分配
            boolean nonAllocCond = nonBrand == null ? false : conditionStrategy.condition(nonBrand);
            boolean allocatedCond = allocatedBrand == null ? false : conditionStrategy.condition(allocatedBrand);
            //如果已经分配过的，和未分配的都不能再进行分配，则直接退出
            if (!nonAllocCond && !allocatedCond) {
                System.out.println("已经分配过的，和未分配的都不能再进行分配， 无法进行分配");
                throw new RuntimeException("已经分配过的，和未分配的都不能再进行分配， 无法进行分配");
            }
            Brand brand = nonAllocCond ? nonBrand : allocatedBrand;
            if (vendor.getNeed() <= brand.getLastCount()) {
                //商品够分配，则直接分配
                brand.malloc(vendor, vendor.getNeed());
            } else {
                //商品不够分，把所有商品都分配给这个商家
                brand.malloc(vendor, brand.getLastCount());
            }
            if (nonAllocCond) {
                allocatedBrandList.add(nonBrand);
                nonBrandIndex++;
            }
            brandSortStrategy.strategy(allocatedBrandList);
            vendorIndex ++;
        }
        if (vendorIndex < vendorList.size() - 1){
            throw new RuntimeException("还有品牌没有被分配到");
        }
        return AllocationResult.buildMallocResult(vendorList, vendorIndex, brandList, null, null, null);
    }
}
