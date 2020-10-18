package com.example.ai_wrongnote.data

class CommendData( val commend_data: String,
                   val commend_data_image: String,
                   val know_point: String,
                   val how_hard: String,
                   val answer: String,
                   val answer_detail: String,
                   val answer_detail_image: String)
{ //改变表结构之后决定在推荐习题那里去掉掌握程度  删掉val how_control:String,

    override fun toString(): String {
        return "题干：$commend_data | 题干图片：$commend_data_image |知识点：$know_point | 难度：$how_hard  | 答案：$answer |答案详情：$answer_detail|答案详情图片：$answer_detail_image "
    }

}