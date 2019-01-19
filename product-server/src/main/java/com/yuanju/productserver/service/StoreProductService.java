package com.yuanju.productserver.service;

import com.yuanju.productcommon.ProductInfoOutput;
import com.yuanju.productserver.dataobject.StoreProduct;

import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 17:29
 */
public interface StoreProductService {
    List<ProductInfoOutput> findList(Integer storeId, List<Integer> productIdList);
}
