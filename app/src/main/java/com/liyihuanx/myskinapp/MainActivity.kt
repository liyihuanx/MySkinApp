package com.liyihuanx.myskinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.liyihuanx.module_skin.SkinManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSkin.setOnClickListener {
            SkinManager.instance?.loadSkin("/data/data/com.liyihuanx.myskinapp/plugin_skin-debug.apk")
        }

        btnSkinReset.setOnClickListener {
            SkinManager.instance?.loadSkin(null)
        }
    }
}