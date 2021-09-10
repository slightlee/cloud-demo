package com.slightlee.user.service.impl;

import com.slightlee.user.api.entity.User;
import com.slightlee.user.mapper.UserMapper;
import com.slightlee.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 明天
 * @since 2021-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
