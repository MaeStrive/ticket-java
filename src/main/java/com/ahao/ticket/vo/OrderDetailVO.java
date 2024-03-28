package com.ahao.ticket.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2024/3/28
 * @Time: 22:19
 * @Description:
 */
@Data
public class OrderDetailVO {
    private Integer amount;
    private BigDecimal price;
    private String name;
    private Date showtime;
    private String address;
    private BigDecimal total;
}
