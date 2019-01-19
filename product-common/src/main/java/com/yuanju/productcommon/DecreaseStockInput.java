package com.yuanju.productcommon;

import lombok.Data;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 11:56
 */
@Data
public class DecreaseStockInput {
    private Integer productId;

    private int productNum;

    public DecreaseStockInput(){}

    public DecreaseStockInput(Integer productId, int productNum){
        this.productId = productId;
        this.productNum = productNum;
    }
}
