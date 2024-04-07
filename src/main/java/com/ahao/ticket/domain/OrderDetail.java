package com.ahao.ticket.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName order_detail
 */
@TableName(value ="tic_order_detail")
@Data
public class OrderDetail implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户关联id
     */
    private Integer userId;

    /**
     * 订单关联id
     */
    private Integer orderId;

    /**
     * 演出关联id
     */
    private Integer ticketId;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 单价
     */
    private BigDecimal price;

    private Integer flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}