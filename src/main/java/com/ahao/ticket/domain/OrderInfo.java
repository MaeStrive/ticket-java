package com.ahao.ticket.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName order_info
 */
@TableName(value ="tic_order_info")
@Data
public class OrderInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单流水号
     */
    private String code;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 关联用户id
     */
    private Integer userId;

    /**
     * 总价格
     */
    private BigDecimal price;

    /**
     * 订单状态（1：待支付；2：已支付；3：已取消）
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}