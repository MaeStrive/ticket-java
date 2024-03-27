package com.ahao.ticket.controller;

import com.ahao.ticket.domain.CommonUser;
import com.ahao.ticket.domain.OrderDetail;
import com.ahao.ticket.domain.OrderInfo;
import com.ahao.ticket.domain.Ticket;
import com.ahao.ticket.dto.OrderDTO;
import com.ahao.ticket.service.OrderDetailService;
import com.ahao.ticket.service.OrderInfoService;
import com.ahao.ticket.service.TicketService;
import com.ahao.ticket.utils.RequestUtil;
import com.ahao.ticket.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.util.IdUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private TicketService ticketService;

    /**
     * 保存订单
     *
     * @param orderDTO
     * @return
     */
    @PostMapping("/saveOrder")
    @Transactional
    public Result<?> saveOder(@RequestBody OrderDTO orderDTO) {
        //再次判断查询票的数量是否够？ 不够我不卖
        Ticket ticket = ticketService.getById(orderDTO.getTicketId());
        String[] priceArr = ticket.getPrice().split(",");
        String[] countArr = ticket.getCount().split(",");
        for (int i = 0; i < priceArr.length; i++) {
            if (orderDTO.getPrice().equals(new BigDecimal(priceArr[i]))) {
                if (orderDTO.getAmount() > Integer.parseInt(countArr[i])) {
                    return Result.error("票不够了！");
                } else {
                    countArr[i] = String.valueOf(Integer.parseInt(countArr[i]) - orderDTO.getAmount());
                }
            }

        }
        //保存订单
        String s = IdUtil.getSnowflake().nextIdStr();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCode(s);
        orderInfo.setStatus("待支付");
        orderInfo.setCreateTime(new Date());
        orderInfo.setUserId(orderDTO.getUserId());
        orderInfo.setPrice(orderDTO.getTotal());
        orderInfoService.save(orderInfo);
        //保存订单项
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUserId(orderDTO.getUserId());
        orderDetail.setPrice(orderDTO.getPrice());
        orderDetail.setAmount(orderDTO.getAmount());
        orderDetail.setTicketId(orderDTO.getTicketId());
        orderDetail.setOrderId(orderInfo.getId());
        orderDetailService.save(orderDetail);
        //将票的数量减了
        //更新票的数量
        String countNew = String.join(",", countArr);
        LambdaUpdateWrapper<Ticket> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Ticket::getId, orderDTO.getTicketId()).set(Ticket::getCount, countNew);
        ticketService.update(updateWrapper);
        return Result.ok();
    }

    /**
     * 查询当前用户的所有订单
     *
     * @return
     */
    @GetMapping("/listOrders")
    public Result<?> listOrders() {
        CommonUser current = RequestUtil.getCurrent();
        if (current == null) return Result.error("请先登录!");
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getUserId, current.getId());
        List<OrderInfo> list = orderInfoService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 根据订单id查询所有订单项
     *
     * @param id
     * @return
     */
    @GetMapping("/listOrderDetailByOrderId")
    public Result<?> listOrderDetailByOrderId(Integer id) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId, id);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 修改支付状态
     *
     * @param orderInfo
     * @return
     */
    @PostMapping("/updateOrderStatus")
    public Result<?> updateOrderStatus(@RequestBody OrderInfo orderInfo) {
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getId, orderInfo.getStatus()).set(OrderInfo::getStatus, orderInfo.getStatus());
        return Result.ok();
    }

    /**
     * 根据状态查询订单
     *
     * @param status
     * @return
     */
    @GetMapping("/listOrderByStatus")
    public Result<?> listOrderByStatus(String status) {
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getStatus, status);
        List<OrderInfo> list = orderInfoService.list(queryWrapper);
        return Result.ok(list);
    }
}
