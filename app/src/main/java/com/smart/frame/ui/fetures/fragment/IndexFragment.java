package com.smart.frame.ui.fetures.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;
import com.smart.frame.BuildConfig;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.bus.RxBus;
import com.smart.frame.bus.impl.TmpBus;
import com.smart.frame.ui.fetures.user.activity.LoginActivity;
import com.smart.frame.ui.fetures.user.activity.ResetLoginPwdActivity;
import com.smart.frame.utils.ToastManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import butterknife.BindView;

/**
 * Description:
 *
 * @author Zijin
 * @date 2017/8/4
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
        TmpBus tmpBus = new TmpBus();
        tmpBus.setName("这是粘性事件");
        RxBus.getInstance().postSticky(tmpBus);
        RxView.clicks(cancel).subscribe(integer -> {
//            startActivity(new Intent(mContext, LoginActivity.class));

            new RxPermissions(getActivity()).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(aBoolean -> {
                if(aBoolean){
                    Tinker tinker = Tinker.with(getContext());
                    Logger.d("Tinker 是否需要更新补丁 " + tinker.isTinkerLoaded());
                    if(!tinker.isTinkerLoaded()){
                        final String patchLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
                        TinkerInstaller.onReceiveUpgradePatch(getContext(), patchLocation);
                    }

                    ToastManager.getInstance().showMessage(mContext,"补丁打上了....没有打上了yoyo~~~XXXX" + BuildConfig.TINKER_ID);
                }
            });
        });
    }
}
