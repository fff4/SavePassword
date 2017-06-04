package com.example.administrator.savepassword.ui.presenter;

import android.app.Activity;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.savepassword.ui.itface.RegisterInterface;
import com.example.administrator.savepassword.ui.model.RegistModel;
import com.example.administrator.savepassword.ui.utils.SMSUtil;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 注册页面的p层--处理注册业务
 */

public class RegisterPresenter {
    private static final String TAG = "RegisterPresenter";

    private RegisterInterface.RegisterView mRegisterView;
    private RegisterInterface.RegisterModel mRegisterModel;
    private String mPhone;
    private String mPwd;

    public RegisterPresenter(RegisterInterface.RegisterView registerView) {
        mRegisterView = registerView;
        mRegisterModel = new RegistModel();
    }

    public void register() {

        mPwd = mRegisterView.geRegisterpwd();
        String phoneAuth = mRegisterView.getPhoneAuth();
        String phone = mRegisterView.getPhone();

        if (TextUtils.isEmpty(mPwd) || TextUtils.isEmpty(phone)) {
            mRegisterView.onRegisterError("账号或密码不能为空");
            return;
        }

        if (!phone.equals(mPhone)) {
            mRegisterView.onRegisterError("请进行短信验证");
            return;
        }

        //2.提交验证码
        if (SMSUtil.judgePhoneNums((Activity) mRegisterView, mPhone)) {
            SMSSDK.submitVerificationCode("86", mPhone, phoneAuth);
        }
    }

    public void sendSms() {
        mPhone = mRegisterView.getPhone();

        //1.获取验证码
        if (SMSUtil.judgePhoneNums((Activity) mRegisterView, mPhone)) {
            SMSSDK.getVerificationCode("86", mPhone);
            //2.开启子线程，倒计时
            new Thread(new CutdownTask()) {
            }.start();
        }
    }


    //回调完成
    //提交验证码成功
    //获取验证码成功
    //返回支持发送验证码的国家列表
    public EventHandler mEh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Log.d(TAG, "提交验证码成功");
                    mRegisterView.onRegisterError("提交验证码成功");
                    mRegisterModel.onRegist(mPhone, mPwd, mRegisterView);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.d(TAG, "获取验证码成功");
                    mRegisterView.onRegisterError("获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
//                Log.d(TAG, "验证失败");
                mRegisterView.onRegisterError("验证失败:"+data.toString());
            }
        }
    };

    private class CutdownTask implements Runnable {
        @Override
        public void run() {
            for (int time = 30; time > 0; time--) {
                SystemClock.sleep(999);
                String text = "剩余时间(" + time + ")秒";
                mRegisterView.onChangeUI(text, false);
            }
            mRegisterView.onChangeUI("发送短信验证码", true);
        }
    }
}
