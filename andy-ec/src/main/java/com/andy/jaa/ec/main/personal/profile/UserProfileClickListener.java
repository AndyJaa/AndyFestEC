package com.andy.jaa.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.ui.camera.CameraHandler;
import com.andy.jaa.andyfec.ui.date.DateDialogUtil;
import com.andy.jaa.andyfec.ui.image.GlideApp;
import com.andy.jaa.andyfec.utils.callback.CallbackManager;
import com.andy.jaa.andyfec.utils.callback.CallbackType;
import com.andy.jaa.andyfec.utils.callback.IGlobalCallback;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.orhanobut.logger.Logger;

/**
 * Created by quanxi on 2018/4/16.
 */

public class UserProfileClickListener extends SimpleClickListener {
    private final LatteDelegate DELEGATE;
    private String[] mGenders = new String[]{"男","女","保密"};

    public UserProfileClickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders,0,listener);
        builder.show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id){
            case 1:
                //照相机
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                        GlideApp.with(DELEGATE)
                                .load(args)
                                .into(avatar);
                        //上传头像信息
                    }
                });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate nameDelegate = new NameDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListenr(new DateDialogUtil.IDateListenr() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
