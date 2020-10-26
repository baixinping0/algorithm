package com.bxp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllocationResult {
    private List<Vendor> vendorList;
    private Integer vendorIndex;
    private List<Brand> brandList;
    private Integer brandIndex;
    private Integer[] vendorOrder;
    private Integer[] brandOrder;

    public static AllocationResult buildMallocResult(List<Vendor> vendorList, Integer vendorIndex, List<Brand> brandList, Integer brandIndex, Integer[] vendorOrder, Integer[] brandOrder){
        return new AllocationResult(vendorList, vendorIndex, brandList, brandIndex, vendorOrder, brandOrder);
    }

    /**
     * 商家有剩余
     * @return
     */
    public boolean isVendorSurplus(){
        return vendorIndex < vendorList.size();
    }

    /**
     * 品牌有剩余
     * @return
     */
    public boolean isBrandSurplus(){
        return brandIndex < brandList.size();
    }

}
