package com.kczd.design_android;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kczd.core.ActionCallbackListener;

/**
 * 登录页面
 */
public class LoginActivity extends KBaseActivity implements View.OnClickListener{

    private EditText phoneEdit;
    private EditText passwordEdit;
    private ImageView loginBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        phoneEdit=bindView(R.id.et_phone);
        passwordEdit=bindView(R.id.et_passwd);
        loginBtn=bindView(R.id.btn_login);
        loginBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                toLogin();
                break;
            case R.id.btn_register:
                toRegister();
                break;
        }
    }


    // 准备登录
    public void toLogin() {
        String loginName = phoneEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        loginBtn.setEnabled(false);
        this.appAction.login(loginName, password, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Toast.makeText(context, R.string.toast_login_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                loginBtn.setEnabled(true);
            }
        });
    }

    // 进入注册页
    public void toRegister() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
