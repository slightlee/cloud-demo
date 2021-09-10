package com.slightlee.order.service.impl;

import com.slightlee.order.api.entity.OrderInfo;
import com.slightlee.order.mapper.OrderMapper;
import com.slightlee.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单快照表 服务实现类
 * </p>
 *
 * @author 明天
 * @since 2021-09-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

}
