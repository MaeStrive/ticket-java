package com.ahao.ticket.service;

import com.ahao.ticket.domain.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TicketService extends IService<Ticket> {

    List<Ticket> listByCategoryId(Integer categoryId);
}
