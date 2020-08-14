package com.example.ai_wrongnote.itemView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.ai_wrongnote.R
import data.CommendListItem
import kotlinx.android.synthetic.main.item_commend.view.*

class CommendListItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_commend,this)
    }

    //在这里给UI上对应位置的变量赋值！！！
    fun bindView(commendListItem: CommendListItem) {
        commend_point_text.text = commendListItem.CommendPoint
        //中间还有个图片的绑定。暂留
        commend_data_text.text = commendListItem.CommendData
    }
}