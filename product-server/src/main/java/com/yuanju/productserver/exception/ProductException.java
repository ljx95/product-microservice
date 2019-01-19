package com.yuanju.productserver.exception;

import com.yuanju.productserver.enums.ResultEnum;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 11:17
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;

    }
}