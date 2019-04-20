package com.gy.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.activity_functional.*

class FunctionalActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functional)
        initListener()
    }

    private fun initListener() {
        af_b_btn1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick1: ")
            }
        })
        af_b_btn1.setOnClickListener { Log.d(TAG, "onClick2: ") }

        af_b_btn3.setOnClickListener {
            val list = listOf("1", "2", "a", "..", "3", "--", "4", "5", "6")
            val sum = list.filter { isInt(it) }.map { it.toInt() }.filter { it > 3 }.sum()
            Log.d(TAG, ": $sum")
        }

        af_b_btn4.setOnClickListener {
            sumFun()
        }
    }

    @SuppressLint("CheckResult")
    private fun sumFun() {
        val list = listOf("1", "2", "a", "..", "3", "--", "4", "5", "6")
        list
            .toObservable()
            .filter { isInt(it) }
            .map { it.toInt() }
            .filter { it > 3 }
            .toList()
            .map { it.sum() }
            .flatMapObservable { Observable.just(it) }
            .subscribe(
                { Log.d(TAG, ": $it") },
                { Log.e(TAG, ": " + it.localizedMessage) },
                { Log.d(TAG, ":compelt ")
            })
    }

    private fun isInt(it: String): Boolean {
        return try {
            it.toInt()
            true
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            false
        }
    }
}
