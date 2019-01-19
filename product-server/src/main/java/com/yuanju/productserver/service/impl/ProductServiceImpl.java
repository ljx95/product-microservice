package com.yuanju.productserver.service.impl;

import com.yuanju.productcommon.DecreaseStockInput;
import com.yuanju.productcommon.ProductInfoOutput;
import com.yuanju.productserver.dataobject.ProductInfo;
import com.yuanju.productserver.dto.CartDTO;
import com.yuanju.productserver.enums.ProductStatusEnum;
import com.yuanju.productserver.enums.ResultEnum;
import com.yuanju.productserver.exception.ProductException;
import com.yuanju.productserver.exception.SellException;
import com.yuanju.productserver.form.ProductForm;
import com.yuanju.productserver.repository.ProductInfoRepository;
import com.yuanju.productserver.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * author: LinjianXiong
 * Date: 2019/1/11
 * Time: 19:05
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public Page<ProductInfo> findByCondition(ProductForm productForm) {
        Pageable pageable = new PageRequest(productForm.getPageNum() - 1, productForm.getPageSize(), Sort.Direction.ASC, "id");
        Page<ProductInfo> page = productInfoRepository.findAll(getCondition(productForm), pageable);
        return page;
    }

    private Specification<ProductInfo> getCondition(ProductForm productForm){
        Specification<ProductInfo> sp = new Specification<ProductInfo>() {
            @Override
            public Predicate toPredicate(Root<ProductInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 创建 Predicate
                Predicate predicate = criteriaBuilder.conjunction();
                // 组装条件
                if(productForm.getId() != 0){
//                    predicate.add(cb.equal(root.get("phone"), vo.getPhone()));
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("id"), productForm.getId()));
                }
                if(StringUtils.isNotBlank(productForm.getProductName())){
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("productName"), "%" + productForm.getProductName() + "%"));
                }
                if(productForm.getProductStatus() != 0 ){
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("productStatus"), productForm.getProductStatus()));
                }
                if (productForm.getStartTime() != null && productForm.getStartTime().toString().length() > 0){
                    predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), productForm.getStartTime()));
                }
                if (productForm.getEndTime() != null && productForm.getEndTime().toString().length() > 0){
                    predicate.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), productForm.getEndTime()));
                }

                return predicate;
            }
        };
        return sp;
    }

    @Override
    public ProductInfo findOne(Integer productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo onSale(Integer productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);

        //商品不存在
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        // 商品已在架
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(Integer productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);

        //商品不存在
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        // 商品已在架
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public List<ProductInfoOutput> findList(List<Integer> productIdList) {
        return productInfoRepository.findByIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfo> findProduct(List<Integer> productIdList) {
        return productInfoRepository.findByIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList){
           Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());

           //判断商品是否存在
           if (!productInfoOptional.isPresent()){
               throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
           }

           ProductInfo productInfo = productInfoOptional.get();

           //判断库存是否足够
            Integer result = productInfo.getProductSku() - decreaseStockInput.getProductNum();
            if (result < 0){
                throw  new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductSku(result);
            productInfoRepository.save(productInfo);
        }
    }
}