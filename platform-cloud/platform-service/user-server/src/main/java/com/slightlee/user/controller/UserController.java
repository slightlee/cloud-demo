package com.slightlee.user.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.google.gson.Gson;
import com.slightlee.base.annotation.ResponseResult;
import com.slightlee.order.api.entity.OrderInfo;
import com.slightlee.user.api.entity.User;
import com.slightlee.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    private final RestTemplate restTemplate;

    @ApiOperation(value = "用户列表")
    @ApiOperationSupport(order = 1,author = "明天")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.list();
    }


    @ApiOperation(value = "订单信息")
    @ApiOperationSupport(order = 2,author = "明天")
    @RequestMapping(value = "/getOrderInfoList",method = RequestMethod.GET)
        public List<OrderInfo> getOrderInfoList(@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId") Long userId){
        String obj = restTemplate.getForObject("http://order-server/order/getOrderInfobyId?userId=" + userId, String.class);

        // 基于 fastjson
//        JSONObject jsonObject = JSONObject.parseObject(obj);
//        List<OrderInfo> orderInfoList = (List<OrderInfo>)jsonObject.get("data");

        // 基于 gson
        Gson gson = new Gson();
        Result result = gson.fromJson(obj, Result.class);
        List<OrderInfo> orderInfoList = (List<OrderInfo>)result.getData();
        log.info("请求url为:{},结果: {}",null,orderInfoList);

        return orderInfoList;
    }

    @Data
    class Result<T>{
        private Integer code;
        private String msg;
        private T data;
    }

}

