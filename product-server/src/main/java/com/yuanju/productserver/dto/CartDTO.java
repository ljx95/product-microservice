package com.yuanju.productserver.dto;

import lombok.Data;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 11:06
 */
@Data
public class CartDTO {

    private Integer productId;

    private int productNum;

    public CartDTO(){}

    public CartDTO(Integer productId, int productNum){
        this.productId = productId;
        this.productNum = productNum;
    }
}
