package com.slightlee.user.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.slightlee.base.annotation.ResponseResult;
import com.slightlee.user.entity.User;
import com.slightlee.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @ApiOperation(value = "用户列表")
    @ApiOperationSupport(order = 1,author = "明天")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.list();
    }


    @ApiOperation(value = "订单信息")
    @ApiOperationSupport(order = 1,author = "明天")
    @RequestMapping(value = "/getOrderInfo",method = RequestMethod.GET)
    public Object getOrderInfo(@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam(value = "userId") Long userId){

        // 方式一、
        RestTemplate restTemplate = new RestTemplate();
        Object object = restTemplate.getForObject("http://127.0.0.1:8003/order/getOrderInfobyId?userId=" + userId, Object.class);
        log.info("方式1: {}",object.toString());

        // 方式二、
        Object objects = restTemplates.getForObject("http://127.0.0.1:8003/order/getOrderInfobyId?userId=" + userId, Object.class);
        log.info("方式2: {}",objects.toString());

        return object;

    }
}

