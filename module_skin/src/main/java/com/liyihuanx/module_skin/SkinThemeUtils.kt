package com.liyihuanx.module_skin

import android.content.Context

/**
 * @author 享学课堂 jett
 */
object SkinThemeUtils {
    /**
     * 获得theme中的属性中定义的 资源id
     * @param context
     * @param attrs
     * @return
     */
    fun getResId(context: Context, attrs: IntArray): IntArray {
        val resIds = IntArray(attrs.size)
        val a = context.obtainStyledAttributes(attrs)
        for (i in attrs.indices) {
            resIds[i] = a.getResourceId(i, 0)
        }
        a.recycle()
        return resIds
    }
}