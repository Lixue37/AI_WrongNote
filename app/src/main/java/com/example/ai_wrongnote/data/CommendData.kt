package com.example.ai_wrongnote.data

class CommendData(val know_point:String, val note_data:String,
                  val how_hard:Double,  val answer:String) { //改变表结构之后决定在推荐习题那里去掉掌握程度  删掉val how_control:String,

    override fun toString(): String {
        return "知识点：$know_point | 题干：$note_data | 难度：$how_hard  | 答案：$answer"
    }

}