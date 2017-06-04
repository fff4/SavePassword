package com.example.administrator.savepassword.ui.ui.weiget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.savepassword.R;
import com.example.administrator.savepassword.ui.bean.Password;

import butterknife.BindView;


/**
 * 自定义新增数据的dialog
 */

public class PasswordDialog extends Dialog {
    private static final String TAG = "PasswordDialog";

    @BindView(R.id.tv_toolbar)
    TextView mTvToolbar;
    @BindView(R.id.btnRight)
    Button mBtnRight;
    @BindView(R.id.id_tool_bar)
    Toolbar mIdToolBar;

    TextInputEditText mEtPwdTitle;
    TextInputEditText mEtPwdUsername;
    TextInputEditText mEtPwdUserpwd;
    TextInputEditText mEtPwdUseremail;
    TextInputEditText mEtPwdUserephone;
    private BtnClick mBtnClick;
    private boolean isAdd = true;
    private int mUser_id;

    public PasswordDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.view_add_password);
        setDialogLayout();
        initView();
    }

    /**
     * 设置dialog的布局参数，全屏和动画
     */
    private void setDialogLayout() {
        //设置dialog的宽高
        //获得dialog的window窗口
        Window window = getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.CENTER);

//        Display display = getWindowManager().getDefaultDisplay();

        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
//        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp
                = window.getAttributes();
//        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        //设置窗口高度为包裹内容
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.y = 20;//设置Dialog距离底部的距离
//        lp.width = (int) (display.getWidth()); //设置宽度
        //将设置好的属性set回去
        window.setAttributes(lp);
    }

    private void initView() {

        mEtPwdTitle = (TextInputEditText) findViewById(R.id.et_pwd_title);
        mEtPwdUsername = (TextInputEditText) findViewById(R.id.et_pwd_username);
        mEtPwdUserpwd = (TextInputEditText) findViewById(R.id.et_pwd_userpwd);
        mEtPwdUseremail = (TextInputEditText) findViewById(R.id.et_pwd_useremail);
        mEtPwdUserephone = (TextInputEditText) findViewById(R.id.et_pwd_userephone);

        ((Button) findViewById(R.id.pwd_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        ((Button) findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    private void close() {
        mBtnClick.onCloseClick();
    }

    public void setOnBtnClick(BtnClick btnClick) {
        mBtnClick = btnClick;
    }

    private void save() {
        Password password = new Password();
        password.setUser_title(mEtPwdTitle.getText().toString().trim());
        password.setUser_name(mEtPwdUsername.getText().toString().trim());
        password.setUser_pwd(mEtPwdUserpwd.getText().toString().trim());
        password.setUser_email(mEtPwdUseremail.getText().toString().trim());
        password.setUser_phone(mEtPwdUserephone.getText().toString().trim());
        if (isAdd) {
            mBtnClick.onSaveClick(password);
        } else {
            password.setUser_id(mUser_id);
            mBtnClick.onChangeClick(password);
            isAdd = true;
        }
    }

    public void setViewDate(Password password) {
        mUser_id = password.getUser_id();
        mEtPwdTitle.setText(password.getUser_title());
        mEtPwdTitle.setSelection(password.getUser_title().length());

        mEtPwdUsername.setText(password.getUser_name());
        mEtPwdUsername.setSelection(password.getUser_name().length());

        mEtPwdUserpwd.setText(password.getUser_pwd());
        mEtPwdUserpwd.setSelection(password.getUser_pwd().length());

        mEtPwdUseremail.setText(password.getUser_email());
        mEtPwdUseremail.setSelection(password.getUser_email().length());

        mEtPwdUserephone.setText(password.getUser_phone());
        mEtPwdUserephone.setSelection(password.getUser_phone().length());

        isAdd = false;
        ((Button) findViewById(R.id.pwd_add)).setText("修改");
    }

    public interface BtnClick {
        public void onSaveClick(Password password);

        public void onChangeClick(Password password);

        public void onCloseClick();
    }
}
