package com.yuanju.productserver.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 13:52
 */
@Data
@Entity
@Table(name = "tb_s_p")
public class StoreProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeProductId;

    //销售商价格
    private BigDecimal storePrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductInfo productInfo;

    private Integer storeId;
}
