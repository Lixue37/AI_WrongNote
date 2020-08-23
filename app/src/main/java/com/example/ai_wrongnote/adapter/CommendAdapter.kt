package com.example.ai_wrongnote.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.activity.CommendDetailActivity
import data.CommendData


//以下是按照jp的实例，把数据绑定的工作在一个adapter里完成，不再分成好几个类的方式
class CommendAdapter(val context: Context, val commendList:List<CommendData>):RecyclerView.Adapter<CommendAdapter.Holder>(){

        inner class  Holder(itemView:View):RecyclerView.ViewHolder(itemView){
            //命名这里之所以多一个view是因为考虑到它就是一个中间变量，给下面赋值用的。避免混淆。
            val commend_point_text_view = itemView.findViewById<TextView>(R.id.commend_point_item_text)
            val commend_data_text_view = itemView.findViewById<TextView>(R.id.commend_data_item_text)

            val commend_itemview = itemView.findViewById<ConstraintLayout>(R.id.cl_comment_item)

            fun bind(position:Int){
                commend_point_text_view.text = commendList[position].know_point
                commend_data_text_view.text = commendList[position].note_data

                commend_itemview.setOnClickListener{
                    Toast.makeText(context,commendList[position].answer,Toast.LENGTH_SHORT).show()

                    //进行页面跳转并且传递数据
                    val intent = Intent(context,CommendDetailActivity::class.java)
                    intent.putExtra("commend_data",commendList[position].note_data)
                    intent.putExtra("point_data",commendList[position].know_point)
                    intent.putExtra("how_hard",commendList[position].how_hard)
                    intent.putExtra("answer",commendList[position].answer)
                    context.startActivity(intent)

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val commend_view = LayoutInflater.from(context).inflate(R.layout.item_commend,parent,false)

            return Holder(commend_view)
        }

        override fun getItemCount(): Int =commendList.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(position)
        }

    }



/*以下是按照B站视频中绑定recyclerview的方式，使用xxxadapter和 xxxListItemView和 xxxListItem 配套的方式。
class CommendAdapter(val context: Context,val commendList: MutableList<CommendListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return CommendListItemViewHolder(CommendListItemView(context))
    }

    class CommendListItemViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!) {

    }

    override fun getItemCount(): Int =commendList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //需要真实数据做绑定
        //拿到holder hold住的item
        val commendListItemView = holder.itemView as CommendListItemView
        commendListItemView.bindView(commendList[position])

        val commendPoint = commendList[position].CommendPoint
        val commendData=commendList[position].CommendData
        //点击item跳转到对应的详情页面 首先在这里设置监听事件
        commendListItemView.setOnClickListener{ context.startActivity<CommendDetailActivity>("commend_point_data_text" to commendPoint,"commend_data_text" to commendData) }
        //跳转过去时要传递过去对应的数据，所以在上一句的上面先定义好变量

    }

}*/