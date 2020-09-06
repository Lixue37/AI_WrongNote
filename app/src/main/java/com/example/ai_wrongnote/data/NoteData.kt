package com.example.ai_wrongnote.data

class NoteData(val know_point:String, val word_data:String,
               val how_hard:String, val how_control:String) {

    override fun toString(): String {
        return "知识点：$know_point | 题干：$word_data | 难度：$how_hard | 掌握程度：$how_control "
    }

}