package com.yuanju.productserver.converter;

import com.google.gson.Gson;
import com.yuanju.productserver.dataobject.ProductInfo;
import com.yuanju.productserver.form.ProductForm;

/**
 * author: LinjianXiong
 * Date: 2019/1/15
 * Time: 23:51
 */
public class ProductForm2ProductInfoConverter {

    public static ProductInfo convert(ProductForm productForm){
        Gson gson = new Gson();
        ProductInfo info = new ProductInfo();
        info.setProductDefails(productForm.getProductDefails());
        return  info;
    }
}
