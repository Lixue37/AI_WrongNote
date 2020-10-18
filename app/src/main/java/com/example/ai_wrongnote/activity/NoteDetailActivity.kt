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
          note_photo_answer.visibility = View.INVISIBLE
          how_control_text.visibility = View.INVISIBLE
          how_hard_text.visibility = View.INVISIBLE
          radioGroup1.visibility = View.INVISIBLE
          radioGroup2.visibility = View.INVISIBLE


          /*Adapter已经绑定好数据，这里负责显示该页面上的 6 列数据*/
          //题干文本框
          val note_data = intent.getStringExtra("note_data")
          note_data_actvt_txt.text = note_data

          //题干图片
          val note_data_image = intent.getStringExtra("note_data_image")
          if (note_data_image=="0"){
               note_photo_problem_txt.text ="暂无题干图片"
               //此处无需绑定默认图片
          }else{
               note_photo_problem_txt.text = note_data_image
               //预留了绑定图片的位置


          }

          //答案图片 or 原笔迹图片
          val note_answer_image = intent.getStringExtra("note_answer_image")
          if(note_answer_image=="0"){
               note_photo_answer_txt.text = "暂无原笔迹或者答案图片"
          }else{
               note_photo_answer_txt.text =note_answer_image
               //预留了绑定图片的位置


          }

          //知识点
          val note_point = intent.getStringExtra("note_point")
          if (note_point=="0"){
               know_point_data_actvt_text.text="暂无知识点标签"
          }else{
               know_point_data_actvt_text.text = note_point
          }

          //掌握程度 不可能为空。用户录入错题必须选择掌握程度
          val note_hard = intent.getStringExtra("note_hard")
          how_control_data_actvt_text.text = note_hard

          //习题难度 可能为空 因为推荐的习题里有的难度为空
          val note_control = intent.getStringExtra("note_control")
          if(note_control=="0.0"){
               how_hard_data_actvt_text.text = "暂无难度"
          }else{
               how_hard_data_actvt_text.text =note_control
          }


          //查看原笔记 开关
          look_btn.setOnClickListener {
               note_photo_answer.visibility = View.VISIBLE
          }
          //关闭图片 开关
          close_btn.setOnClickListener {
               note_photo_answer.visibility = View.INVISIBLE
          }

          //修改难度和掌握程度
          yes_btn.setOnClickListener {
               how_control_text.visibility = View.VISIBLE
               how_hard_text.visibility = View.VISIBLE
               radioGroup1.visibility = View.VISIBLE
               radioGroup2.visibility = View.VISIBLE
          }

     }


}