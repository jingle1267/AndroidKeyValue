package com.ihognqiqu.kv;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Realm数据库初始化
 * <p>
 * 需要在application的onCreate方法中调用此类的init方法
 * <p>
 * Created by zhenguo on 11/9/17.
 */
public class RealmInit {

    public static void init(Context context) {
        Realm.init(context.getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("yycdq.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
