package com.example.ai_wrongnote.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.ai_wrongnote.NoteViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.adapter.NoteAdapter
import data.NoteData
import kotlinx.android.synthetic.main.note_fragment.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.json.JSONObject


class NoteFragment : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }

    //上下语句是自动生成的
    private lateinit var viewModel: NoteViewModel

    /*没写BaseFragment的后果就是，onCreateView函数和onViewCreated函数每次都要写。另外onActivityCreated
    目前应该用不到,可以删掉*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    //先加载数据
    fun loadNotes() {
        //读取json数据的实现
        val queue =
            Volley.newRequestQueue(getActivity()?.getApplicationContext()) //getActivity()?.getApplicationContext() 或者 getActivity() getContext()
        val note_datas = mutableListOf<NoteData>()


        //button.setOnClickListener {
        note_datas.clear()
        val jsonArrayRequest = JsonArrayRequest(
            "http://47.102.140.185:8080/TestHandler2.ashx",
            Response.Listener {
                for (i in 0..it.length() - 1) {
                    val item = it.get(i) as JSONObject
                    val know_point = item["know_point"] as String
                    val word_data = item["word_data"] as String
                    val how_hard = item["how_hard"] as String
                    val how_control = item["how_control"] as String

                    val note_data =
                        NoteData(know_point, word_data, how_hard, how_control)
                    note_datas.add(note_data)

//                    lable1.append("${commend_data}\n")

                    note_recyclerview.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        //绑定第三步。做好adapter之后再回来绑定就可以
                        adapter = NoteAdapter(context, note_datas)
                    }
                }
            },
            Response.ErrorListener {
                //lable1.text = it.message
                toast("请求数据失败")
            }
        )

        queue.add(jsonArrayRequest)
        //加载成功之后
        onLoadNotesSuccess()
    }



    //这是view层
    fun init() {
        //下拉刷新的进度条
        swipeRefresh.apply {
            setColorSchemeResources(R.color.GreenDark)
            isRefreshing = true
            //设置监听器，当用户手动下拉刷新的时候也要刷新数据
            setOnRefreshListener {
                loadNotes()
            }
        }

        loadNotes()

    }
    fun onLoadNotesSuccess() {
        //加载成功之后要刷新一下数据
        swipeRefresh.isRefreshing = false
        context?.toast("加载用户习题成功")
    }


}
