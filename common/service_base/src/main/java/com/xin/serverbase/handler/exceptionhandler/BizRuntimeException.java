package com.xin.serverbase.handler.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //有参数构造函数
@NoArgsConstructor //无参数构造函数
public class BizRuntimeException extends java.lang.RuntimeException {


    private Integer code;
    private String msg;

}
