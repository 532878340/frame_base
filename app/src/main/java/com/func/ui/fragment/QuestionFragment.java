package com.func.ui.fragment;

import com.frame.R;
import com.frame.manager.base.view.FrameRootFragment;

/**
 * Description:常见问题页
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public class QuestionFragment extends FrameRootFragment {
    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_question;
    }
}
