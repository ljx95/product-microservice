package com.yuanju.productserver.repository;

import com.yuanju.productserver.dataobject.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: LinjianXiong
 * Date: 2019/1/17
 * Time: 15:29
 */
@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Integer>,JpaSpecificationExecutor<StoreProduct> {
    StoreProduct findByStoreProductId(Integer id);

    @Query("SELECT a FROM StoreProduct a " + " left join a.productInfo u " + " WHERE a.storeId = :storeId AND a.id in :productId")
    List<StoreProduct> findByStoreIdAndProductId(@Param("storeId") Integer storeId, @Param("productId") List<Integer> productId);

    List<StoreProduct> findByStoreIdAndProductInfo_IdIn(Integer storeId, List<Integer> productIds);
}
