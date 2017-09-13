package com.smart.frame.ui.fragment.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Description:联系人 manager
 * Created by Zijin on 2017/9/13.
 * Email: info@zijinqianbao.com
 */

public class ContactManager {
    /**
     * 获取联系人集合
     * @param context 上下文
     * @param conditons 搜索条件
     */
    public static ArrayList<ContactEntity> getContactList(Context context,String conditons){
        ArrayList<ContactEntity> result = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE '%" + conditons + "%' OR " +
                        ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE '%" + conditons + "%'",
                null,
                null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(0).replace(" ", "").replace("-", "");
                String contactName = phoneCursor.getString(1);
                result.add(new ContactEntity(contactName, phoneNumber));
            }
            phoneCursor.close();
        }

        Collections.sort(result, (l, r) -> l.compareTo(r));
        return result;
    }
}
