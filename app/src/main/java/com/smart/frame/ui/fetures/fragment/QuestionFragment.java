package com.smart.frame.ui.fetures.fragment;

import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class QuestionFragment extends SimpleFragment {
    public static QuestionFragment getInstance() {
        return new QuestionFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initViewOrData() {
        Logger.d("initViewOrData 创建" + toString());
    }
}
