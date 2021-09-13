package com.slightlee.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.slightlee.base.annotation.ResponseResult;
import com.slightlee.order.api.entity.OrderInfo;
import com.slightlee.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 订单快照表 前端控制器
 * </p>
 *
 * @author 明天
 * @since 2021-09-10
 */
@Api(value = "订单接口", tags = "订单接口" )
@ApiSupport(order = 10,author = "明天")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ResponseResult
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "订单列表")
    @ApiOperationSupport(order = 1,author = "明天")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<OrderInfo> list(){
        return orderService.list();
    }

    @ApiOperation(value = "用户订单信息列表")
    @ApiOperationSupport(order = 2,author = "明天")
    @RequestMapping(value = "/getOrderInfobyId",method = RequestMethod.GET)
    public List<OrderInfo> getOrderInfobyId(@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId") Long userId){
        return orderService.list(new LambdaQueryWrapper<OrderInfo>().eq(OrderInfo::getUserId,userId));
    }


}

