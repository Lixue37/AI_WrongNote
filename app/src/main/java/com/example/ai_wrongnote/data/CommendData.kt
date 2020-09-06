package com.example.ai_wrongnote.data

class CommendData(val know_point:String, val note_data:String,
                  val how_hard:String, val how_control:String, val answer:String) {

    override fun toString(): String {
        return "知识点：$know_point | 题干：$note_data | 难度：$how_hard | 掌握程度：$how_control | 答案：$answer"
    }

}