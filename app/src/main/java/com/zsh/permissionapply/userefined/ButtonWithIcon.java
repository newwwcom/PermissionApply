package com.zsh.permissionapply.userefined;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by ZhouShaohua on 2018/9/15.
 */
public class ButtonWithIcon extends Button {
    public ButtonWithIcon(Context context) {
        super(context);
    }

    public ButtonWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (drawableLeft != null) {
            float textWidth = getPaint().measureText(getText().toString());//文本的宽度
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawableLeft.getBounds().width();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate(0, 0);
        }
        super.onDraw(canvas);
    }
}
