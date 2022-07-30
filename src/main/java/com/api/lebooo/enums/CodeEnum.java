package com.api.lebooo.enums;

/**
 * @Date 2020/7/18 10:17
 */
public enum CodeEnum {

    /**
     * 返回码
     */
    SUCCESS(0,"SUCCESS"),
    ERROR(-1,"ERROR"),
    SUCCESS_NOT_LOGIN(1001,"NOT_LOGIN"),
    ERROR_REQUEST(400,"ERROR_REQUEST"),
    SUCCESS_NOT_DATE(101,"NOT_DATE"),//缺少参数
    SUCCESS_NO_FOUND(404,"NO_FOUND"),//请求失败，资源未找到
    SUCCESS_NO_RANGE(403,"NO_RANGE")//请求失败，未授权
    ;

    private Integer code;
    private String msg;
    private CodeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(String code){
        for (CodeEnum codeEnum : CodeEnum.values()){
            if (codeEnum.getCode().equals(code)){
                return codeEnum.msg;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
