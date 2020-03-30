package com.nysheng.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 类目表 product_category
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    public ProductCategory(){}
    public ProductCategory(String categoryName,Integer categoryType){
        this.categoryName=categoryName;
        this.categoryType=categoryType;
    }
    //类目ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目编号
    private Integer categoryType;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", category_name='" + categoryName + '\'' +
                ", category_type=" + categoryType +
                '}';
    }
}
