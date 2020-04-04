package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * 类目 DAO层接口
 *
 * @author nysheng
 * 2020/3/30 17:10
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
