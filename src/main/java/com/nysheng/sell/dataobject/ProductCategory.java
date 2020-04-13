package com.nysheng.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;

/**
 * 类目表 product_category
 *
 * @author nysheng
 * 2020/3/30 16:51
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    //类目ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目编号
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    public ProductCategory(){}
    public ProductCategory(String categoryName,Integer categoryType){
        this.categoryName=categoryName;
        this.categoryType=categoryType;
    }
    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", category_name='" + categoryName + '\'' +
                ", category_type=" + categoryType +
                '}';
    }
}
