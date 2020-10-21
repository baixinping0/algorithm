package com.bxp;

import lombok.Data;

import java.util.*;

public class RandomAllocation {
    private Random random = new Random();
    public static void main(String[] args) {
        Integer[] vendors = new Integer[]{4, 5, 6, 5};
        Integer[] brands = new Integer[]{7, 3, 10};
        Integer sum = 20;
        RandomAllocation ra = new RandomAllocation();
        Map<Integer, Integer[]> result = ra.randomMalloc(vendors, brands, sum);

        printResult(result);
    }

    private static void printResult(Map<Integer, Integer[]> result) {
        Set<Map.Entry<Integer, Integer[]>> set = result.entrySet();
        for (Map.Entry<Integer, Integer[]> entry : set){
            Integer[] values = entry.getValue();
            System.out.println("第 " + entry.getKey() + " 个品牌分配：");
            for (int i = 0; i < values.length; i++){
                System.out.println("给第" + i + "个店铺量：  " + values[i]);
            }
            System.out.println("--------------------");
        }
    }
    /**
     * @param vendors [4, 5, 6, 5]
     * @param brands [7, 3, 10]
     * @param sum [20]
     */
    public Map<Integer, Integer[]> randomMalloc(Integer[] vendors, Integer[] brands, int sum){
        Integer[] mallocResult = doRandomMalloc(vendors, brands, sum);
        Map<Integer, Integer[]> result = dealMallocResult(mallocResult, vendors, brands);
        return result;
    }

    private Map<Integer, Integer[]> dealMallocResult(Integer[] mallocResult, Integer[] vendors, Integer[] brands) {
        //每种品牌给了每个店铺多少
        Map<Integer, Integer[]> map = new TreeMap<Integer, Integer[]>();
        for (int i = 0; i < brands.length; i++){
            Integer[]  countResult = countSingleBrand(mallocResult, vendors, i);
            map.put(i, countResult);
        }
        return map;
    }

    /**
     *
     * @param mallocResult
     * @param vendors
     * @param b
     * @return 第i中品牌，给了每个商家多少
     */
    private Integer[] countSingleBrand(Integer[] mallocResult, Integer[] vendors, int b) {
        Integer[] res = new Integer[vendors.length];
        int left = 0;
        for (int i = 0; i < vendors.length; i++){
            int vendorCount = vendors[i];
            int right = left + vendorCount;
            int count = 0;
            for (int j = left; j < right; j++){
                if (mallocResult[j] == b){
                    count++;
                }
            }
            res[i] = count;
            left = right;
        }
        return res;
    }

    private Integer[] doRandomMalloc(Integer[] vendors, Integer[] brands, int sum) {
        //[-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
        Integer[] vendorArray = initVendorArray(sum);
        //[0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
        Integer[] brandArray = initBrandArray(brands, sum);
        //每次取数范围，第一次0-20， 第二次0-19
        int bound = sum;
        //按顺序赋值，每次加1
        int vendorArrayIndex = 0;
        while (bound != 0){
            //取随机数赋值
            Integer brandArrayIndex =  random.nextInt(bound--);
            vendorArray[vendorArrayIndex++] = brandArray[brandArrayIndex];
            //如果取的随机数刚好是最后一位，就不用交换，否则就交换位置
            if (brandArrayIndex != bound){
                arraySwap(brandArray, brandArrayIndex, bound);
            }
        }
        return vendorArray;
    }

    private void arraySwap(Integer[] array, Integer i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    private Integer[] initVendorArray(int sum) {
        Integer[] vendorArray =  new Integer[sum];
        for (int i = 0; i < vendorArray.length; i++){
            vendorArray[i] = -1;
        }
        return vendorArray;
    }
    //[7, 3, 10]
    private Integer[] initBrandArray(Integer[] brands, int sum) {
        Integer[] brandArray =  new Integer[sum];
        int index = 0;
        for (int i = 0; i < brands.length; i++){
            int count = brands[i];
            while (count != 0){
                brandArray[index++] = i;
                count --;
            }
        }
        return brandArray;
    }
}
