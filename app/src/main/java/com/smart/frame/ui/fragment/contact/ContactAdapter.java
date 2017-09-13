package com.smart.frame.ui.fragment.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.TextView;

import com.smart.frame.R;
import com.smart.frame.ui.view.recycleview.BaseRecycleAdapter;
import com.smart.frame.ui.view.recycleview.BaseRecycleHolder;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Description:联系人适配器
 * Created by Zijin on 2017/9/13.
 * Email: info@zijinqianbao.com
 */

public class ContactAdapter extends BaseRecycleAdapter<ContactEntity>{
    private LinkedHashMap<Integer,String> checkMap = new LinkedHashMap<>();

    public ContactAdapter(Context context, @NonNull List<ContactEntity> list) {
        super(context, list, R.layout.listitem_share_contact_content);
    }

    @Override
    public void onBindView(BaseRecycleHolder holder, int position, ContactEntity contactEntity) {
        TextView name = holder.getView(R.id.name);
        CheckBox checkBox = holder.getView(R.id.checkBox);
        name.setText(contactEntity.getName());

        checkBox.setChecked(checkMap.containsKey(position));
        checkBox.setOnClickListener(view -> {
            if(checkMap.containsKey(position)){
                checkMap.remove(position);
            }else{
                checkMap.put(position,contactEntity.getPhone());
            }
        });
    }

    /**
     * 获取选择的号码
     */
    public String getCheckedPhone(){
        String checkedPhone = "";
        for (Integer integer : checkMap.keySet()){
            checkedPhone += checkMap.get(integer) + ",";
        }

        if(checkedPhone.length() > 0){
            checkedPhone = checkedPhone.substring(0,checkedPhone.length() - 1);
        }
        return checkedPhone;
    }
}
