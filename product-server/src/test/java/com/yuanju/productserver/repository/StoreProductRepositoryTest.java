package com.yuanju.productserver.repository;

import com.yuanju.productserver.ProductServerApplicationTests;
import com.yuanju.productserver.dataobject.StoreProduct;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 15:49
 */
@Component
public class StoreProductRepositoryTest extends ProductServerApplicationTests {

    @Autowired
    private StoreProductRepository storeProductRepository;
    @Test
    public void findByStoreProductId() {
        StoreProduct storeProduct = storeProductRepository.findByStoreProductId(1);
        Assert.assertTrue( storeProduct != null);
    }

    @Test
    public void findByStoreIdAndProductIdIn() {
        List<StoreProduct> storeProducts = storeProductRepository.findByStoreIdAndProductId(2,Arrays.asList(1));
        Assert.assertTrue(storeProducts.size() > 0 );
    }

    @Test
    public void findByStoreIdAndProductInfo_IdIn() {
        List<StoreProduct> storeProducts = storeProductRepository.findByStoreIdAndProductInfo_IdIn(2,Arrays.asList(1));
        Assert.assertTrue(storeProducts.size() > 0 );
    }
}