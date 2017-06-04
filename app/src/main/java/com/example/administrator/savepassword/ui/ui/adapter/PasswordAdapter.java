package com.example.administrator.savepassword.ui.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.savepassword.R;
import com.example.administrator.savepassword.ui.bean.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置数据过滤
 */

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder> {

    private Context mContext;
    private List<Password> mPasswords;
    private ItemClick mItemClick;

    public PasswordAdapter(Context context) {
        mContext = context;
    }

    public void setListData(List<Password> listData) {
        mPasswords = listData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pwd_data, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(position, mPasswords);
    }

    @Override
    public int getItemCount() {
        if (mPasswords != null && mPasswords.size() > 0) {
            return mPasswords.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.item_pwd_title)
        TextView mItemPwdTitle;
        @BindView(R.id.item_pwd_name)
        TextView mItemPwdName;
        @BindView(R.id.item_pwd_pwd)
        TextView mItemPwdPwd;
        @BindView(R.id.item_pwd_email)
        TextView mItemPwdEmail;
        @BindView(R.id.item_pwd_phone)
        TextView mItemPwdPhone;
        @BindView(R.id.titlt_email)
        TextView mTitltEmail;
        @BindView(R.id.titlt_phone)
        TextView mTitltPhone;
        private int mItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void bindView(int item, List<Password> passwords) {
            mItem = item;
            Password password = passwords.get(item);
            mItemPwdTitle.setText(password.getUser_title());
            mItemPwdName.setText(password.getUser_name());
            mItemPwdPwd.setText(password.getUser_pwd());
            //判断是否存在邮箱、电话，有则显示，没有则不显示
            if (TextUtils.isEmpty(password.getUser_email())) {
                mItemPwdEmail.setVisibility(View.GONE);
                mTitltEmail.setVisibility(View.GONE);
            } else {
                mItemPwdEmail.setVisibility(View.VISIBLE);
                mTitltEmail.setVisibility(View.VISIBLE);
                mItemPwdEmail.setText(password.getUser_email());
            }

            if (TextUtils.isEmpty(password.getUser_phone())) {
                mItemPwdPhone.setVisibility(View.GONE);
                mTitltPhone.setVisibility(View.GONE);
            } else {
                mItemPwdPhone.setVisibility(View.VISIBLE);
                mTitltPhone.setVisibility(View.VISIBLE);
                mItemPwdPhone.setText(password.getUser_phone());
            }
        }

        @Override
        public void onClick(View v) {
            if (mItemClick != null) {
                // 重点 这里ViewHolder 中提供了 getPosition（）；
                int position = getPosition();
                mItemClick.onItemClick(position, mPasswords.get(position));
            }
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getPosition();
            mItemClick.onItemLongClick(position, mPasswords.get(position));
            return true;
        }
    }

    public interface ItemClick {
        public void onItemClick(int item, Password password);
        public void onItemLongClick(int item, Password password);
    }

    public void setOnItemClick(ItemClick itemClick) {
        mItemClick = itemClick;
    }
}
