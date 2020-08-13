package com.example.ai_wrongnote.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.activity_commend.*

class CommendDetailActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commend)
        init()
    }

    //NoteAdapter里在跳转的时候传递了数据，但是如果要显示出来数据，还需要在这里进行显示
    fun init(){
        initAllData()
    }

    private fun initAllData() {
        //首先隐藏错题答案图片
        commend_answer_text.visibility = View.INVISIBLE

        //获取该页面上的五个数据
        val commend_point_data = intent.getStringExtra("commend_point_data_text")
        commend_point_data_text.text=commend_point_data
        val commend_data = intent.getStringExtra("commend_data_text")
        commend_data_text.text=commend_data
    }


}