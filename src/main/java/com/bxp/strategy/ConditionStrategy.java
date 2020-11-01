package com.bxp.strategy;

import com.bxp.Brand;

public interface ConditionStrategy {
    /**
     * 判断商品剩余数量和商家需要数量之间的关系，判断是否能够分配
     * @return
     * @param brand
     */
    boolean condition(Brand brand);
}
