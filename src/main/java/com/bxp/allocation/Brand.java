package com.bxp.allocation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Brand {
    private Integer index;
    private String name;
    /**
     * 品牌原始数量
     */
    private Integer count;
    /**
     * 品牌剩余数量
     */
    private Integer lastCount;

    /**
     * 分配给某一个商家，多少这种品牌
     *
     * @param vendor
     * @param count
     */
    public boolean malloc(Vendor vendor, Integer count) {
        if (vendor == null) {
            return false;
        }
        if (this.lastCount < count) {
            return false;
        }
        /**
         * 创建一个品牌子集给一个商家
         */
        Brand brand = new Brand(this.getIndex(), this.getName(), count,  count);
        vendor.malloc(brand);
        this.setLastCount(this.getLastCount() - count);
        return true;
    }
}