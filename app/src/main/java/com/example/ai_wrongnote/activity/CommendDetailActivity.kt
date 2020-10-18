package com.example.ai_wrongnote.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.activity_commend.*
import org.jetbrains.anko.image
import org.jetbrains.anko.toast


class CommendDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commend)
        init()
    }

    //CommendAdapter里在跳转的时候传递了数据，但是如果要显示出来数据，还需要在这里进行显示
    fun init() {
        initAllData()
    }

    fun initAllData() {
        //首先隐藏错题答案图片 以及掌握程度按钮
        commend_answer_text.visibility = View.INVISIBLE
        textView10.visibility = View.INVISIBLE
        answer_detail_actvt_text.visibility = View.INVISIBLE
        answer_detail_image_actvt.visibility = View.INVISIBLE

        how_control_text.visibility = View.INVISIBLE
        control_list.visibility = View.INVISIBLE
        join_btn.visibility = View.INVISIBLE


        /*Adapter已经绑定好数据，这里负责显示该页面上的 7 列数据*/
        //题干文本框显示
        val commend_data = intent.getStringExtra("commend_data")
        commend_data_actvt_text.text = commend_data
        //题干补充图片的显示（暂且将读出的图片名绑定道文本框中。后续可以根据名称绑定相应图片 ！！！！！！ 布局中图片的命名为commend_data_image_actvt
        val commend_data_image = intent.getStringExtra("commend_data_image")
        if (commend_data_image=="0"){
            commend_data_image_txt.text ="暂无图片"
            //此处无需绑定默认图片
        }
        else{
            commend_data_image_txt.text = commend_data_image
            //预留了绑定图片的位置

        }

        //知识点文本框显示
        val know_point = intent.getStringExtra("know_point")
        /*处理数据库中数据为空的问题。在转成json时，就已经将string类型的空数据转成 "0" 将double类型的数据转成 0.0 */
        if (know_point=="0")
            commend_point_data_actvt_text.text ="暂无标签"
        else
            commend_point_data_actvt_text.text = know_point

        //难度文本框显示
        val how_hard = intent.getStringExtra("how_hard")
        if (how_hard=="0.0")
            commend_how_hard_data_text.text = "-1.0"
        else
            commend_how_hard_data_text.text = how_hard

        //答案文本框显示
        val answer = intent.getStringExtra("answer")
        if (answer=="0")
            commend_answer_text.text = "暂无答案"
        else
            commend_answer_text.text = answer

        //答案详解文本框显示
        val answer_detail = intent.getStringExtra("answer_detail")
        if (answer_detail=="0")
            answer_detail_actvt_text.text ="暂无详解"
        else
            answer_detail_actvt_text.text = answer_detail

        //答案详解图片显示（暂且将读出的图片名绑定道文本框中。后续可以根据名称绑定相应图片 ！！！！！！！布局中图片的命名为answer_detail_image_actvt
        val answer_detail_image = intent.getStringExtra("answer_detail_image")
        if (answer_detail_image=="0"){
            answer_detail_image_txt.text ="暂无图片"
            //此处无需绑定默认图片
        }
        else{
            answer_detail_image_txt.text = answer_detail_image
            //预留了绑定图片的位置

        }



        /*测试能否拿到数据*/
        //Toast.makeText(this,commend_point_data.toString(),Toast.LENGTH_SHORT).show()

        //查看和关闭答案的开关
        look_btn.setOnClickListener {
            commend_answer_text.visibility = View.VISIBLE
            textView10.visibility = View.VISIBLE
            answer_detail_actvt_text.visibility = View.VISIBLE
            answer_detail_image_actvt.visibility = View.VISIBLE
        }
        close_btn.setOnClickListener {
            commend_answer_text.visibility = View.INVISIBLE
            textView10.visibility = View.INVISIBLE
            answer_detail_actvt_text.visibility = View.INVISIBLE
            answer_detail_image_actvt.visibility = View.INVISIBLE
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

            val how_use:Float =control_int + how_hard.toFloat()

            //“加入错题本”按钮监听事件
            join_btn.setOnClickListener {
                //在此将信息传入服务器上的userA表
                /*
                * 文字题干：commend_data  （string）
                * 题干图片：commend_data_image  （string）
                * 笔记图片（就是这里的答案详情图片）：answer_detail_image （string）
                * 知识点标签：commend_point_data  （string）
                * 主观难度（其实此处是客观难度）：how_hard.toFloat()
                * 掌握程度：control_int （整型）
                * 有用程度：how_use  (Float型）
                * 时间戳：时间戳我没做                *
                * */
            }

        }

    }


}