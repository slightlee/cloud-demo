package com.slightlee.user.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.slightlee.base.annotation.ResponseResult;
import com.slightlee.order.api.entity.OrderInfo;
import com.slightlee.user.api.entity.User;
import com.slightlee.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 明天
 * @since 2021-09-10
 */
@Api(value = "用户接口", tags = "用户接口" )
@ApiSupport(order = 10,author = "明天")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ResponseResult
@Slf4j
public class UserController {

    private final UserService userService;

    private final RestTemplate restTemplates;

    private final DiscoveryClient discoveryClient;


    @ApiOperation(value = "用户列表")
    @ApiOperationSupport(order = 1,author = "明天")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.list();
    }


    @ApiOperation(value = "订单信息")
    @ApiOperationSupport(order = 1,author = "明天")
    @RequestMapping(value = "/getOrderInfoList",method = RequestMethod.GET)
    public List<OrderInfo> getOrderInfoList(@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId") Long userId){

        List<ServiceInstance> instances = discoveryClient.getInstances("order-server");
        List<String> urls = instances.stream()
                .map(serviceInstance -> serviceInstance.getUri().toString() + "/order/getOrderInfobyId?userId=" + userId)
                .collect(Collectors.toList());

        int i = ThreadLocalRandom.current().nextInt(urls.size());
        Map map = restTemplates.getForObject(urls.get(i),Map.class);
        List<OrderInfo> list = (List<OrderInfo>) map.get("data");
        log.info("请求url为:{},结果: {}",urls.get(i),list.toString());
        return list;
    }


}

