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
          //首先隐藏错题答案图片
          note_photo_answer.visibility = View.INVISIBLE

          //获取该页面上的五个数据
          val knowpoint_data = intent.getStringExtra("knowpoint_data_txt")
          knowpoint_tui_data_txt.text=knowpoint_data
     }

     //fun getLayoutResId():Int = R.layout.activity_note
}