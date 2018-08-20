package com.zsh.libpermissionwarp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Setting;
import com.zsh.libpermissionwarp.runtime.RuntimeRationale;
import com.zsh.libpermissionwarp.util.ConvertUtil;

import java.util.List;

/**
 * Created by ZhouShaohua on 2018/8/20.
 */
public class PermissionRequest {
    private Activity mActivity;

    private PermissionRequest() {

    }

    private static class Builder {
        private static PermissionRequest instance = new PermissionRequest();
    }

    public static PermissionRequest build() {
        return Builder.instance;
    }

    public void permissionApply(final Activity activity, final String... permissions) {
        this.mActivity = activity;
        AndPermission.with(activity)
            .runtime()
            .permission(permissions)
            .rationale(new RuntimeRationale())
            .onGranted(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {

                }
            })
            .onDenied(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
                    if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                        showSettingDialog(activity, ConvertUtil.StrArrayToList(permissions));
                    }
                }
            });
    }

    private void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
            .setCancelable(false)
            .setTitle(R.string.title_dialog)
            .setMessage(message)
            .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setPermission();
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
    }

    private void setPermission() {
        AndPermission.with(mActivity)
            .runtime()
            .setting()
            .onComeback(new Setting.Action() {
                @Override
                public void onAction() {
                    Toast.makeText(mActivity, R.string.message_setting_comeback, Toast.LENGTH_SHORT).show();
                }
            }).start();
    }
}
