package com.smart.frame.ui.fetures.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.view.RxView;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.bus.RxBus;
import com.smart.frame.bus.impl.TmpBus;
import com.smart.frame.ui.fetures.user.activity.LoginActivity;
import com.smart.frame.ui.view.basic.UltimateBar;
import com.smart.frame.utils.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions2.RxPermissions;

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
    @BindView(R.id.imgIcon)
    ImageView imgIcon;

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
            new RxPermissions(getActivity()).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(aBoolean -> {
                if(aBoolean){
//                    Tinker tinker = Tinker.with(getContext());
//                    Logger.d("Tinker 是否需要更新补丁 " + tinker.isTinkerLoaded());
//                    if(!tinker.isTinkerLoaded()){
//                        final String patchLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
//                        TinkerInstaller.onReceiveUpgradePatch(getContext(), patchLocation);
//                    }
//
//                    ToastManager.getInstance().showMessage(mContext,"补丁打上了....没有打上了yoyo~~~XXXXTTTTTTT" + BuildConfig.TINKER_ID);

                    startActivity(new Intent(mContext, LoginActivity.class));
                }
            });
        });

        ImageLoader.displayImage(mContext,"http://192.168.10.148:8999/productPic/20180329/3256c82b90a84543acd851dc45c2ba99.jpg",imgIcon);

        Log.d(TAG, "initViewOrData: ");
    }

    private static final String TAG = "IndexFragment";
}
