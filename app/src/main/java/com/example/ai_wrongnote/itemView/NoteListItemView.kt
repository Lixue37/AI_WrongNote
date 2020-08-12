package com.example.ai_wrongnote.itemView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.ai_wrongnote.R
import data.NoteListItem
import kotlinx.android.synthetic.main.item_note.view.*

class NoteListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.item_note,this)
    }

    //在这里给UI上对应位置的变量赋值！！！
    fun bindView(noteListItem: NoteListItem) {
        tuijian_point_text.text = noteListItem.NotePoint
        //中间还有个图片的绑定。暂留
        note_time_text.text = noteListItem.NoteTime
    }
}