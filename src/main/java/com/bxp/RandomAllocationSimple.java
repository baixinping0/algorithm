package com.bxp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class RandomAllocationSimple {
    private String[] vendorNames = new String[]{"L1P1","L1P2","L1P3","L1P4","L1P5","L1P6","L2P1","L2P2","L2P3","L2P4","L2P5","L3P1","L3P2","L3P3","L3P4","L3P5","L4P3","L4P4","L4P5","L4P6","L5P4","L5P5","L5P6","L6P5","L6P6","VIP"};
    private Integer[] venderRates = new Integer[]{66,40,27,12,3,0,351,594,355,24,3,8,437,3093,460,24,816,5293,546,3,556,5409,43,64,1435,0};
    private String[] brandNames = new String[]{"B1","B2","B3","B4","B5","B6","B7","B8"};
    private Integer finalVendors[] = new Integer[]{368,122,41,14,6,1,737,781,457,33,6,30,541,2031,264,29,359,2305,228,2,273,1359,16,19,274,1};
    public static void main(String[] args) {
        Integer vendors[] = new Integer[]{368,122,41,14,6,1,737,781,457,33,6,30,541,2031,264,29,359,2305,228,2,273,1359,16,19,274,1};
        Integer[] brands = new Integer[]{5855, 110, 1352, 2892, 2572, 1280, 3140, 2458};
        long start = System.currentTimeMillis();
        new RandomAllocationSimple().randomMalloc(vendors, brands, true);
        System.out.println("执行时间 : " + (System.currentTimeMillis() - start) + " ms");
    }
    public void randomMalloc(Integer[] vendors, Integer[] brands, boolean shuffle){

        List<ArrayValueObj> vendorObjList =  initArrayValueObjList(vendors);
        if (shuffle){
            Collections.shuffle(vendorObjList);
        }
        Integer[][] result = doRandomMalloc(vendorObjList, brands);
        pringResult(vendorObjList, result);

        System.out.println();
    }

    private void pringResult(List<ArrayValueObj> vendorObjList, Integer[][] result) {
        System.out.print(" ,");
        for (int i = 0; i < brandNames.length; i++){
            System.out.print(brandNames[i] + ",");
        }
        System.out.println();
        for (int i = 0; i < result.length; i++){
            System.out.print(vendorNames[vendorObjList.get(i).getIndex()] + ",");
            for (int j = 0; j < result[i].length; j++){
                System.out.print(result[i][j] + ",");
            }
            System.out.println();
        }
    }

    private Integer[][] doRandomMalloc(List<ArrayValueObj> vendorObjList, Integer[] brands) {
        Integer[][] result = initResultArray(vendorObjList.size(), brands.length);
        int brandIndex = 0;
        for (int i = 0; i < vendorObjList.size(); i++){
            ArrayValueObj vendorObj = vendorObjList.get(i);
            Integer vendorValue = vendorObj.getValue();
            if (vendorValue < brands[brandIndex]){
                result[i][brandIndex] = vendorValue;
                brands[brandIndex] = brands[brandIndex] - vendorValue;
            }else if (vendorValue == brands[brandIndex]){
                result[i][brandIndex] = vendorValue;
                brands[brandIndex] = 0;
                brandIndex ++;
            }else {
                while (true){
                    if (vendorValue < brands[brandIndex]){
                        result[i][brandIndex] = vendorValue;
                        brands[brandIndex] = brands[brandIndex] - vendorValue;
                        break;
                    }
                    result[i][brandIndex] = brands[brandIndex];
                    vendorValue = vendorValue - brands[brandIndex];
                    brands[brandIndex] = 0;
                    brandIndex++;
                }
            }
        }

        List<ArrayValueObj> rateObjList = initArrayValueObjList(venderRates);
        Collections.sort(rateObjList, new Comparator<ArrayValueObj>() {
            @Override
            public int compare(ArrayValueObj o1, ArrayValueObj o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for (ArrayValueObj rate : rateObjList){
            //税率在原数据中的位置
            Integer sourceIndex = rate.getIndex();
            //商家的户数，投放数量
            Integer venderCount = finalVendors[sourceIndex];
            //目前商家在结果数据中多少行
            Integer row = getResultIndex(sourceIndex, vendorObjList);



        }

        return result;
    }

    //通过索引，获取乱序后商家的位置，也就是在二维结果中的第几行
    private Integer getResultIndex(Integer index, List<ArrayValueObj> vendorObjList) {
        for (int i = 0; i < vendorObjList.size(); i++){
            if (index == vendorObjList.get(i).getIndex()){
                return i;
            }
        }
        return null;
    }

    private Integer[][] initResultArray(int size, int length) {
        Integer[][] result = new Integer[size][length];
        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[i].length; j++){
                result[i][j] = 0;
            }
        }
        return result;
    }

    private  List<ArrayValueObj> initArrayValueObjList(Integer[] vendors) {
        List<ArrayValueObj> vendorObjList = new ArrayList<>();
        for (int i = 0; i < vendors.length; i++){
            vendorObjList.add(new ArrayValueObj(i, vendors[i]));
        }
        return vendorObjList;
    }

}
