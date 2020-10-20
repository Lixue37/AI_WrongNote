package com.example.ai_wrongnote.fragment


import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.ai_wrongnote.GameViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.activity.GameDetailActivity
import com.example.ai_wrongnote.activity.MainActivity
import com.example.ai_wrongnote.adapter.CommendAdapter
import com.example.ai_wrongnote.data.CommendData
import com.example.ai_wrongnote.data.GameData
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject


class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        onLoadGameNotes()
    }

    var score:Int = 0


    fun onLoadGameNotes() {
        //从服务器读出推荐习题的数据
        val queue =
            Volley.newRequestQueue(getActivity()?.getApplicationContext()) //getActivity()?.getApplicationContext() 或者 getActivity() getContext()
        val game_datas = mutableListOf<GameData>()

        game_datas.clear()

        val jsonArrayRequest = JsonArrayRequest(
            "http://47.102.140.185:8080/RecommendItem.ashx",
            Response.Listener {
                for (i in 0..it.length() - 1) {
                    val item = it.get(i) as JSONObject

                    val sqgame_data = item["commend_data"] as String
                    val game_data_image = item["commend_data_image"] as String

                    val game_data = GameData(sqgame_data, game_data_image)
                    game_datas.add(game_data)

                }
            },
            Response.ErrorListener {
                //lable1.text = it.message
                toast("请求数据失败")
            }
        )

        queue.add(jsonArrayRequest)


        //全局变量score记录分数并显示
        score_actvt.text = score.toString()


        //apple1-6的代码相似 我都是不会传值到下一个页面。注释掉的方法我试了却不行
        apple1.setOnClickListener {
            val intent = Intent(context,GameDetailActivity::class.java)
            intent.putExtra("commend_data",game_datas[1].game_data)
            intent.putExtra("commend_data_image",game_datas[1].game_data_image)
            //页面之间如何传值？？？！！！！！！！！？？？？？？？？？？？？？？？？？？？？？？ 还差一步，就是把值传给详情页面，在详情页面显示。
            //context?.startActivity<GameDetailActivity>()
            //context?.startActivity(intent)

            /*toast(game_datas[1].game_data)
            * 尝试把读到的数据用toast打出来，发现可以打得出来 说明正确获取到了值。只剩下把值传过去了。*/


        }

        apple2.setOnClickListener {
            val intent = Intent(context,GameDetailActivity::class.java)
            intent.putExtra("commend_data",game_datas[2].game_data)
            intent.putExtra("commend_data_image",game_datas[2].game_data_image)
            //页面之间如何传值？？？！！！！！！！！？？？？？？？？？？？？？？？？？？？？？？ 还差一步，就是把值传给详情页面，在详情页面显示。
            //context?.startActivity<GameDetailActivity>()
            //context?.startActivity(intent)
            //toast(game_datas[1].game_data)
            //context?.startActivity<GameDetailActivity>()

        }

        apple3.setOnClickListener {
            val intent = Intent(context,GameDetailActivity::class.java)
            intent.putExtra("commend_data",game_datas[3].game_data)
            intent.putExtra("commend_data_image",game_datas[3].game_data_image)
            //页面之间如何传值？？？！！！！！！！！？？？？？？？？？？？？？？？？？？？？？？ 还差一步，就是把值传给详情页面，在详情页面显示。
            //context?.startActivity<GameDetailActivity>()
            //context?.startActivity(intent)
            //toast(game_datas[1].game_data)
            //context?.startActivity<GameDetailActivity>()

        }

        apple4.setOnClickListener {
            val intent = Intent(context,GameDetailActivity::class.java)
            intent.putExtra("commend_data",game_datas[4].game_data)
            intent.putExtra("commend_data_image",game_datas[4].game_data_image)
            //页面之间如何传值？？？！！！！！！！！？？？？？？？？？？？？？？？？？？？？？？ 还差一步，就是把值传给详情页面，在详情页面显示。
            //context?.startActivity<GameDetailActivity>()
            //context?.startActivity(intent)
            //toast(game_datas[1].game_data)
            //context?.startActivity<GameDetailActivity>()

        }

        apple5.setOnClickListener {
            val intent = Intent(context,GameDetailActivity::class.java)
            intent.putExtra("commend_data",game_datas[5].game_data)
            intent.putExtra("commend_data_image",game_datas[5].game_data_image)
            //页面之间如何传值？？？！！！！！！！！？？？？？？？？？？？？？？？？？？？？？？ 还差一步，就是把值传给详情页面，在详情页面显示。
            //context?.startActivity<GameDetailActivity>()
            //context?.startActivity(intent)
            //toast(game_datas[1].game_data)
            //context?.startActivity<GameDetailActivity>()

        }

        apple6.setOnClickListener {
            val intent = Intent(context,GameDetailActivity::class.java)
            intent.putExtra("commend_data",game_datas[6].game_data)
            intent.putExtra("commend_data_image",game_datas[6].game_data_image)
            //页面之间如何传值？？？！！！！！！！！？？？？？？？？？？？？？？？？？？？？？？ 还差一步，就是把值传给详情页面，在详情页面显示。
            //context?.startActivity<GameDetailActivity>()
            //context?.startActivity(intent)
            //toast(game_datas[1].game_data)
            //context?.startActivity<GameDetailActivity>()

        }



    }





}


