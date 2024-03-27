package com.ahao.ticket.controller;

import com.ahao.ticket.domain.Notice;
import com.ahao.ticket.service.NoticeService;
import com.ahao.ticket.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/listAllNormalNotices")
    public Result<?> listAllNormalNotices() {
        LambdaQueryWrapper<Notice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Notice::getStatus, 2);
        List<Notice> list = noticeService.list(queryWrapper);
        return Result.ok(list);
    }
}
