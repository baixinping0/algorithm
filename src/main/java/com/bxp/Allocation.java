package com.bxp;

import com.bxp.strategy.BrandStrategy;
import com.bxp.strategy.VendorStrategy;
import com.bxp.strategy.impl.OrderBrandStrategy;
import com.bxp.strategy.impl.OrderVendorStrategy;
import com.bxp.strategy.impl.ShuffleVendorStrategy;

import java.util.*;

public class Allocation {
    public AllocationResult defaultMalloc(List<Vendor> vendorList, List<Brand> brandList){
        return malloc(vendorList, brandList, new ShuffleVendorStrategy(), new OrderBrandStrategy());
    }

    public AllocationResult malloc(List<Vendor> vendorList, List<Brand> brandList, VendorStrategy vendorStrategy, BrandStrategy brandStrategy){
        return malloc(vendorList, 0,  brandList, 0, vendorStrategy, brandStrategy);
    }

    public AllocationResult malloc(List<Vendor> vendorList, List<Brand> brandList, VendorStrategy vendorStrategy){
        return malloc(vendorList, 0,  brandList, 0, vendorStrategy, new OrderBrandStrategy());
    }

    public AllocationResult malloc(List<Vendor> vendorList, int vendorIndex, List<Brand> brandList, int brandIndex,  VendorStrategy vendorStrategy, BrandStrategy brandStrategy){
        vendorStrategy.strategy(vendorList);
        brandStrategy.strategy(brandList);
        return execMalloc(vendorList, vendorIndex,  brandList, brandIndex);
    }

    /**
     * 执行真正的分配
     * @param vendorList 商家列表
     * @param startVendor 从第几个商家开始分配
     * @param brandList 品牌列表
     * @param startBrand 从第几个品牌开始分配
     */
    private AllocationResult execMalloc(List<Vendor> vendorList, int startVendor, List<Brand> brandList, int startBrand){
        int brandIndex = startBrand;
        int vendorIndex = startVendor;
        while (true){
            //所有商家都分配完成，或者所有品牌都分配完成，不再继续分配
            if (vendorIndex >= vendorList.size() || brandIndex >= brandList.size()){
                break;
            };
            Vendor vendor = vendorList.get(vendorIndex);
            Brand brand = brandList.get(brandIndex);
            //要给当前商家分配的数量
            Integer vendorNeed = vendor.getNeed();
            //商家需要的数量小于当前品牌的数量，直接分配即可
            if (vendorNeed < brand.getCount()){
                brand.malloc(vendor, vendorNeed);
                //当前商家分配结束，开始给下一个商家分配
                vendorIndex ++;
                continue;
            }
            //商家需要的数量小于当前品牌的数量，直接分配即可
            if (vendorNeed == brand.getCount()){
                brand.malloc(vendor, vendorNeed);
                //当前商家分配结束，开始给下一个商家分配, 并且开始分配下一个品牌
                vendorIndex ++;
                brandIndex ++;
                continue;
            }
            //商家需要的数量大于当前品牌的数量，先把当前品牌全部分配给当前商家,
            //下一轮分配继续给当前商家分配，开始分配下一个品牌
            brand.malloc(vendor, brand.getCount());
            brandIndex ++;
        }

        //记录当期顺序
        Integer[] vendorOrder = new Integer[vendorList.size()];
        for (int i = 0; i < vendorList.size(); i++){
            vendorOrder[i] = vendorList.get(i).getIndex();
        }
        Integer[] brandOrder = new Integer[brandList.size()];
        for (int i = 0; i < brandList.size(); i++){
            brandOrder[i] = brandList.get(i).getIndex();
        }
        return AllocationResult.buildMallocResult(vendorList, vendorIndex, brandList, brandIndex, vendorOrder, brandOrder);
    }


    public List<Brand> initBrandList(String[] brandNames, Integer[] brandCounts) {
        List<Brand> brandList = new ArrayList<>();
        for (int i = 0; i < brandCounts.length; i++){
            brandList.add(new Brand(i, brandNames[i], brandCounts[i]));
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
