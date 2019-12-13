package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Dictionary;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    EditText et_reg_account, et_reg_pwd1, et_reg_pwd2;
    Button b_reg_confirm;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pref = getSharedPreferences("ACCOUNT", MODE_PRIVATE);


        et_reg_account = findViewById(R.id.et_reg_account);
        et_reg_pwd1 = findViewById(R.id.et_reg_pwd1);
        et_reg_pwd2 = findViewById(R.id.et_reg_pwd2);

        b_reg_confirm = findViewById(R.id.b_reg_confirm);

        // 確認按鈕按下事件
        b_reg_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_reg_account.getText().toString();          // 輸入帳號
                String pwd1 = et_reg_pwd1.getText().toString();                 // 輸入密碼
                String pwd2 = et_reg_pwd2.getText().toString();                 // 輸入確認密碼
                String password;

                //檢查有無空值
                if (account.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "任一輸入方框不得為空值", Toast.LENGTH_LONG).show();
                    return;
                }

                //檢查密碼是否一致
                if (!pwd1.equals(pwd2)) {
                    Toast.makeText(RegisterActivity.this, "輸入密碼不一致", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    password = pwd1;
                }

                //檢查帳號是否存在
                if (pref.contains(account)){
                    Toast.makeText(RegisterActivity.this, "使用者已存在", Toast.LENGTH_LONG).show();
                    return;
                }


                //新增使用者
                pref.edit()
                        .putString(account, password)
                        .apply();


                Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_LONG).show();
                finish();

                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));  // 生命週期範例
                // 筆記:
                // 按下BACK按鈕或調用finish()方法離開應用程式: OnPause() -> OnStop() -> onDestroy().
                // 再次進入應用程式會調用: OnCreate() -> OnStart() -> OnResume().
                //  Ref1: http://androidbiancheng.blogspot.com/2010/07/androidlife-cycle.html
                //  Ref2: https://litotom.com/2017/05/25/ch5-4-activity-lifecycle/
            }
        });
    }
}
