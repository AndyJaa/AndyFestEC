package com.andy.jaa.andyfec.ui.camera;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.andy.jaa.andyfec.R;
import com.andy.jaa.andyfec.delegates.PermissionCheckerDelegate;
import com.andy.jaa.andyfec.utils.file.FileUtils;

import java.io.File;

/**
 * Created by quanxi on 2018/4/16.
 */

public class CameraHandler implements View.OnClickListener {
    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    public CameraHandler(PermissionCheckerDelegate DELEGATE) {
        this.DIALOG = new AlertDialog.Builder(DELEGATE.getContext()).create();
        this.DELEGATE = DELEGATE;
    }

    final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setContentView(R.layout.dialog_camera_panel);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
        }
    }

    private String getPhotoName() {
        return FileUtils.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto() {
        final String currPhotoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File tmpFile = new File(FileUtils.CAMERA_PHOTO_DIR, currPhotoName);

        //兼容7.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tmpFile.getParent());
            final Uri uri = DELEGATE.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            //需要将uri路径转换成实际路径
            final File realFile = com.blankj.utilcode.util.FileUtils.getFileByPath
                    (FileUtils.getRealFilePath(DELEGATE.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri uri = Uri.fromFile(tmpFile);
            CameraImageBean.getInstance().setPath(uri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
    }

    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult
                (Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHOTO);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.photodialog_btn_take) {
            takePhoto();
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
            DIALOG.cancel();
        } else {
            DIALOG.cancel();
        }
    }
}
