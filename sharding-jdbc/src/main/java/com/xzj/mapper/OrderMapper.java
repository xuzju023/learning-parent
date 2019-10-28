package com.xzj.mapper;


import com.xzj.model.Order;
import com.xzj.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @date 2019/10/14 15:03
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Mapper
public interface OrderMapper {
    void insert(@Param("order") Order order);

    List<Order> selectOrder(@Param("orderId") int orderId);

    List<OrderItem> selectOrderItem(@Param("orderId") int orderId);
}
