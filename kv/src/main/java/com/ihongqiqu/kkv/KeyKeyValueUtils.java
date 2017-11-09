package com.ihongqiqu.kkv;

import android.text.TextUtils;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.ArrayList;
import java.util.List;

/**
 * KKV类型数据操作工具类
 * <p>
 * Created by zhenguo on 11/9/17.
 */
public class KeyKeyValueUtils {

    public static boolean delete() {
        return delete(null, null);
    }

    public static boolean delete(String key) {
        return delete(key, null);
    }

    public static boolean delete(String key, String value) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmQuery<KeyTypeValue> keyValueRealmQuery = realm.where(KeyTypeValue.class);
        if (!TextUtils.isEmpty(key)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("key", key);
        }
        if (!TextUtils.isEmpty(value)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("value", value);
        }
        RealmResults<KeyTypeValue> keyValues = keyValueRealmQuery.findAll();
        boolean isSuccess = keyValues.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
        return isSuccess;
    }

    public static boolean insertOrUpdate(String key, String value) {
        KeyTypeValue keyValue = new KeyTypeValue();
        keyValue.setId(key + value);
        keyValue.setKey(key);
        keyValue.setValue(value);
        keyValue.setTime(System.currentTimeMillis());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(keyValue);
        realm.commitTransaction();
        realm.close();
        return true;
    }

    public static List<KeyTypeValue> query() {
        return query(null);
    }

    public static List<KeyTypeValue> query(String key) {
        return query(key, null);
    }

    public static List<KeyTypeValue> query(String key, String value) {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<KeyTypeValue> keyValueRealmQuery = realm.where(KeyTypeValue.class);
        if (!TextUtils.isEmpty(key)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("key", key);
        }
        if (!TextUtils.isEmpty(value)) {
            keyValueRealmQuery = keyValueRealmQuery.equalTo("value", value);
        }
        RealmResults<KeyTypeValue> keyValues = keyValueRealmQuery.findAll().sort("time", Sort.DESCENDING);
        List<KeyTypeValue> keyValueList = keyValues.subList(0, keyValues.size());
        List<KeyTypeValue> destKeyValueList = new ArrayList<>();
        for (KeyTypeValue keyValue : keyValueList) {
            KeyTypeValue copyKV = new KeyTypeValue();
            copyKV.setKey(keyValue.getKey());
            copyKV.setValue(keyValue.getValue());
            destKeyValueList.add(copyKV);
        }
        realm.close();
        return destKeyValueList;
    }

}
