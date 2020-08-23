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
        //首先隐藏错题答案图片 以及掌握程度按钮
        commend_answer_text.visibility = View.INVISIBLE
        how_control_text.visibility = View.INVISIBLE
        half_btn.visibility = View.INVISIBLE
        few_btn.visibility = View.INVISIBLE
        little_btn.visibility = View.INVISIBLE
        join_btn.visibility = View.INVISIBLE

        //获取该页面上的五个数据
        val commend_point_data = intent.getStringExtra("point_data")
        commend_point_data_actvt_text.text = commend_point_data
        val commend_data = intent.getStringExtra("commend_data")
        commend_data_actvt_text.text = commend_data
        val how_hard = intent.getStringExtra("how_hard")
        commend_how_hard_data_text.text = how_hard
        val answer = intent.getStringExtra("answer")
        commend_answer_text.text = answer

        look_btn.setOnClickListener { commend_answer_text.visibility = View.VISIBLE }
        close_btn.setOnClickListener { commend_answer_text.visibility = View.INVISIBLE }
        yes_btn.setOnClickListener {
            how_control_text.visibility = View.VISIBLE
            half_btn.visibility = View.VISIBLE
            few_btn.visibility = View.VISIBLE
            little_btn.visibility = View.VISIBLE
            join_btn.visibility = View.VISIBLE
        }
    }




}