package com.gy.myapplication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        am_b_npe.setOnClickListener { go(NpeActivity::class.java) }
        am_b_fun1.setOnClickListener { go(FunctionalActivity::class.java) }
        am_b_fun2.setOnClickListener { go(Functional2Activity::class.java) }

        
    }
}
