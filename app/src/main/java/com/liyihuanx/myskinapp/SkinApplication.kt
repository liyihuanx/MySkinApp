package com.liyihuanx.myskinapp

import android.app.Application
import com.liyihuanx.module_skin.SkinManager

/**
 * @ClassName: SkinApplicaiton
 * @Description: java类作用描述
 * @Author: liyihuan
 * @Date: 2021/12/1 23:09
 */
class SkinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
    }

}