package com.example.ai_wrongnote.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.activity_note.*

class NoteDetailActivity:AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_note)
          init()
     }

     //NoteAdapter里在跳转的时候传递了数据，但是如果要显示出来数据，还需要在这里进行显示
     fun init(){
          initAllData()
     }

     private fun initAllData() {
          how_control_text.visibility = View.INVISIBLE
          control_btn_100.visibility = View.INVISIBLE
          control_btn_80.visibility = View.INVISIBLE
          control_btn_60.visibility = View.INVISIBLE
          control_btn_40.visibility = View.INVISIBLE
          control_btn_20.visibility = View.INVISIBLE
          how_hard_text.visibility = View.INVISIBLE
          easy_btn.visibility = View.INVISIBLE
          soso_btn.visibility = View.INVISIBLE
          hard_btn.visibility = View.INVISIBLE
          join_btn.visibility = View.INVISIBLE


          //获取页面上的三个数据
          val know_point_data = intent.getStringExtra("know_point")
          know_point_data_actvt_text.text = know_point_data
          val how_hard_data = intent.getStringExtra("how_hard")
          how_control_data_actvt_text.text = how_hard_data
          val how_control_data = intent.getStringExtra("how_control")
          how_hard_data_actvt_text.text = how_control_data

          yes_btn.setOnClickListener {
               how_control_text.visibility = View.VISIBLE
               control_btn_100.visibility = View.VISIBLE
               control_btn_80.visibility = View.VISIBLE
               control_btn_60.visibility = View.VISIBLE
               control_btn_40.visibility = View.VISIBLE
               control_btn_20.visibility = View.VISIBLE
               how_hard_text.visibility = View.VISIBLE
               easy_btn.visibility = View.VISIBLE
               soso_btn.visibility = View.VISIBLE
               hard_btn.visibility = View.VISIBLE
               join_btn.visibility = View.VISIBLE
          }

     }


}