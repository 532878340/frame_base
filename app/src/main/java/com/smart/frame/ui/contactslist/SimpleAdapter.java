package com.smart.frame.ui.contactslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.smart.frame.R;
import com.smart.frame.ui.view.recycleview.BaseRecycleAdapter;
import com.smart.frame.ui.view.recycleview.BaseRecycleHolder;

import java.util.List;

/**
 * Description:
 * Created by Zijin on 2017/9/12.
 * Email: info@zijinqianbao.com
 */

public class SimpleAdapter extends BaseRecycleAdapter<ShareContactsBean>{

    public SimpleAdapter(Context context, @NonNull List<ShareContactsBean> list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void onBindView(BaseRecycleHolder holder, int position,final ShareContactsBean shareContactsBean) {
        TextView nameTx = holder.getView(R.id.name);
//        TextView phoneTv = holder.getView(R.id.list_item_contact_number);
        nameTx.setText(shareContactsBean.getName());
//        phoneTv.setText(shareContactsBean.getPhone());
    }
}
