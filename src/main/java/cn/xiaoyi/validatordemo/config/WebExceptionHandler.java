package cn.xiaoyi.validatordemo.config;

import cn.xiaoyi.validatordemo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class WebExceptionHandler {

    /**
     * 请求资源不存在处理
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result NotFountHandler(NoHandlerFoundException exception){
        Result result = new Result(404,"访问路径不存在");
        log.info("NoHandlerFoundException------访问路径不存在");
        log.info(exception.getMessage());
        return result;
    }

    /**
     * 请求方式异常
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception){
        Result result = new Result(405,"请求方式不正确");
        log.info("HttpRequestMethodNotSupportedException-----请求方式不正确");
        log.info(exception.getMessage());
        return result;
    }


    /**
     * 业务异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result BusinessExceptionHandler(BusinessException exception){
        log.info("BusinessException-------业务异常");
        log.info(exception.getMessage());
        return new Result(400,exception.getMessage());
    }
    /**
     * 运行时异常，系统异常
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result RuntimeExceptionHandler(RuntimeException exception){
        Result result = new Result(500,"系统异常");
        log.info("RuntimeException-----系统运行异常");
        log.info(exception.getMessage());
        return result;
    }


    /**
     * RequestBody 上validate失败后抛出的异常是MethodArgumentNotValidException异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        Result result =new Result();
        String message = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.info("MethodArgumentNotValidException-----requestBody参数映射失败");
        log.info(exception.getMessage());
        result.setCode(400);
        result.setMessage(message);
        return result;
    }

    /**
     * 参数校验绑定异常
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException exception){
        Result result = new Result();
        String message = exception.getMessage().substring(exception.getMessage().indexOf(":")+2);
        result.setMessage(message);
        log.info("ConstraintViolationException-----参数映射失败");
        log.info(message);
        return new Result(400,message);
    }

    /**
     * Valid 验证路径中请求实体校验失败后抛出的异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result BindExceptionHandler(BindException exception){
        Result result = new Result();
        String message = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.info("BindException------参数校验错误");
        log.info(message);
        result.setCode(400);
        result.setMessage(message);
        return result;
    }
}
