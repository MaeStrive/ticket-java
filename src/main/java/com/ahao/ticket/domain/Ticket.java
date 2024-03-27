package com.ahao.ticket.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName ticket
 */
@TableName(value = "tic_ticket")
@Data
public class Ticket implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 演出票名称
     */
    private String name;

    /**
     * 价格（多档价格之间用，分割）
     */
    private String price;

    /**
     * 演出时间
     */
    private Date showtime;

    /**
     * 演出地点
     */
    private String address;

    /**
     *
     */
    private Integer status;

    /**
     * 分类关联id
     */
    private Integer categoryId;

    /**
     * 演示图片地址
     */
    private String imgPath;

    /**
     * 单笔订单限购数量
     */
    private Integer orderLimit;

    private String district;

    private String detail;

    /**
     *
     * 票的数量
     */
    private String count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}