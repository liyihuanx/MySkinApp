package com.liyihuanx.module_skin

import android.content.Context
import android.content.SharedPreferences

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: 类的描述
 */
class SkinPreference private constructor(context: Context) {
    private val mPref: SharedPreferences
    fun reset() {
        mPref.edit().remove(KEY_SKIN_PATH).apply()
    }

    var skin: String?
        get() = mPref.getString(KEY_SKIN_PATH, null)
        set(skinPath) {
            mPref.edit().putString(KEY_SKIN_PATH, skinPath).apply()
        }

    companion object {
        private const val SKIN_SHARED = "skins"
        private const val KEY_SKIN_PATH = "skin-path"

        @Volatile
        var instance: SkinPreference? = null
            private set

        fun init(context: Context) {
            if (instance == null) {
                synchronized(SkinPreference::class.java) {
                    if (instance == null) {
                        instance = SkinPreference(context.applicationContext)
                    }
                }
            }
        }
    }

    init {
        mPref = context.getSharedPreferences(SKIN_SHARED, Context.MODE_PRIVATE)
    }
}