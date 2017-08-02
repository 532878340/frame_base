package com.func.ui.activity;

import android.widget.TextView;

import com.frame.FrameApplication;
import com.frame.R;
import com.frame.manager.base.view.BaseListActivity;
import com.frame.view.recycleview.BaseRecycleHolder;
import com.func.di.component.DaggerRecycleComponent;
import com.func.di.module.BaseListModule;
import com.func.entity.SteadyEntity;
import com.func.presenter.RecycleListPresenter;

/**
 * Description:
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

public class ListActivity extends BaseListActivity<RecycleListPresenter,SteadyEntity>{
    @Override
    protected int getItemResource() {
        return R.layout.item_home;
    }

    @Override
    protected void initInjector() {
        DaggerRecycleComponent.builder()
                .appComponent(FrameApplication.getAppComponent())
                .baseListModule(new BaseListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar(false,"稳健型产品");
    }

    @Override
    protected void bindView(BaseRecycleHolder holder, int position, SteadyEntity steadyEntity) {
        TextView textView = holder.getView(R.id.id_num);
        textView.setText(steadyEntity.getProductInformationName());
    }
}
