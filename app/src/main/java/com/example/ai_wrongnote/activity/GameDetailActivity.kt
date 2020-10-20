package com.example.ai_wrongnote.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.game_detail.*

class GameDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_detail)

        //进行页面跳转并且传递数据
        val commend_data = intent.getStringExtra("commend_data")
        game_note_data_actvt.text = commend_data

        //依旧是把图片的名字显示在文本框里了，需要进行图片绑定。 代表图片名的变量：commend_data_image
        val commend_data_image = intent.getStringExtra("game_data_image")
        textView11.text = commend_data_image
        //需要绑定图片！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！


        yesbtn.setOnClickListener {
            //表示做对的按钮点击之后，跳转回GameFragment，并且告诉全局变量score+1

        }

        nobtn.setOnClickListener {
            //表示做错的按钮点击之后，跳转回GameFragment，并且告诉全局变量score+0

        }

    }

}