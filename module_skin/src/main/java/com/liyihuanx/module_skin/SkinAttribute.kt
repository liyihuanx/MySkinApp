package com.liyihuanx.module_skin

import android.util.AttributeSet
import android.view.View

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: 类的描述
 */
class SkinAttribute {
    // 定义需要换肤的属性
    private val mAttributes = arrayListOf(
        "background", "src", "textColor"
    )

    //
    // 保存换肤的View和他的属性  view   --> List<SkinPairs>
    // 保存的类型如           TextView --> {[textColor,resId],[background,resId]}
    private val mSkinViews = ArrayList<SkinView>()

    /**
     * 通知全部View换肤
     */
    fun notifyAllApplySkin(){
        mSkinViews.forEach {
            it.applySkin()
        }
    }

    /**
     * 收集每个view的可以换肤的属性
     */
    fun collectViewAttr(view: View, attrs: AttributeSet) {
        val mSkinPairs = ArrayList<SkinPair>()
        for (i in 0 until attrs.attributeCount) {
            // 获取属性名
            val attributeName = attrs.getAttributeName(i)
            // 判断在不在可替换的属性列表中
            if (mAttributes.contains(attributeName)) {
                val attributeValue = attrs.getAttributeValue(i)
                // #号开头的不做替换，就是默认写死的颜色了
                if (attributeValue.startsWith("#")) {
                    continue
                }

                // ？开头的，一般都是系统的，就去系统的找一下
                val resId = if (attributeValue.startsWith("?")) {
                    val attrId = attributeValue.drop(1).toInt()
                    SkinThemeUtil.getResId(view.context, intArrayOf(attrId))[0]
                } else {
                    // 这里就是@开头的值了
                    attributeValue.drop(1).toInt()
                }

                mSkinPairs.add(SkinPair(attributeName,resId))
            }

            if (mSkinPairs.isNotEmpty() || view is SkinViewSupport) {
                val skinView = SkinView(view, mSkinPairs)
                // 如果选择过皮肤，调用一次加载皮肤的资源
                skinView.applySkin()
                mSkinViews.add(skinView)
            }
        }


    }
}

