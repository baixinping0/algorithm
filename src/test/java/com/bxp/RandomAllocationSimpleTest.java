package com.bxp;

import com.alibaba.fastjson.JSON;
import com.bxp.strategy.BrandSortStrategy;
import com.bxp.strategy.impl.*;
import org.junit.Test;

import java.util.List;

public class RandomAllocationSimpleTest {
    /**
     * L1P1,4,0,0,
     * L1P2,3,2,0,
     * L1P3,0,1,5,
     * L1P4,0,0,5,
     */
    @Test
    public void randomMallocTest0(){
        String[] vendorNames = new String[]{"L1P1","L1P2","L1P3","L1P4"};
        Integer vendorCounts[] = new Integer[]{4, 3, 5, 7};
        Integer[] vendorRates = new Integer[]{4, 3, 2, 1};
        String[] brandNames = new String[]{"B1","B2","B3"};
        Integer[] brandCounts = new Integer[]{9, 3, 10};
        long start = System.currentTimeMillis();

        //7, 6, 4, 3
        //10. 7, 3
        //7, 6, 3,

        Allocation allocation = new Allocation();
        List<Vendor> vendorList = allocation.initVendorList(vendorNames,vendorCounts, vendorRates);
        List<Brand> brandList = allocation.initBrandList(brandNames, brandCounts);

        AllocationResult result = allocation.malloc(vendorList, brandList);

        System.out.println(JSON.toJSONString(vendorList));
        System.out.println();
    }
    @Test
    public void randomMallocTest(){
        Allocation simple = new Allocation();
        String[] vendorNames = new String[]{"L1P1","L1P2","L1P3","L1P4","L1P5","L1P6","L2P1","L2P2","L2P3","L2P4","L2P5","L3P1","L3P2","L3P3","L3P4","L3P5","L4P3","L4P4","L4P5","L4P6","L5P4","L5P5","L5P6","L6P5","L6P6","VIP"};
        Integer vendorCounts[] = new Integer[]{368,122,41,14,6,1,737,781,457,33,6,30,541,2031,264,29,359,2305,228,2,273,1359,16,19,274,1};
        Integer[] vendorRates = new Integer[]{66,40,27,12,3,0,351,594,355,24,3,8,437,3093,460,24,816,5293,546,3,556,5409,43,64,1435,0};
        String[] brandNames = new String[]{"B1","B2","B3","B4","B5","B6","B7","B8"};
        Integer[] brandCounts = new Integer[]{5855, 110, 1352, 2892, 2572, 1280, 3140, 2458};
        long start = System.currentTimeMillis();

        Allocation allocation = new Allocation();
        List<Vendor> vendorList = allocation.initVendorList(vendorNames,vendorCounts, vendorRates);
        List<Brand> brandList = allocation.initBrandList(brandNames, brandCounts);

        AllocationResult result = allocation.malloc(vendorList, brandList);

        System.out.println(JSON.toJSONString(vendorList));
        System.out.println("执行时间 : " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void main(String[] args) {
        String[] vendorNames = new String[]{"L1P1","L1P2","L1P3","L1P4"};
        Integer vendorCounts[] = new Integer[]{4, 5, 6, 5};
        Integer[] vendorRates = new Integer[]{4, 3, 2, 1};
        String[] brandNames = new String[]{"B1","B2","B3", "B4"};
        Integer[] brandCounts = new Integer[]{7, 3, 12, 10};
        long start = System.currentTimeMillis();

        Allocation allocation = new Allocation();
        List<Vendor> vendorList = allocation.initVendorList(vendorNames,vendorCounts, vendorRates);
        List<Brand> brandList = allocation.initBrandList(brandNames, brandCounts);

        BrandSortStrategy brandSortStrategy = new MaxCountBrandSortSortStrategy();
        brandSortStrategy.strategy(brandList);
        System.out.println();
    }
}
