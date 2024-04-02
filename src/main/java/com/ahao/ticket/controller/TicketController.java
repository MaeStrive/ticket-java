package com.ahao.ticket.controller;

import com.ahao.ticket.domain.Category;
import com.ahao.ticket.domain.Ticket;
import com.ahao.ticket.dto.TicketDTO;
import com.ahao.ticket.mapper.TicketMapper;
import com.ahao.ticket.service.CategoryService;
import com.ahao.ticket.service.TicketService;
import com.ahao.ticket.utils.Result;
import com.ahao.ticket.vo.ListTicketVO;
import com.ahao.ticket.vo.PriceVO;
import com.ahao.ticket.vo.TicketDetailVO;
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
    @Autowired
    private TicketMapper ticketMapper;

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

    /***
     * 根据地区获取产品
     */
    @GetMapping("/listTicketByDistinct")
    public Result<?> listTicketByDistinct(String distinct) {
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ticket::getDistrict, distinct);
        List<Ticket> list = ticketService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 获取所有的地区
     *
     * @return
     */
    @GetMapping("/listAllDisctinct")
    public Result<?> listAllDistinct() {
        List<Ticket> list = ticketMapper.listAllDistinct();
        return Result.ok(list);
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
    public Result<?> getTicketById(Integer id) {
        Ticket byId = ticketService.getById(id);
        String[] price = byId.getPrice().split(",");
        String[] count = byId.getCount().split(",");
        TicketDetailVO ticketDetailVO = new TicketDetailVO();
        List<PriceVO> priceVOList = new LinkedList<>();
        for (int i = 0; i < price.length; i++) {
            PriceVO priceVO = new PriceVO();
            priceVO.setCount(count[i]);
            priceVO.setPrice(price[i]);
            priceVOList.add(priceVO);
        }
        ticketDetailVO.setTicket(byId);
        ticketDetailVO.setPriceVOList(priceVOList);
        return Result.ok(ticketDetailVO);
    }

    @GetMapping("/listTicketByCondition")
    public Result<?> listTicketByCondition(TicketDTO ticketDTO) {
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Ticket> queryWrapper1 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Ticket> queryWrapper2 = new LambdaQueryWrapper<>();
        if (ticketDTO.getCategoryId() != 0) {
            queryWrapper.eq(Ticket::getCategoryId, ticketDTO.getCategoryId());
            queryWrapper1.eq(Ticket::getCategoryId, ticketDTO.getCategoryId());
            queryWrapper2.eq(Ticket::getCategoryId, ticketDTO.getCategoryId());
        }
        if (!ticketDTO.getDistrict().equals("全部")) {
            queryWrapper.eq(Ticket::getDistrict, ticketDTO.getDistrict());
            queryWrapper1.eq(Ticket::getDistrict, ticketDTO.getDistrict());
            queryWrapper2.eq(Ticket::getDistrict, ticketDTO.getDistrict());
        }
        ListTicketVO result = new ListTicketVO();
        List<Ticket> list = ticketService.list(queryWrapper);
        result.setList0(list);


        queryWrapper1.orderByDesc(Ticket::getShowtime);
        List<Ticket> list1 = ticketService.list(queryWrapper1);
        result.setList1(list1);

        queryWrapper2.orderByDesc(Ticket::getCreatetime);
        List<Ticket> list2 = ticketService.list(queryWrapper2);
        result.setList2(list2);

        return Result.ok(result);
    }

    @GetMapping("/listRecommend")
    public Result<?> listRecommend(Integer ticketId) {
        List<Ticket> list = ticketMapper.listYouMayLike(ticketId);
        return Result.ok(list);
    }

    @GetMapping("/listLike")
    public Result<?> listLike() {
        List<Ticket> list = ticketMapper.listYouMayLike00();
        return Result.ok(list);
    }
}
