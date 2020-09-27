package com.bookstore.work.config;

/**
 * 公用返回对象
 * Created by zhaokui on 2017/8/10.
 */
public class ObjectVo {

    int code;
    String message;
    Object data;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

	public ObjectVo code(int code) {
        this.setCode(code);
        return this;
    }

    public ObjectVo message(String message) {
        this.setMessage(message);
        return this;
    }

    public ObjectVo data(Object data) {
        this.setData(data);
        return this;
    }

    public ObjectVo ok() {
        return ok(null, null);
    }

    public ObjectVo ok(Object data) {
        return ok(null, data);
    }

    public ObjectVo ok(String message) {
       return ok(message, null);
    }

    public ObjectVo ok(String message, Object data) {
        this.setCode(200);
        this.setMessage(message);
        this.setData(data);
        return this;
    }

    public ObjectVo error() {
       return error(500, "未知异常，请联系管理员");
    }

    public ObjectVo error(String message) {
        return error(500, message);
    }

    public ObjectVo error(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
        return this;
    }
    /**
     * lpf
     * 自己定制异常
     * @param code
     * @param message
     * @param setData
     * @return
     */
    public ObjectVo customize(int code, String message,Object setData) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(setData);
        return this;
    }
    
}
