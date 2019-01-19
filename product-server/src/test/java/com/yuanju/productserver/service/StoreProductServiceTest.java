package com.yuanju.productserver.service;

import com.yuanju.productcommon.ProductInfoOutput;
import com.yuanju.productserver.ProductServerApplicationTests;
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
 * Time: 19:48
 */
@Component
public class StoreProductServiceTest extends ProductServerApplicationTests {

    @Autowired
    private StoreProductService storeProductService;

    @Test
    public void findList() {
       List<ProductInfoOutput> productInfoOutputList = storeProductService.findList(2, Arrays.asList(1));
        Assert.assertTrue(productInfoOutputList.size() > 0);
    }
}