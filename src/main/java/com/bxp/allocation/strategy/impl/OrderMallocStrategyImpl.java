package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.AllocationResult;
import com.bxp.allocation.Brand;
import com.bxp.allocation.Vendor;
import com.bxp.allocation.strategy.MallocStrategy;

import java.util.List;

public class OrderMallocStrategyImpl implements MallocStrategy {

    /**
     * 按顺序依次分配
     * 执行真正的分配
     * @param vendorList 商家列表
     * @param startVendor 从第几个商家开始分配
     * @param brandList 品牌列表
     * @param startBrand 从第几个品牌开始分配
     */
    @Override
    public AllocationResult execMalloc(List<Vendor> vendorList, int startVendor, List<Brand> brandList, int startBrand){
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
            //商家需要的数量等于当前品牌的数量，直接分配即可
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
}
