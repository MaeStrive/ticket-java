package com.ahao.ticket.vo;

import com.ahao.ticket.domain.Ticket;
import lombok.Data;

import java.util.List;

/**
 * @Author: Mae
 * @Date: 2024/3/24
 * @Time: 18:26
 * @Description:
 */
@Data
public class TicketIndexVO {
    private Integer id;
    private String title;
    private List<Ticket> rightList;
    private Ticket leftTicket;
}
