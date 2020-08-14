package com.example.ai_wrongnote.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.get_question_activity.*
import org.jetbrains.anko.startActivity

class GetQuestionActivity : AppCompatActivity() {

    lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_question_activity)

        photoUri = intent.extras!!.getString("photoUri")!!.toUri()
        textView3.text = "$photoUri" // 多余测试代码

        img_question.setImageURI(photoUri)

        save.setOnClickListener {
            startActivity<GetAnswerActivity>()
        }

    }
}