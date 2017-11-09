package com.ihognqiqu.kv;

import android.text.TextUtils;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

/**
 * kv工具类
 * <p>
 * Created by zhenguo on 11/9/17.
 */
public class KeyValueUtil {

    public static boolean delete() {
        return delete(null, null);
    }

    public static boolean delete(String key) {
        return delete(key, null);
    }

    public static boolean delete(String key, String value) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmQuery<KeyValue> keyValueRealmQuery = realm.where(KeyValue.class);
        if (!TextUtils.isEmpty(key)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("key", key);
        }
        if (!TextUtils.isEmpty(value)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("value", value);
        }
        RealmResults<KeyValue> keyValues = keyValueRealmQuery.findAll();
        boolean isSuccess = keyValues.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
        return isSuccess;
    }

    public static boolean insertOrUpdate(String key, String value) {
        KeyValue keyValue = new KeyValue();
        keyValue.setKey(key);
        keyValue.setValue(value);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(keyValue);
        realm.commitTransaction();
        realm.close();
        return true;
    }

    public static List<KeyValue> query() {
        return query(null);
    }

    public static List<KeyValue> query(String key) {
        return query(key, null);
    }

    public static List<KeyValue> query(String key, String value) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<KeyValue> keyValueRealmQuery = realm.where(KeyValue.class);
        if (!TextUtils.isEmpty(key)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("key", key);
        }
        if (!TextUtils.isEmpty(value)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("value", value);
        }
        RealmResults<KeyValue> keyValues = keyValueRealmQuery.findAll();
        List<KeyValue> keyValueList = keyValues.subList(0, keyValues.size());
        List<KeyValue> destKeyValueList = new ArrayList<>();
        for (KeyValue keyValue : keyValueList) {
            KeyValue copyKV = new KeyValue();
            copyKV.setKey(keyValue.getKey());
            copyKV.setValue(keyValue.getValue());
            destKeyValueList.add(copyKV);
        }
        realm.close();
        return destKeyValueList;
    }

}
