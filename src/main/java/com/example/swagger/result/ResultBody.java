package com.example.swagger.result;

/**
 * author： Created by shiming on 2018/9/27 09:43
 * mailbox：lamshiming@sina.com
 * des : 返回的封装类
 * IllegalArgumentException ： 不合法的参数异常。No converter found for return value of type： 找不到类型返回值的转换器
 *遇到这种错误可以考虑是没有提供getXXX方法
 * 这个类一定要提供 getXXX的方法
 */

public class ResultBody {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return  result;
    }
    //返回的关键的地，是改动这里
    public void setObject(Object result) {
        this.result = result;
    }

    private String code;

    private String message;

    private Object result;

    /**
     * 错误的返回
     * @param errorInfoInterface
     */
    public ResultBody(ErrorInfoInterface errorInfoInterface) {
        this.code = errorInfoInterface.getCode();
        this.message=errorInfoInterface.getMessage();
    }

    public ResultBody(Object result) {
        this.code=GlobalErrorInfoEnum.SUCCESS.getCode();
        this.message=GlobalErrorInfoEnum.SUCCESS.getMessage();
        this.result=result;
    }
}
