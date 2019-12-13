package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DataViewActivity extends AppCompatActivity {
    TextView tv_welcome;
    ListView lv_account;

    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        tv_welcome = findViewById(R.id.tv_welcome);
        lv_account = findViewById(R.id.lv_account);

        Intent intent = getIntent();        //取得intent資料
        tv_welcome.setText("Hi, " + intent.getStringExtra("account") + "!");        //顯示intent資料 "account"，並做字串處理

        pref = getSharedPreferences("ACCOUNT", MODE_PRIVATE);       //存取名為ACCOUNT的偏好設定
        Map<String, ?> allEntries = pref.getAll();
        Object[] str =allEntries.keySet().toArray();    //Map => Object陣列


        ArrayAdapter<Object> adapter =  new ArrayAdapter<>(this,    // 宣告陣列調適器
                android.R.layout.simple_list_item_1,        // layout樣式
                str);   // 資料

        lv_account.setAdapter(adapter);     // 顯示
    }
}
