package com.example.ai_wrongnote.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.activity_commend.*
import org.jetbrains.anko.toast

class CommendDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commend)
        init()
    }

    //NoteAdapter里在跳转的时候传递了数据，但是如果要显示出来数据，还需要在这里进行显示
    fun init() {
        initAllData()
    }

    fun initAllData() {
        //首先隐藏错题答案图片 以及掌握程度按钮
        commend_answer_text.visibility = View.INVISIBLE
        how_control_text.visibility = View.INVISIBLE
        control_btn_100.visibility = View.INVISIBLE
        control_btn_80.visibility = View.INVISIBLE
        control_btn_60.visibility = View.INVISIBLE
        control_btn_40.visibility = View.INVISIBLE
        control_btn_20.visibility = View.INVISIBLE
        join_btn.visibility = View.INVISIBLE

        //获取该页面上的五个数据
        val commend_point_data = intent.getStringExtra("point_data")
        commend_point_data_actvt_text.text = commend_point_data
        //测试能否拿到数据
        //Toast.makeText(this,commend_point_data.toString(),Toast.LENGTH_SHORT).show()


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
            control_btn_100.visibility = View.VISIBLE
            control_btn_80.visibility = View.VISIBLE
            control_btn_60.visibility = View.VISIBLE
            control_btn_40.visibility = View.VISIBLE
            control_btn_20.visibility = View.VISIBLE
            join_btn.visibility = View.VISIBLE
        }
    }


}