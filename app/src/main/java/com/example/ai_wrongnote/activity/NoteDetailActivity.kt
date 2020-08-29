package com.example.ai_wrongnote.activity

import android.os.Bundle
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
          //获取页面上的三个数据
          val know_point_data = intent.getStringExtra("know_point")
          know_point_data_actvt_text.text = know_point_data
          val how_hard_data = intent.getStringExtra("how_hard")
          how_control_data_actvt_text.text = how_hard_data
          val how_control_data = intent.getStringExtra("how_control")
          how_hard_data_actvt_text.text = how_control_data

     }


}