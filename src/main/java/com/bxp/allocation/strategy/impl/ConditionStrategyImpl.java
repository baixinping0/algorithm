package com.bxp.allocation.strategy.impl;

import com.bxp.allocation.Brand;
import com.bxp.allocation.strategy.ConditionStrategy;

/**
 * 判断商品剩余数量和商家需要数量之间的关系，判断是否能够分配
 */
public class ConditionStrategyImpl implements ConditionStrategy {
    @Override
    public boolean condition(Brand brand) {
        return true;
    }
}
