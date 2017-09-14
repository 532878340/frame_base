package com.smart.frame.ui.fragment;

import android.content.Intent;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.ui.activity.SecondActivity;
import com.smart.frame.ui.contactslist.ContactListActivity;
import com.smart.frame.ui.fragment.contact.AppointTimePickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class IndexFragment extends SimpleFragment{
    @BindView(R.id.cancel)
    Button cancel;

    public static IndexFragment getInstance() {
        return new IndexFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initViewOrData() {
        RxView.clicks(cancel)
                .subscribe(o -> {
                    List<String> list = new ArrayList<>();
                    for (int i = 0 ;i < 10;i++){
                        list.add("轮子" + i);
                    }
                    AppointTimePickerDialog dialog = new AppointTimePickerDialog(mContext,list);
                    dialog.setPickerListener(new AppointTimePickerDialog.TimePickerListener() {
                        @Override
                        public void onPickerSelected(int pos) {
                            cancel.setText(list.get(pos));
                        }
                    });
                    dialog.show();
                });

        Logger.d("initViewOrData 创建" + toString());
    }
}
