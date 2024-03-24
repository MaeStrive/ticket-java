package com.ahao.ticket.controller;

import com.ahao.ticket.domain.Category;
import com.ahao.ticket.domain.Ticket;
import com.ahao.ticket.service.CategoryService;
import com.ahao.ticket.service.TicketService;
import com.ahao.ticket.utils.Result;
import com.ahao.ticket.vo.TicketIndexVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Mae
 * @Date: 2024/3/24
 * @Time: 16:51
 * @Description:
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类下的产品
     *
     * @return
     */
    @GetMapping("/listTicketByCategory")
    public Result<?> listTicketIndex(Integer categoryId) {
        List<Ticket> tickets = ticketService.listByCategoryId(categoryId);
        return Result.ok(tickets);
    }

    /**
     * 首页的三个分类
     *
     * @return
     */
    @GetMapping("/listTicketIndex")
    public Result<?> listTicketIndex() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSequence).last("limit 3");
        List<Category> list = categoryService.list(queryWrapper);
        List<TicketIndexVO> ticketIndexVOS = new LinkedList<>();
        for (Category category : list) {
            TicketIndexVO ticketIndexVO = new TicketIndexVO();
            ticketIndexVO.setId(category.getId());
            ticketIndexVO.setTitle(category.getName());
            //查询分类下的
            List<Ticket> tickets = ticketService.listByCategoryId(category.getId());
            //第一个左侧 剩下的右侧
            List<Ticket> list1 = new LinkedList<>();
            if (!tickets.isEmpty()) {
                ticketIndexVO.setLeftTicket(tickets.get(0));
                for (int i = 1; i < tickets.size(); i++) {
                    list1.add(tickets.get(i));
                }
            }
            ticketIndexVO.setRightList(list1);
            ticketIndexVOS.add(ticketIndexVO);
        }
        return Result.ok(ticketIndexVOS);
    }

    @GetMapping("/getTicketById")
    public Result<?> getTicketById(Integer id){
        Ticket byId = ticketService.getById(id);
        return Result.ok(byId);
    }
}
