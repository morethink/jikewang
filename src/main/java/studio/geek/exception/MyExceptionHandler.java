package studio.geek.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studio.geek.util.Result;

/**
 * @author 李文浩
 * @version 2017/2/15.
 */

@RestControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(ErrorException.class)
    public Result handleErrorException(ErrorException e) {
        return new Result(true, e.getError());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return new Result(true, "异常未处理");
    }

    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(Exception e) {
        e.printStackTrace();
        return new Result(true, "空指针异常，前端检查传值，后端检查代码");
    }
}