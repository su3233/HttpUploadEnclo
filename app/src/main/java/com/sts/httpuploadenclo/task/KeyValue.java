package com.sts.httpuploadenclo.task;

import java.io.File;

/**
 * @author SuTs
 * @create 2019/9/9
 * @Describe
 */
public class KeyValue {
    private String key;

    private Object value;

    public KeyValue(String ket, String value) {
        this.key = ket;
        this.value = value;
    }

    public KeyValue(String ket, File value) {
        this.key = ket;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
