package com.jiyun.wanandroid.entity.todo;

/**
 * Created by $sl on 2019/5/24 15:59.
 */
public class ToDoDeleteBean {
    /**
     * data : null
     * errorCode : 0
     * errorMsg :
     */

    private Object data;
    private int errorCode;
    private String errorMsg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
