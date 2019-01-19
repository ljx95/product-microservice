package com.yuanju.productclient;

import com.yuanju.productcommon.DecreaseStockInput;
import com.yuanju.productcommon.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/13
 * Time: 1:51
 */
@FeignClient(name = "product-microservice")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<Integer> productIdList,@RequestParam("storeId") Integer storeId);

    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);
}
