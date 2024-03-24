package com.ahao.ticket.controller;

import com.ahao.ticket.domain.Category;
import com.ahao.ticket.service.CategoryService;
import com.ahao.ticket.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Mae
 * @Date: 2024/3/24
 * @Time: 16:55
 * @Description:
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public Result<?> listAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSequence);
        List<Category> list = categoryService.list(queryWrapper);
        return Result.ok(list);
    }
}
