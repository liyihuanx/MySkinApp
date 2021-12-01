package com.liyihuanx.module_skin

import android.content.Context

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: 类的描述
 */
object SkinThemeUtil {


    /**
     * 获取theme属性中定义的资源id
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