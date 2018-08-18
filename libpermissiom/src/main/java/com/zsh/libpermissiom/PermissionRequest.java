package com.zsh.libpermissiom;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.Set;

/**
 * 对外提供动态权限申请适配封装API
 * Created by ZhouShaohua on 2018/8/18.
 */
public class PermissionRequest {

    private PermissionRequest() {

    }

    private static class Builder {
        private static PermissionRequest instance = new PermissionRequest();
    }

    public static PermissionRequest build() {
        return Builder.instance;
    }

    public boolean apply(Activity activity) {
        return apply(activity, null);
    }

    public boolean apply(Activity activity, @Nullable String[] permissions) {
        Set<String> permissionSet = ConvertUtil.StrArrayToSet(PermissionCategory.DEAFALT_PERMISSIONS);
        if (permissions != null && permissions.length > 0) {
            for (String s : permissions) {
                if (permissionSet.contains(s)) {
                    continue;
                }
                permissionSet.add(s);
            }
        }
        request(activity, ConvertUtil.SetToStrArray(permissionSet), 1);
        return false;
        //test begin
        /*for (String s : permissionSet) {
            android.util.Log.i("zsh", "get sets str : " + s);
        }

        String[] ss = ConvertUtil.SetToStrArray(permissionSet);
        for (String s : ss) {
            android.util.Log.i("zsh", "get ss[] str : " + s);
        }*/
        //test end
    }

    private void request(Activity activity, String[] ps, int reqCode) {
        ActivityCompat.requestPermissions(activity, ps, reqCode);
    }

}
