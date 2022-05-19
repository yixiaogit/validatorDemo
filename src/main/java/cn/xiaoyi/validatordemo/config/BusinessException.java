package cn.xiaoyi.validatordemo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;
    public BusinessException(String message){
        this.message = message;
    }
}
