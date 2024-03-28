package com.ahao.ticket.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Integer categoryId;//0-全部
    private String district;//全部-全部
    private Integer type;//0-推荐顺序 id  1-表演顺序 showtime 2-上架时间 createtime
}
