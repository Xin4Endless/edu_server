package com.xin.serverbase.handler.exceptionhandler;

import com.xin.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定异常类型
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("接口异常");
    }

    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public R error(MyRuntimeException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
        //return R.error().message("执行了'MyRuntimeException'异常");
    }

}
