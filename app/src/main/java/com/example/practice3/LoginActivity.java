package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button b_login, b_register;
    EditText et_account, et_password;

    SharedPreferences pref;         // 宣告偏好設定
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b_login = findViewById(R.id.b_login);
        b_register = findViewById(R.id.b_register);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);

        pref = getSharedPreferences("ACCOUNT", MODE_PRIVATE);       // 選擇或宣告名為ACCOUNT的偏好設定
//        pref.edit().clear().apply();

        // 登入按鈕的按下事件
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_account = et_account.getText().toString();     // 輸入帳號
                String input_pwd = et_password.getText().toString();        // 輸入密碼

                //檢查帳號是否存在
                if(!pref.contains(input_account)){
                    Toast.makeText(LoginActivity.this, "帳號不存在", Toast.LENGTH_LONG).show();
                    return;
                }

                //密碼比對
                if(pref.getString(input_account, "").equals(input_pwd)){
                    Intent intent = new Intent(LoginActivity.this, DataViewActivity.class);     //宣告DataViewActivity的頁面
                    intent.putExtra("account", input_account);      //存入intent資料: "account"=input_account
                    startActivity(intent);      // 切換至DataViewActivity頁面
                }else {
                    Toast.makeText(LoginActivity.this, "密碼錯誤", Toast.LENGTH_LONG).show();
                }
            }
        });


        // 註冊按鈕的按下事件
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));  //切換至RegisterActivity頁面
            }
        });
    }
}
