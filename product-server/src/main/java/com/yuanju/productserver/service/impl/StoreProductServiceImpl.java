package com.yuanju.productserver.service.impl;

import com.yuanju.productcommon.ProductInfoOutput;
import com.yuanju.productserver.dataobject.StoreProduct;
import com.yuanju.productserver.repository.StoreProductRepository;
import com.yuanju.productserver.service.StoreProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 17:32
 */
@Service
@Transactional
public class StoreProductServiceImpl implements StoreProductService {

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Override
    public List<ProductInfoOutput> findList(Integer storeId, List<Integer> productIdList) {
        return storeProductRepository.findByStoreIdAndProductInfo_IdIn(storeId, productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e.getProductInfo(), output);
                    output.setStoreId(e.getStoreId());
                    output.setStoreProductId(e.getStoreProductId());
                    output.setStorePrice(e.getStorePrice());
                    return output;
                })
                .collect(Collectors.toList());
    }
}
