package com.liyihuanx.module_skin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import java.lang.reflect.Constructor
import java.util.*

/**
 * @author created by liyihuanx
 * @date 2021/11/24
 * @description: view的生产工厂，代替系统创建view时的inflate过程
 */
class SkinLayoutFactory(
    //用于获取窗口的状态框的信息
    private val activity: Activity
) : LayoutInflater.Factory2,Observer {

    private val mClassPrefixList = arrayOf(
        "android.widget.",
        "android.webkit.",
        "android.app.",
        "android.view."
    )


    // 记录view的构造函数
    private val mConstructorSignature = arrayOf(
        Context::class.java, AttributeSet::class.java
    )

    private val sConstructorMap = HashMap<String, Constructor<out View>>()

    // 页面属性管理器 当选择新皮肤后需要替换View与之对应的属性
    private val skinAttribute: SkinAttribute = SkinAttribute()


    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return null
    }


    private fun createSDKView(name: String, context: Context, attrs: AttributeSet): View? {
        //如果包含，则不是SDK中的view可能是自定义view包括support库中的View
        if (-1 != name.indexOf('.')) {
            return null
        }

        //不包含就要在解析的节点name前，拼上:android.widget.等尝试去反 射
        for (i in mClassPrefixList.indices) {
            val createView = createView(mClassPrefixList.get(i).toString() + name, context, attrs)
            if (createView!=null){
                return createView
            }
        }
        return null
    }


    private fun createView(name: String, context: Context, attrs: AttributeSet): View? {
        val constructor = findConstructor(context, name)
        try {
            return constructor.newInstance(context, attrs)
        } catch (e: java.lang.Exception) {
        }
        return null
    }


    private fun findConstructor(context: Context, name: String): Constructor<out View> {
        var constructor = sConstructorMap[name]
        if (constructor == null) {
            try {
                val clazz = context.classLoader.loadClass(name).asSubclass(View::class.java)
                constructor = clazz.getConstructor(*mConstructorSignature)
                sConstructorMap[name] = constructor
            } catch (e: Exception) {
            }
        }
        return constructor!!
    }




    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return null
    }

    override fun update(o: Observable?, arg: Any?) {
        TODO("Not yet implemented")
    }
}