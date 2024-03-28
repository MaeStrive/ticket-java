package com.ahao.ticket.mapper;

import com.ahao.ticket.domain.Ticket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Entity com.ahao.ticket.domain.Ticket
 */
public interface TicketMapper extends BaseMapper<Ticket> {

    List<Ticket> listAllDistinct();

    List<Ticket> listYouMayLike(@Param("ticketId")Integer ticketId);
}




