package com.example.administrator.savepassword.ui.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.savepassword.R;
import com.example.administrator.savepassword.ui.base.BaseActivity;
import com.example.administrator.savepassword.ui.bean.Password;
import com.example.administrator.savepassword.ui.itface.PasswordInterface;
import com.example.administrator.savepassword.ui.presenter.PasswordPresenter;
import com.example.administrator.savepassword.ui.ui.adapter.PasswordAdapter;
import com.example.administrator.savepassword.ui.ui.weiget.PasswordDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PasswordActivity extends BaseActivity implements PasswordInterface.PasswordView, PasswordDialog.BtnClick, PasswordAdapter.ItemClick {

    private static final String TAG = "PasswordActivity";
    @BindView(R.id.id_tool_bar)
    Toolbar mIdToolBar;
    @BindView(R.id.pwd_rv)
    RecyclerView mPwdRv;
    @BindView(R.id.tv_toolbar)
    TextView mTvToolbar;
    @BindView(R.id.btnRight)
    Button mBtnRight;
    @BindView(R.id.not_date)
    TextView mNotDate;
    private PasswordDialog mPasswordDialog;
    private PasswordInterface.PasswordPresenter mPresenter;
    private List<Password> mList;
    private PasswordAdapter mAdapter;
    private int mItem;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_password;
    }

    @Override
    protected void init() {
        super.init();
        initToolbar();
        initRV();
        initData();
    }

    private void initRV() {
        mPwdRv.setHasFixedSize(true);
        mPwdRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PasswordAdapter(this);
        mPwdRv.setAdapter(mAdapter);
        mAdapter.setOnItemClick(this);
    }

    private void initData() {
        mPresenter = new PasswordPresenter(this);
        mList = new ArrayList<>();
        mPresenter.onShowData();
    }

    private void initToolbar() {
        //用Toolbar替换原来的ActionBar
        setSupportActionBar(mIdToolBar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(null);
        mTvToolbar.setVisibility(View.VISIBLE);
        mTvToolbar.setText("我的密码管理");
        //显示返回按钮
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.mipmap.tab_home_unselect);
    }

    @Override
    public void onPasswordShow(List<Password> passwords) {
        mList.clear();
        mList = passwords;
        if (mList == null || mList.size() <= 0) {
            mPwdRv.setVisibility(View.GONE);
            mNotDate.setVisibility(View.VISIBLE);
            return;
        }

        mNotDate.setVisibility(View.GONE);
        mPwdRv.setVisibility(View.VISIBLE);
        mAdapter.setListData(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPasswordAdd() {
        mPasswordDialog = new PasswordDialog(this);
        mPasswordDialog.show();
        mPasswordDialog.setOnBtnClick(this);
    }

    @Override
    public void onPasswordError(String text) {
        showToast(text);
    }

    @Override
    public void onPasswordSuccess(Password password) {
        mPasswordDialog.dismiss();
        mNotDate.setVisibility(View.GONE);
        mPwdRv.setVisibility(View.VISIBLE);
        mList.add(password);
        mAdapter.setListData(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPasswordDel(Password password) {
        mList.remove(password);
        mAdapter.setListData(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPasswordChange(List<Password> listData) {
        mPasswordDialog.dismiss();
        mList.clear();
        mList.addAll(listData);
        mAdapter.setListData(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_right_btn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                onPasswordAdd();
                break;
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        return true;

    }

    @Override
    public void onSaveClick(Password password) {
        mPresenter.onAddClick(password);
    }

    @Override
    public void onChangeClick(Password password) {
        mPresenter.onChangeClick(password);
    }

    @Override
    public void onCloseClick() {
        mPasswordDialog.dismiss();
    }

    @Override
    public void onItemClick(int item, Password password) {
        mItem = item;
        mPasswordDialog =  new PasswordDialog(this);
        mPasswordDialog.show();
        mPasswordDialog.setViewDate(mList.get(item));
        mPasswordDialog.setOnBtnClick(this);
    }

    @Override
    public void onItemLongClick(int item, final Password password) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除条目");
        String user_title = password.getUser_title();
        builder.setMessage("是否删除您的"+user_title+"信息？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onDelClick(password);
            }
        });
        builder.show();
    }
}
