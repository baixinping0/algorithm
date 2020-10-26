package com.bxp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Brand {
    private Integer index;
    private String name;
    private Integer count;

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
        if (this.count < count) {
            return false;
        }
        Brand brand = new Brand(this.getIndex(), this.getName(), count);
        this.setCount(this.getCount() - count);
        vendor.malloc(brand);
        return true;
    }
}