package com.example.ai_wrongnote.activity

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
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
        textView10.visibility = View.INVISIBLE
        answer_detail.visibility = View.INVISIBLE
        answer_detail_image.visibility = View.INVISIBLE

        how_control_text.visibility = View.INVISIBLE
        control_list.visibility = View.INVISIBLE
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

        //查看和关闭答案的开关
        look_btn.setOnClickListener {
            commend_answer_text.visibility = View.VISIBLE
            textView10.visibility = View.VISIBLE
            answer_detail.visibility = View.VISIBLE
            answer_detail_image.visibility = View.VISIBLE
        }
        close_btn.setOnClickListener {
            commend_answer_text.visibility = View.INVISIBLE
            textView10.visibility = View.INVISIBLE
            answer_detail.visibility = View.INVISIBLE
            answer_detail_image.visibility = View.INVISIBLE
        }

        //加入错题本的开关
        yes_btn.setOnClickListener {
            how_control_text.visibility = View.VISIBLE
            control_list.visibility = View.VISIBLE
            join_btn.visibility = View.VISIBLE

            var control_int: Int=0
            //掌握程度单选框 监听事件 掌握程度，1代表完全不懂。5代表完全懂。
            control_list.setOnCheckedChangeListener { group, checkedId ->

                when(checkedId){
                    R.id.control1->{
                        control_int =1

                    }
                    R.id.control5->{
                        control_int =1
                        //toast(control_int.toString())
                    }
                    R.id.control2->{
                        control_int =3
                        //toast(control_int.toString())
                    }
                    R.id.control4->{
                        control_int =3
                        //toast(control_int.toString())
                    }
                    R.id.control3->{
                        control_int =5
                        //toast(control_int.toString())
                    }
                }
                //这个toast放在这里或者上面都可以
                toast(control_int.toString())
                //if(control1.id==checkedId || control5.id==checkedId) control_str=1 这种不要when的方法不行，虽然不知道为什么。会闪退并且自动重新打开app
            }

            val how_use:Double=control_int + how_hard.toDouble()

            //“加入错题本”按钮监听事件
            join_btn.setOnClickListener {
                //在此将信息传入本地的SQLite
                /*
                * 文字题干：commend_data
                * 题干图片：
                * 知识点标签：commend_point_data
                * 主观难度（其实此处是客观难度）：how_hard  （可能需要转成double型）
                * 掌握程度：control_int （整型）
                * 有用程度：how_use  (Double型）
                * 时间戳：（本地的sqlite不需要时间戳）                *
                * */
            }

        }

    }


}