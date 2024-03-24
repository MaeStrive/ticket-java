package com.ahao.ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ahao.ticket.domain.Ticket;
import com.ahao.ticket.service.TicketService;
import com.ahao.ticket.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
        implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<Ticket> listByCategoryId(Integer categoryId) {
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ticket::getCategoryId, categoryId).orderByAsc(Ticket::getShowtime);
        return ticketMapper.selectList(queryWrapper);
    }
}




