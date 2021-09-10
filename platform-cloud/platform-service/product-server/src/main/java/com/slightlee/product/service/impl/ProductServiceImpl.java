package com.slightlee.product.service.impl;

import com.slightlee.product.api.entity.Product;
import com.slightlee.product.mapper.ProductMapper;
import com.slightlee.product.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
