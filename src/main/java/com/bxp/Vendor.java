package com.bxp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Vendor {
    /**
     * 商家最初位置
     */
    private Integer index;
    /**
     * 商家名称
     */
    private String name;
    /**
     * 商家最开始数量
     */
    private Integer count;
    /**
     * 需要给商家分配的数量
     */
    private Integer need;
    /**
     * 商家的比重
     */
    private Integer rate;
    /**
     * 商家拥有的品牌
     */
    private List<Brand> brands;

    /**
     * 给商家分配品牌
     *
     * @param brand
     */
    public boolean malloc(Brand brand) {
        //给商家分配的数量大于商家需要的数量，不能这样分配，直接返回false
        if (brand.getLastCount() > need) {
            return false;
        }
        this.setNeed(this.getNeed() - brand.getCount());
        brands.add(brand);
        return true;
    }
}