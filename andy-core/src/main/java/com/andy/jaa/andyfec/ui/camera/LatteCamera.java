package com.andy.jaa.andyfec.ui.camera;

import android.net.Uri;

import com.andy.jaa.andyfec.delegates.PermissionCheckerDelegate;
import com.andy.jaa.andyfec.utils.file.FileUtils;

/**
 * Created by quanxi on 2018/4/16.
 */

public class LatteCamera {
    public static Uri createCropFile(){
        return Uri.parse
                (FileUtils.createFile("crop_image",
                        FileUtils.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
