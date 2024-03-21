package com.ahao.ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ahao.ticket.domain.Category;
import com.ahao.ticket.service.CategoryService;
import com.ahao.ticket.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




