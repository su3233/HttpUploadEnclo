package com.sts.httpuploadenclo.task;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public enum RequestMethod {

    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    DELETE("DELETE");
    private String value;

    RequestMethod(String delete) {
        this.value = delete;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
