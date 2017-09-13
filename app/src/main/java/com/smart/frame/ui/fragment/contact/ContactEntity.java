package com.smart.frame.ui.fragment.contact;

/**
 * Description:联系人实体
 * Created by Zijin on 2017/9/13.
 */

public class ContactEntity implements Abbreviated, Comparable<ContactEntity> {
    private final String mName;
    private final String mPhone;
    private final String mAbbreviation;
    private final String mInitial;

    ContactEntity(String name, String phone) {
        this.mName = name;
        this.mPhone = phone;
        this.mAbbreviation = ContactsUtils.getAbbreviation(name);
        this.mInitial = mAbbreviation.substring(0, 1);
    }

    @Override
    public String getInitial() {
        return mInitial;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    @Override
    public int compareTo(ContactEntity r) {
        if (mAbbreviation.equals(r.mAbbreviation)) {
            return 0;
        }
        boolean flag;
        if ((flag = mAbbreviation.startsWith("#")) ^ r.mAbbreviation.startsWith("#")) {
            return flag ? 1 : -1;
        }
        return getInitial().compareTo(r.getInitial());
    }
}
