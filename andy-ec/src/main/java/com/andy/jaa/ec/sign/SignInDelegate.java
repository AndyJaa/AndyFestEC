package com.andy.jaa.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.IError;
import com.andy.jaa.andyfec.net.callback.IFailure;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.utils.file.FileUtils;
import com.andy.jaa.andyfec.wechat.LatteWeChat;
import com.andy.jaa.andyfec.wechat.callbacks.IWeChatSignInCallback;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;
import com.andy.jaa.ec.database.DatabaseManager;
import com.andy.jaa.ec.database.UserProfile;
import com.andy.jaa.ec.database.UserProfileDao;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by quanxi on 2018/3/21.
 */

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText etEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText etPassWord;
    private ISignListener mISignListener;

    @OnClick(R2.id.btn_sign_in)
    void OnClickSignIn(){
        checkForm();
        //提交数据
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
//                    .param("name",etName.getText().toString())
//                    .param("address",etEmail.getText().toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        String key = etEmail.getText().toString();
                        String pass = etPassWord.getText().toString();
                        UserProfile uer = DatabaseManager.getInstance().getUserDao()
                                .queryBuilder()
                                .where(UserProfileDao.Properties.Address.eq(key))
                                .unique();
                        if (uer!=null) {
                            if (uer.getPassWord()!=null && pass!=null
                                    && uer.getPassWord().equalsIgnoreCase(pass)) {
                                response = JSONObject.toJSONString(uer);
                                SignHandler.onSignIn(response, mISignListener);
                            }else{
                                Toast.makeText(getContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(),"暂无此用户，请前往注册",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void failure() {
                        Log.e(SignUpDelegate.class.getSimpleName()+">>","failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void error(int code, String msg) {
                        Log.e(">>>>>>"+code,msg);
                    }
                })
                .build()
                .post();
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void OnClickWeChat(){
        LatteWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }
    @OnClick(R2.id.tv_link_sign_up)
    void OnClickLink(){
        getSupportDelegate().start(new SignUpDelegate());
    }

    boolean checkForm(){
        boolean isPass = true;
        String email = etEmail.getText().toString();
        String password = etPassWord.getText().toString();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("不是合法的邮箱地址");
            isPass = false;
        }else {
            etEmail.setError(null);
        }
        if (TextUtils.isEmpty(password) || password.length()<6){
            etPassWord.setError("密码不能少于6位");
            isPass = false;
        }else {
            etPassWord.setError(null);
        }

        return isPass;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {
        getSupportDelegate().post(runnable);
    }
}
