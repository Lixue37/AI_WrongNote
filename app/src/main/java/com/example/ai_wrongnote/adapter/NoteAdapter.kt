package com.example.ai_wrongnote.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.activity.NoteDetailActivity
import data.NoteData

class NoteAdapter (val context: Context, val noteList:List<NoteData>):RecyclerView.Adapter<NoteAdapter.Holder>(){

    inner class  Holder(itemView:View):RecyclerView.ViewHolder(itemView){
        val know_point_text_view = itemView.findViewById<TextView>(R.id.know_point_item_text)
        val word_data_test_view = itemView.findViewById<TextView>(R.id.word_data_test)

        val commend_itemview = itemView.findViewById<ConstraintLayout>(R.id.cl_note_item)

        fun bind(position:Int){
            know_point_text_view.text = noteList[position].know_point
            word_data_test_view.text = noteList[position].word_data

            commend_itemview.setOnClickListener{
                //Toast.makeText(context,noteList[position].answer, Toast.LENGTH_SHORT).show()

                //进行页面跳转并且传递数据
                val intent = Intent(context, NoteDetailActivity::class.java)
                intent.putExtra("know_point",noteList[position].know_point)
                intent.putExtra("how_control",noteList[position].how_control)
                intent.putExtra("how_hard",noteList[position].how_hard)
                context.startActivity(intent)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val commend_view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)

        return Holder(commend_view)
    }

    override fun getItemCount(): Int =noteList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }


}

/*
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

        val knowPoint = noteListItems[position].NotePoint
        //点击item跳转到对应的详情页面 首先在这里设置监听事件
        noteListItemView.setOnClickListener{ context.startActivity<NoteDetailActivity>("knowpoint_data_text" to knowPoint) }
        //跳转过去时要传递过去对应的数据，所以在上一句的上面先定义好变量
    }

    //创建一个viewHolder 为了让第一个函数有所返回。这个viewholder它hold住的是一个个条目
    class NoteListItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){

    }
}*/