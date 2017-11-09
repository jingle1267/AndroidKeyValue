package com.ihognqiqu.kv;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Key Value数据结构
 * <p>
 * Created by zhenguo on 11/9/17.
 */
public class KeyValue extends RealmObject {

    @PrimaryKey
    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
