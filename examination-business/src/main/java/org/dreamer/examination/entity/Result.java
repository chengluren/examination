package org.dreamer.examination.entity;

/**
 * Created by lcheng on 2014/4/17.
 */
public class Result {

    private String message;
    private boolean success;

    public Result(boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
