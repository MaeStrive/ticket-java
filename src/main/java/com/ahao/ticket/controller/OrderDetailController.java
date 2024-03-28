package com.ahao.ticket.controller;

import com.ahao.ticket.domain.OrderDetail;
import com.ahao.ticket.domain.Ticket;
import com.ahao.ticket.service.OrderDetailService;
import com.ahao.ticket.service.TicketService;
import com.ahao.ticket.utils.Result;
import com.ahao.ticket.vo.OrderDetailVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Mae
 * @Date: 2024/3/28
 * @Time: 22:13
 * @Description:
 */
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/getOrderDetail")
    public Result<?> getOrderDetail(Integer id) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId, id);
        OrderDetail one = orderDetailService.getOne(queryWrapper);
        System.out.println(one);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setPrice(one.getPrice());
        orderDetailVO.setAmount(one.getAmount());
        Ticket byId = ticketService.getById(one.getTicketId());
        orderDetailVO.setName(byId.getName());
        orderDetailVO.setAddress(byId.getAddress());
        orderDetailVO.setShowtime(byId.getShowtime());
        return Result.ok(orderDetailVO);
    }
}
