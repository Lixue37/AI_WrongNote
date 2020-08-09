package com.example.ai_wrongnote.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_wrongnote.itemView.NoteListItemView
import data.NoteListItem

class NoteAdapter(
    val context: Context,
    val noteListItems: MutableList<NoteListItem>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //绑定的第二步，实现这三个函数  (给recycleView绑定条目。绑定完条目才是“点击条目跳转到相应页面的详情页”）
    //做完之后，recycleView的初始化就做好了
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteListItemViewHolder(NoteListItemView(context))
    }

    //需要真实数据。在无数据时可先定义固定的一个数值，表示有几条item
    override fun getItemCount(): Int =noteListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //需要真实数据做绑定
        //拿到holder hold住的item
        val noteListItemView = holder.itemView as NoteListItemView
        noteListItemView.bindView(noteListItems[position])
    }

    //创建一个viewHolder 为了让第一个函数有所返回。这个viewholder它hold住的是一个个条目
    class NoteListItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){

    }
}