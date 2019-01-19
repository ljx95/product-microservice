package com.yuanju.productserver.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/12
 * Time: 22:24
 */
@Data
public class ProductForm {

    private int id;
    /**
     * 商品名称
     */
    private String productName;

    /** 商品颜色*/
    private List<String> productColors;
    private String productColor;

    /** 库存数量*/
    private int productSku;

    /** 库存状态*/
    private int skuStatus;

    /** 商品图片*/
    private String productPicture;

    /** 商品质量*/
    private String productQuality;

    /** 商品尺寸*/
    private List<String> productSizes;
    private String productSize;

    /** 商品重量*/
    private double productWeight;

    /** 商品卖点*/
    private String sellPoint;

    /** 商品特价*/
    private BigDecimal productRecommendprice;

    /** 商品销售价*/
    private BigDecimal productPrice;

    /** 商品描述*/
    private String productDefails;

    /** 限购数量*/
    private int limitNum;

    /** 商品类别编号*/
    private Integer sCategoryId;

    /** 仓库编号*/
    private Integer warehouseId;

    private Integer brandId;

    private Integer productStatus = 0 ;

    private Date startTime;

    private Date endTime;

    private int pageNum = 0;

    private int pageSize = 10;

}
