package com.liyihuanx.module_skin

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: 存放每一个View和他对应的属性
 */
data class SkinView(
    val view: View,
    val skinPairs: List<SkinPair>
) {

    /**
     * 遍历该View下的所有属性值
     * 可以修改的就做修改
     */
    fun applySkin() {
        applySkinSupport()
        skinPairs.forEach {
            when (it.attributeName) {
                "textColor" -> {
                    (view as TextView).setTextColor(
                        SkinResource.instance?.getColorStateList(it.resId))
                }
                "background" -> {
                    val background = SkinResource.instance?.getBackground(it.resId)
                    if (background is Int) {
                        view.setBackgroundResource(background)
                    } else {
                        ViewCompat.setBackground(view, background as Drawable)
                    }
                }
                "src" -> {
                    val background = SkinResource.instance?.getBackground(it.resId)
                    if (background is Int) {
                        (view as ImageView).setImageDrawable(ColorDrawable(background))
                    } else {
                        (view as ImageView).setImageDrawable(background as Drawable)
                    }
                }

            }
        }
    }

    /**
     * 如果是自定义view想要换肤
     */
    private fun applySkinSupport() {
        if (view is SkinViewSupport) {
            view.applySkinSupport()
        }
    }


}
