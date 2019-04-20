package com.gy.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ：2019/04/20 15:10
 * 描 述 ：
 * ============================================================
 */
open class BaseActivity : AppCompatActivity() {
    protected lateinit var TAG: String
    protected lateinit var mContext: Context
    protected lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.TAG = javaClass.simpleName
        this.mContext = this
        this.mActivity = this
    }

    fun go(clazz: Class<out BaseActivity>) {
        _goActivity(clazz, null, false)
    }

    fun _goActivity(clazz: Class<out BaseActivity>?, bundle: Bundle?, finish: Boolean) {
        if (null == clazz) {
            throw IllegalArgumentException("you must pass a target activity where to go.")
        }
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        if (finish) {
            finish()
        }
    }
}