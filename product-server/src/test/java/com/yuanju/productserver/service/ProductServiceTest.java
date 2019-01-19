package com.yuanju.productserver.service;

import com.yuanju.productcommon.ProductInfoOutput;
import com.yuanju.productserver.ProductServerApplicationTests;
import com.yuanju.productserver.dataobject.ProductInfo;
import com.yuanju.productserver.dto.CartDTO;
import com.yuanju.productserver.form.ProductForm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:12
 */
@Component
public class ProductServiceTest extends ProductServerApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductDefails("商品描述");
        productService.save(productInfo);

        assert productInfo.getId() > 0 : "create error";
    }

    @Test
    public void findByCondition() {
        ProductForm productForm = new ProductForm();
        productForm.setProductName("IPhon");
        //productForm.setProductStatus(1);
        productForm.setPageNum(1);
        productForm.setPageSize(10);
        Page<ProductInfo> list = productService.findByCondition(productForm);
        Assert.assertTrue(((Page) list).getSize() > 0);
    }

    @Test
    public void decreaseStock() throws Exception{
  /*      CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(1);
        cartDTO.setProductNum(2);
        productService.decreaseStock(Arrays.asList(cartDTO));*/
    }

    @Test
    public void findProduct() {
        List<ProductInfo> list = productService.findProduct(Arrays.asList(1));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList() {
        List<ProductInfoOutput> list = productService.findList(Arrays.asList(1));
        Assert.assertTrue(list.size() > 0);
    }
}