package com.ihognqiqu.androidkeyvalue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ihognqiqu.kv.KeyValue;
import com.ihognqiqu.kv.KeyValueUtil;
import io.realm.Realm;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button btnAdd;
    protected Button btnQuery;
    protected Button btnDelete;
    protected TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

        Realm.init(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            String value = "value" + new Random(100).nextInt();
            KeyValueUtil.insertOrUpdate("key1", value);
        } else if (view.getId() == R.id.btn_query) {
            List<KeyValue> list =  KeyValueUtil.query("key1");
            if (list != null && list.size() > 0) {
                tvResult.setText(list.get(0).getValue());
            } else {
                tvResult.setText("");
            }
        } else if (view.getId() == R.id.btn_delete) {
            KeyValueUtil.delete("key1");
        }
    }

    private void initView() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(MainActivity.this);
        btnQuery = (Button) findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(MainActivity.this);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(MainActivity.this);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }
}
