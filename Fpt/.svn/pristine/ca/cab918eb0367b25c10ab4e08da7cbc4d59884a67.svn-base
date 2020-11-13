package com.weimi.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Result implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态
     */
    private String isSuccess;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据对象
     */
    private Object result;

    /**
     * 无参构造器
     */
    public Result() {
        super();
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param success
     * @param code
     * @param massege
     */
    public Result(String success, Integer code, String massege) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.message = massege;
    }

    /**
     * 只返回状态，状态码，数据对象
     *
     * @param success
     * @param code
     * @param result
     */
    public Result(String success, Integer code, Object result) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.result = result;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     *
     * @param statu
     * @param code
     * @param massege
     * @param result
     */
    /**
     * @param success
     * @param code
     * @param massege
     * @param result
     */
    public Result(String success, Integer code, String massege, Object result) {
        super();
        this.isSuccess = success;
        this.code = code;
        this.message = massege;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
  

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public <T> T getResult(Class<T> clazz) {
        String resultStr = JSONObject.toJSONString(result);
        return JSONObject.parseObject(resultStr, clazz);
    }
}
