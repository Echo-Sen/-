package cn.edu.ctbu.datajpa03.exception;

import cn.edu.ctbu.datajpa03.constain.REnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//捕获所有异常
    public REnum ex(Exception ex) {
        ex.printStackTrace();
        return REnum.UNKNOWN_ERR;
    }
}
