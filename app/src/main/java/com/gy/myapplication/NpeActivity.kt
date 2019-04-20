package com.gy.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gy.myapplication.model.User
import kotlinx.android.synthetic.main.activity_npe.*
import java.lang.NullPointerException

class NpeActivity : BaseActivity() {

    var a: String = ""
    var b: String? = null
    lateinit var c: String
    val d: String by lazy {
        //需要的时候懒加载获取
        "xxx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_npe)
        initListener()
    }

    private fun initListener() {
        an_b_sumFund1.setOnClickListener {
            Toast.makeText(mContext, "请查看LogCat", Toast.LENGTH_SHORT).show()
            try {
                sumFund1()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        an_b_sumFund2.setOnClickListener {
            Toast.makeText(mContext, "请查看LogCat", Toast.LENGTH_SHORT).show()
            try {
                sumFund2()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        an_b_smartCast1.setOnClickListener {
        }
        an_b_smartCast2.setOnClickListener {
            mUser2 = User()
            testSmartCast2()
            mUser2 = null
        }

    }

    var mUser: User? = null
    fun sumFund1() {
        val user = mUser
        val fund = user?.mAccount?.mFund
        val sum = fund?.plus(3)
        println(sum)
    }

    fun sumFund2() {
        val user = mUser ?: throw NullPointerException("user is null")
        val account = user.mAccount ?: throw NullPointerException("account is null")
        val fund = account.mFund ?: throw NullPointerException("fund is null")
        val sum = fund + 3
        println(sum)
    }

    var mUser2: User? = null
//    fun testSmartCast1() {
//        if (mUser2 == null) throw NullPointerException("mUser2 is null,please check!")
//        val account = mUser2.mAccount
//    }

    fun testSmartCast2() {
        val user = mUser2
        Log.d(TAG, "testSmartCast2:${Thread.currentThread()}, $user,$mUser2")
        Thread {
            Thread.sleep(3000)
            Log.d(TAG, "testSmartCast2:${Thread.currentThread()}, $user,$mUser2")
        }.start()
    }
}
