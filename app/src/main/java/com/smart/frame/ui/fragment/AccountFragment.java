package com.smart.frame.ui.fragment;

import android.Manifest;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.ui.contactslist.common.FloatingBarItemDecoration;
import com.smart.frame.ui.fragment.contact.ContactAdapter;
import com.smart.frame.ui.fragment.contact.ContactEntity;
import com.smart.frame.ui.fragment.contact.ContactManager;
import com.smart.frame.ui.fragment.contact.IndexBar;
import com.smart.frame.ui.view.loading.LoadingView;
import com.smart.frame.ui.view.recycleview.BaseRecycleAdapter;
import com.smart.frame.utils.SnackBarUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class AccountFragment extends SimpleFragment {
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.loadingView)
    LoadingView loadingView;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    private Status mStatus;

    ContactAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private LinkedHashMap<Integer, String> mHeaderList = new LinkedHashMap<>();
    private View mLetterHintView;

    /**
     * 联系人列表
     */
    private List<ContactEntity> contactList = new ArrayList<>();

    public static AccountFragment getInstance() {
        return new AccountFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initViewOrData() {

        loadingView.loading();
        new RxPermissions(getActivity())
                .request(Manifest.permission.READ_CONTACTS)
                .subscribe(granted -> {
                    if (granted) {//有权限
                        setStatus(Status.LOADING);
                    } else {
                        setStatus(Status.NO_PERMISSION);
                    }
                });

        initRecycleView();
        initIndexBar();
        initSearchView();
    }

    /**
     * 初始化recycleview
     */
    void initRecycleView() {
        mLayoutManager = new LinearLayoutManager(mContext);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(mContext)
                        .color(0xffe0e0e0)
                        .size(1)
                        .marginResId(R.dimen.divider_margin_left,R.dimen.divider_margin_right)
                        .build());
        recycleView.addItemDecoration(new FloatingBarItemDecoration(mContext, mHeaderList));
        mAdapter = new ContactAdapter(mContext,contactList);
        recycleView.setAdapter(mAdapter);
    }

    /**
     * 初始化搜索栏
     */
    void initSearchView() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fetchContactList();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * 初始化IndexBar
     */
    void initIndexBar() {
        indexBar.setOnTouchingLetterChangedListener(new IndexBar.OnTouchingLetterChangeListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                showLetterHintDialog(s);
                SnackBarUtils.showSnackBar(mContainer,mAdapter.getCheckedPhone());
                for (Integer position : mHeaderList.keySet()) {
                    if (mHeaderList.get(position).equals(s)) {
                        mLayoutManager.scrollToPositionWithOffset(position, 0);
                        return;
                    }
                }
            }

            @Override
            public void onTouchingStart(String s) {
                showLetterHintDialog(s);
            }

            @Override
            public void onTouchingEnd(String s) {
                hideLetterHintDialog();
            }
        });
    }

    private PopupWindow mOperationPpWindow;

    /**
     * related to {@link com.smart.frame.ui.contactslist.common.IndexBar#mOnTouchingLetterChangeListener}
     * show dialog in the center of this window
     *
     * @param s
     */
    private void showLetterHintDialog(String s) {
        if (mOperationPpWindow == null) {
            mLetterHintView = View.inflate(mContext, R.layout.dialog_letter_hint, null);
            mOperationPpWindow = new PopupWindow(mLetterHintView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
            mOperationPpWindow.setOutsideTouchable(true);
        }
        ((TextView) mLetterHintView.findViewById(R.id.dialog_letter_hint_textview)).setText(s);
        mOperationPpWindow.showAtLocation(mContainer, Gravity.CENTER, 0, 0);
    }

    private void hideLetterHintDialog() {
        if (mOperationPpWindow != null) {
            mOperationPpWindow.dismiss();
        }
    }

    /**
     * 设置加载状态
     */
    void setStatus(Status status) {
        if (mStatus == status) return;
        mStatus = status;

        switch (status) {
            case LOADING:
                tvStatus.setVisibility(View.GONE);
                loadingView.loading();
                fetchContactList();
                break;
            case SHOW:
                tvStatus.setVisibility(View.GONE);
                loadingView.success();
                break;
            case NO_PERMISSION:
                tvStatus.setText("暂无权限，请手动授权");
                tvStatus.setVisibility(View.VISIBLE);
                break;
        }
    }

    public enum Status {
        LOADING,    //加载联系人
        SHOW,       //显示联系人
        NO_PERMISSION,  //无权限
    }

    /**
     * 获取联系人数据
     */
    void fetchContactList() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                contactList = ContactManager.getContactList(mContext, edtSearch.getText().toString());
                mHandler.sendEmptyMessage(0);
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                preOperation();

                mAdapter.clear();
                mAdapter.addAll(contactList);
                setStatus(Status.SHOW);
            }
        }
    };

    /**
     * calculate the TAG and add to {@link #mHeaderList}
     */
    private void preOperation() {
        mHeaderList.clear();
        if (contactList.size() == 0) {
            return;
        }
        mHeaderList.put(0, contactList.get(0).getInitial());
        for (int i = 1; i < contactList.size(); i++) {
            if (!contactList.get(i - 1).getInitial().equalsIgnoreCase(contactList.get(i).getInitial())) {
                mHeaderList.put(i, contactList.get(i).getInitial());
            }
        }

        indexBar.setNavigators(new ArrayList<>(mHeaderList.values()));
    }
}
