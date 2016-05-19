package com.itao.exception;

/**业务异常
 * @author Created by Vicdor(linss) on 2016-05-20 01:05.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 6036471743206250120L;
    private int code;
    private String message;

    public BusinessException(Code code) {
        this.code = code.getCode();
        this.message = code.getMsg();
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }
}