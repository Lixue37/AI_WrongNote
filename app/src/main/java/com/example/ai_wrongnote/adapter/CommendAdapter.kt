package com.example.ai_wrongnote.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_wrongnote.activity.CommendDetailActivity
import com.example.ai_wrongnote.itemView.CommendListItemView
import data.CommendListItem
import org.jetbrains.anko.startActivity

class CommendAdapter(val context: Context,val commendListItems: MutableList<CommendListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommendListItemViewHolder(CommendListItemView(context))
    }

    class CommendListItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!) {

    }

    override fun getItemCount(): Int =commendListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //需要真实数据做绑定
        //拿到holder hold住的item
        val commendListItemView = holder.itemView as CommendListItemView
        commendListItemView.bindView(commendListItems[position])

        val commendPoint = commendListItems[position].CommendPoint
        val commendData=commendListItems[position].CommendData
        //点击item跳转到对应的详情页面 首先在这里设置监听事件
        commendListItemView.setOnClickListener{ context.startActivity<CommendDetailActivity>("commend_point_data_text" to commendPoint,"commend_data_text" to commendData) }
        //跳转过去时要传递过去对应的数据，所以在上一句的上面先定义好变量

    }

}