package com.zsh.permissionapply;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.zsh.libpermissionwarp.PermissionRequest;
import com.zsh.libpermissionwarp.util.ConvertUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayAdapter<String> mAad;
    private Button btn1, btn2;
    private String[] mTests = new String[]{"we", "are", "the", "emmm", "yes", "no"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        //init();
        test();
    }

    private void test() {
        btn1 = findViewById(R.id.test_view);
        btn1.setCompoundDrawables(getTestDrawable(), null, null, null);
        btn1.setText("ceshi");
    }

    public Drawable getTestDrawable() {
        Drawable drawable = getResources().getDrawable(R.drawable.testpng);
        drawable.setBounds(0, 0, (int) getResources().getDimension(R.dimen.user_width), (int) getResources().getDimension(R.dimen.user_height));
        return drawable;
    }

    private void init() {
        //PermissionRequest.build().apply(MainActivity.this, new String[]{"我", "们", "是", "中", "们", "国", "是", "人"});
        PermissionRequest.build().permissionApply(MainActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_set:
                mAad.clear();
                mAad.addAll(ConvertUtil.StrArrayToList(mTests));
                android.util.Log.i("zsh", "the num in set : " + mAad.getCount());
                break;
            case R.id.button_clear:
                mAad.clear();
                android.util.Log.i("zsh", "the num in clear : " + mAad.getCount());
                break;
            default:
                break;
        }
    }


}

