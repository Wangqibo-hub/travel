package com.itheima.travel.exception;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-05-29 19:10
 */
public class CustomerErrorMsgException extends Exception {

    //空参构造方法
    public CustomerErrorMsgException() {
    }

    //带参数构造方法
    public CustomerErrorMsgException(String message) {
        super(message);
    }
}
