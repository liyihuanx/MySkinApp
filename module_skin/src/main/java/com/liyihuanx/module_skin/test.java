package com.liyihuanx.module_skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Constructor;

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: 类的描述
 */
public class test {

    public static int[] getResId(Context context, int[] attrs) {
        int[] resIds = new int[attrs.length];
        TypedArray a = context.obtainStyledAttributes(attrs);
        for (int i = 0; i < attrs.length; i++) {
            resIds[i] = a.getResourceId(i, 0);
        }
        a.recycle();
        return resIds;
    }

    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };

}
