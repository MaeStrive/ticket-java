package com.ahao.ticket.dto;

import lombok.Data;

/**
 * @Author: Mae
 * @Date: 2024/3/30
 * @Time: 13:46
 * @Description:
 */
@Data
public class PasswordDTO {
    private String oldpassword;
    private String newpassword;
    private Integer id;
}
