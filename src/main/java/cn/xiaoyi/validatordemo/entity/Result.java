package cn.xiaoyi.validatordemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据交互公共类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    public Result(Integer code, String message){
        this(code,message,null);
    }
    public static Result success(){
        return new Result(200,"操作成功");
    }
    public static Result success(String message){
        return new Result(200,message);
    }
    public static Result success(String message,Object data){
        return new Result<>(200,message,data);
    }
    public static Result failure(){
        return new Result(400,"操作失败！");
    }
    public static Result failure(String message){
        return new Result(400,message);
    }
}
