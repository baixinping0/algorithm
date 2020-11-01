package com.bxp;

import com.bxp.strategy.BrandSortStrategy;
import com.bxp.strategy.MallocStrategy;
import com.bxp.strategy.VendorSortStrategy;
import com.bxp.strategy.impl.*;

import java.util.*;

public class Allocation {
    private VendorSortStrategy vendorSortStrategy;
    private BrandSortStrategy brandSortStrategy;
    private MallocStrategy mallocStrategy;
    public Allocation(){
        this(new MaxCountVendorSortStrategy(), new MaxCountBrandSortSortStrategy(), new AllBrandMallocStrategyImpl());
    }

    public Allocation(VendorSortStrategy vendorSortStrategy, BrandSortStrategy brandSortStrategy, MallocStrategy mallocStrategy) {
        this.vendorSortStrategy = vendorSortStrategy;
        this.brandSortStrategy = brandSortStrategy;
        this.mallocStrategy = mallocStrategy;
    }


    public AllocationResult malloc(List<Vendor> vendorList, List<Brand> brandList){
        return malloc(vendorList, 0,  brandList, 0);
    }

    public AllocationResult malloc(List<Vendor> vendorList, int vendorIndex, List<Brand> brandList, int brandIndex){
        vendorSortStrategy.strategy(vendorList);
        brandSortStrategy.strategy(brandList);
        return mallocStrategy.execMalloc(vendorList, vendorIndex,  brandList, brandIndex);
    }




    public List<Brand> initBrandList(String[] brandNames, Integer[] brandCounts) {
        List<Brand> brandList = new ArrayList<>();
        for (int i = 0; i < brandCounts.length; i++){
            brandList.add(new Brand(i, brandNames[i], brandCounts[i], brandCounts[i]));
        }
        return brandList;
    }

    /**
     * 创建vendor对象
     * @param vendorCounts
     * @param vendorNames
     * @param vendorRates
     * @return
     */
    public List<Vendor> initVendorList(String[] vendorNames, Integer[] vendorCounts, Integer[] vendorRates) {
        List<Vendor> vendorObjList = new ArrayList<>();
        for (int i = 0; i < vendorCounts.length; i++){
            vendorObjList.add(new Vendor(i, vendorNames[i], vendorCounts[i], vendorCounts[i], vendorRates[i], new ArrayList<>()));
        }
        return vendorObjList;
    }
}
