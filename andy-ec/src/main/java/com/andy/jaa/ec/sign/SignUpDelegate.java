package com.andy.jaa.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by quanxi on 2018/3/20.
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText etName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText etEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText etPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText etPassWord;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText etRePassWord;
    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){
            //提交数据
//            RestClient.builder()
//                    .url("signUp")
//                    .param("","")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    })
//                    .build()
//                    .post();
            Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
        }
    }

    boolean checkForm(){
        boolean isPass = true;
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPassWord.getText().toString();
        String repass = etRePassWord.getText().toString();

        if (TextUtils.isEmpty(name)){
            etName.setError("姓名不能为空");
            isPass = false;
        }else{
            etName.setError(null);
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("不是合法的邮箱地址");
            isPass = false;
        }else {
            etEmail.setError(null);
        }
        if (TextUtils.isEmpty(phone) || phone.length()!=11){
            etPhone.setError("请输入正确的手机号码");
            isPass = false;
        }else {
            etPhone.setError(null);
        }
        if (TextUtils.isEmpty(password) || password.length()<6){
            etPassWord.setError("密码不能少于6位");
            isPass = false;
        }else {
            etPassWord.setError(null);
        }
        if (TextUtils.isEmpty(repass) || repass.length()<6 || !repass.equalsIgnoreCase(password)){
            etRePassWord.setError("密码不一致");
            isPass = false;
        }else {
            etRePassWord.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
