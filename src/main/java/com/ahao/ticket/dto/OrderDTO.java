package com.ahao.ticket.dto;

import com.ahao.ticket.domain.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    List<OrderDetail> details;
    private BigDecimal total;
    private Integer ticketId;
}
