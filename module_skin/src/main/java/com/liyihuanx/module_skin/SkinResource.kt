package com.liyihuanx.module_skin

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.TextUtils

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: 类的描述
 */
class SkinResource private constructor(context: Context) {

    companion object {
        //单例
        @Volatile
        var instance: SkinResource? = null
            private set


        fun init(context: Context) {
            if (instance == null) {
                synchronized(SkinResource::class.java) {
                    if (instance == null) {
                        instance = SkinResource(context)
                    }
                }
            }
        }
    }

    // 皮肤包的包名
    private var mSkinPkgName = ""

    // 是否使用的默认皮肤包
    private var isDefaultSkin = true

    // app原始的resource
    private val mAppResources: Resources = context.resources

    //皮肤包的resource
    private var mSkinResources: Resources? = null

    fun applySkin(skinResources: Resources? = null, pkgName: String = "") {

        mSkinResources = skinResources
        mSkinPkgName = pkgName
        //是否使用默认皮肤
        isDefaultSkin = TextUtils.isEmpty(pkgName) || skinResources == null
    }

    // 恢复默认皮肤，重置变量
    fun reset() {
        mSkinResources = null
        mSkinPkgName = ""
        isDefaultSkin = true
    }

    /**
     * 比如替换颜色
     * 主App的R.color.white = #123  ， 在Skin的R.color.white = #456
     * 主App拿到需要替换变量的名字和类型   ---->  在Skin搜索同类型且同名的变量
     * 这样就可以替换里面的值了
     */
    private fun getIdentifier(resId: Int): Int {
        if (isDefaultSkin) {
            return resId;
        }
        //
        val resourceName = mAppResources.getResourceEntryName(resId)
        val resourceType = mAppResources.getResourceTypeName(resId)
        return mSkinResources!!.getIdentifier(resourceName, resourceType, mSkinPkgName)
    }


    fun getColor(resId: Int): Int {
        if (isDefaultSkin) {
            return mAppResources.getColor(resId)
        }
        val skinId = getIdentifier(resId)
        if (skinId == 0) {
            return mAppResources.getColor(resId)
        }
        return mSkinResources!!.getColor(skinId)
    }

    fun getDrawable(resId: Int): Drawable {
        if (isDefaultSkin) {
            return mAppResources.getDrawable(resId)
        }
        val skinId = getIdentifier(resId)
        if (skinId == 0) {
            return mAppResources.getDrawable(resId)
        }
        return mSkinResources!!.getDrawable(skinId)
    }

    fun getBackground(resId: Int): Any {
        val resourceTypeName = mAppResources.getResourceTypeName(resId)
        return if (resourceTypeName == "color"){
            getColor(resId)
        } else {
            getDrawable(resId)
        }
    }

    fun getColorStateList(resId: Int): ColorStateList? {
        if (isDefaultSkin) {
            return mAppResources.getColorStateList(resId)
        }
        val skinId = getIdentifier(resId)
        return if (skinId == 0) {
            mAppResources.getColorStateList(resId)
        } else mSkinResources!!.getColorStateList(skinId)
    }
}