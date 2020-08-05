package com.example.ai_wrongnote.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_wrongnote.itemView.NoteListItemView

class NoteAdapter(val context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //绑定的第二步，实现这三个函数
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteListItemViewHolder(NoteListItemView(context))
    }

    //需要真实数据。在无数据时可先定义固定的一个数值，表示有几条item
    override fun getItemCount(): Int =10

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //需要真实数据做绑定
    }

    //创建一个viewHolder 为了让第一个函数有所返回
    class NoteListItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){

    }
}