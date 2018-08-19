package com.zsh.libpermissiom;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZhouShaohua on 2018/8/19.
 */
/*package*/ class PermissionCheck {

    public PermissionCheck() {

    }

    //返回需要申请权限的Set,判断Set的长度即可。如果为0，就权限均已申请成功。
    public Set<String> checkPermission(Context context, String... permissions) {
        return checkPermission(context, Arrays.asList(permissions));
    }

    public Set<String> checkPermission(Context context, List<String> permissions) {
        AppOpsManager opsManager = null;
        Set<String> needGetPermissions = new HashSet<>();
        for (String permission : permissions) {
            int result = context.checkPermission(permission, Process.myPid(), Process.myUid());
            if (result == PackageManager.PERMISSION_DENIED) {
                needGetPermissions.add(permission);
                continue;
            }

            String op = AppOpsManager.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) {
                continue;
            }

            if (opsManager == null) {
                opsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            }
            result = opsManager.checkOpNoThrow(op, Process.myPid(), context.getPackageName());
            if (result != AppOpsManager.MODE_ALLOWED) {
                needGetPermissions.add(permission);
            }
        }
        return needGetPermissions;
    }
}
