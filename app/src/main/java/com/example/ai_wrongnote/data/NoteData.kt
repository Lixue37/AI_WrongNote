package com.example.ai_wrongnote.data

class NoteData(val note_data:String,
               val note_data_image:String,
               val note_answer_image:String,
               val note_point:String,
               val note_hard:String,
               val note_control:String) {

    override fun toString(): String {
        return "文字题干：$note_data | 题干图片：$note_data_image | 笔记图片：$note_answer_image | 知识点标签:$note_point | 主观难度:$note_hard| 掌握程度：$note_control "
    }

}