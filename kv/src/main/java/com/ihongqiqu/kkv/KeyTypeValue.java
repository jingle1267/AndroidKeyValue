package com.ihongqiqu.kkv;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * kkkv数据库
 * <p>
 * 可以key相同value不同
 * <p>
 * Created by zhenguo on 11/9/17.
 */
public class KeyTypeValue extends RealmObject {

    @PrimaryKey
    private String id;

    private String key;

    private String value;

    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
