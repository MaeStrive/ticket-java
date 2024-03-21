package com.ahao.ticket.utils;

import lombok.Data;

/**
 * @Author: Mae
 * @Date: 2024/3/21
 * @Time: 21:33
 * @Description:
 */
@Data
public class Result<T> {
    private String msg;
    private T data;
    private Integer code=0;

    public Result(T data) {
        this.data = data;
    }
    public Result(){

    }
    public Result(String msg,T data){
        this.msg=msg;
        this.data=data;
    }

    public Result(String msg,T data,Integer code){
        this.msg=msg;
        this.data=data;
        this.code=code;
    }

    public Result(Integer code){
        this.code=code;
    }

    public static<T> Result<T> ok(T data) {
        Result<T> r = new Result<T>();
        r.setCode(0);
        r.setData(data);
        return r;
    }
    public static<T> Result<T> ok(T data,String msg) {
        Result<T> r = new Result<T>();
        r.setCode(0);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static<T> Result<T> ok(String msg) {
        Result<T> r = new Result<T>();
        r.setCode(0);
        r.setMsg(msg);
        return r;
    }

    public static<T> Result<T> ok() {
        Result<T> r = new Result<T>();
        r.setCode(0);
        return r;
    }

    public static<T> Result<T> error(String msg) {
        Result<T> r = new Result<T>();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }

    public static<T> Result<T> error(String msg,Integer code) {
        Result<T> r = new Result<T>();
        r.setMsg(msg);
        r.setCode(code);
        return r;
    }
    public static<T> Result<T> error(Integer code) {
        Result<T> r = new Result<T>();
        r.setCode(code);
        return r;
    }
}
