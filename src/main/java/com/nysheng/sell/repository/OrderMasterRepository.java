package com.nysheng.sell.repository;

import com.nysheng.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单 DAO层接口
 *
 * @author nysheng
 * 2020/3/31 20:55
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}
