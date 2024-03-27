package com.ahao.ticket.vo;

import com.ahao.ticket.domain.Ticket;
import lombok.Data;

import java.util.List;

/**
 * @Author: Mae
 * @Date: 2024/3/27
 * @Time: 19:51
 * @Description:
 */
@Data
public class TicketDetailVO {
    private Ticket ticket;
    List<PriceVO> priceVOList;
}
