package com.zsh.libpermissiom;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.Iterator;
import java.util.Set;

/**
 * 对外提供动态权限申请适配封装API
 * Created by ZhouShaohua on 2018/8/18.
 */
public class PermissionRequest {
    private final int ALL_PERMISSION_DENIED = -1;
    private final int PART_PERMISSION_GRANTED = 0;
    private final int ALL_PERMISSION_GRANTED = 1;

    private PermissionRequest() {

    }

    private static class Builder {
        private static PermissionRequest instance = new PermissionRequest();
    }

    public static PermissionRequest build() {
        return Builder.instance;
    }

    public int apply(Activity activity, int reqCode, @Nullable String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return ALL_PERMISSION_GRANTED;
        }

        if(permissions == null || permissions.length < 1){
            return ALL_PERMISSION_DENIED;
        }

        Set<String> getPermissionSet = ConvertUtil.StrArrayToSet(permissions);
        Iterator<String> iterator = getPermissionSet.iterator();
        while (iterator.hasNext()) {
            if (check(activity, iterator.next())) {
                getPermissionSet.remove(iterator.next());
            }
        }
        if (getPermissionSet.size() == 0) {
            return ALL_PERMISSION_GRANTED;
        }
        request(activity, ConvertUtil.SetToStrArray(getPermissionSet), reqCode);
        return ALL_PERMISSION_GRANTED;
    }

    //判断当前是否含有被赋予权限
    private boolean check(Context context, String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(context, permission);
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    //经过封装后的权限申请调用方法
    private void request(Activity activity, String[] ps, int reqCode) {
        ActivityCompat.requestPermissions(activity, ps, reqCode);
    }

}
