package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情 DAO层接口
 *
 * @author nysheng
 * @date 2020/3/31 20:50
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrOrderId(String orderId);
}
