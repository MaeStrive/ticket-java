package com.ahao.ticket.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName notice
 */
@TableName(value ="tic_notice")
@Data
public class Notice implements Serializable {
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告图片
     */
    private String img;

    /**
     * 发布的管理员id
     */
    private Integer managerId;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}