package com.example.ai_wrongnote.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ai_wrongnote.HomeViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.TestActivity
import com.example.ai_wrongnote.activity.GetQuestionActivity
import com.example.ai_wrongnote.adapter.CommendAdapter
import com.example.ai_wrongnote.data.CommendData
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.*


class HomeFragment : Fragment() {

    lateinit var photoUri: Uri

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)


        //拍照功能实现
        camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
        }

        init()
    }

//以下是recyclerview的相关代码  (新版）

    fun loadNotes() {
        //读取json数据的实现
        val queue =
            Volley.newRequestQueue(getActivity()?.getApplicationContext()) //getActivity()?.getApplicationContext() 或者 getActivity() getContext()
        val commend_datas = mutableListOf<CommendData>()

        //button.setOnClickListener {
        commend_datas.clear()
        val jsonArrayRequest = JsonArrayRequest(
            "http://47.102.140.185:8080/RecommendItem.ashx",
            Response.Listener {
                for (i in 0..it.length() - 1) {
                    val item = it.get(i) as JSONObject
                    val know_point = item["know_point"] as String
                    val note_data = item["note_data"] as String
                    val how_hard = item["how_hard"] as Double
                    //val how_control = item["how_control"] as String
                    val answer = item["answer"] as String

                    val commend_data =
                        CommendData(know_point, note_data, how_hard, answer)
                    commend_datas.add(commend_data)

//                    lable1.append("${commend_data}\n")

                    commend_recyclerview.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        //绑定第三步。做好adapter之后再回来绑定就可以
                        adapter = CommendAdapter(context, commend_datas)
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

    fun init() {
        swipeRefresh_home.apply {
            setColorSchemeResources(R.color.GreenDark)
            isRefreshing = true
            //设置监听器，当用户手动下拉刷新的时候也要刷新数据
            setOnRefreshListener {
                loadNotes()
            }

            loadNotes()
        }

        //“我要推荐” 按钮监听事件，点击之后向服务器发送信号，开始运行算法
        btn_recommend.setOnClickListener {
            val queue = Volley.newRequestQueue(getActivity()?.getApplicationContext())
            var stringRequest = StringRequest(
                "http://47.102.140.185:33060/recommend",
                Response.Listener {
                    val str = it.toString()
                    //Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
                    toast(str)
                },
                Response.ErrorListener {
                    //lable1.text = it.message
                    toast("推荐习题失败！请检查原因！")
                }
            )
            queue.add(stringRequest)
        }


        //测试post请求的按钮调用
        /*预留，供测试使用！ 按钮的名字叫做inter_test，但美观起见先在layout里把该按钮删掉了
        inter_test.setOnClickListener {
            context?.startActivity<TestActivity>()
        }*/
    }

    fun onLoadNotesSuccess() {
        //加载成功之后要刷新一下数据
        swipeRefresh_home.isRefreshing = false
        context?.toast("加载推荐习题成功")
    }
    //以上是recyclerview的相关代码


    // 重载onActivityResult方法
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //拍照功能
        if (resultCode == Activity.RESULT_OK) {
            val sdStatus = Environment.getExternalStorageState()

            if (sdStatus != Environment.MEDIA_MOUNTED) {
                Toast.makeText(context, getString(R.string.insufficient_memory), Toast.LENGTH_SHORT)
                    .show()
                return
            }

            val filename = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA))
                .toString() + ".jpg"
            val bundle = data!!.extras
            val bitmap = bundle?.get("com/example/ai_wrongnote/data") as Bitmap

            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val file = File(path, filename)
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            photoUri = Uri.fromFile(file)

            fos.flush()
            fos.close()

            //bitmap = createPhotos(bitmap);

            imageView3.setImageBitmap(bitmap)

            val intent = Intent(
                context,
                GetQuestionActivity::class.java
            )
            intent.putExtra("photoUri", photoUri.toString())
            startActivity(intent)
        }
    }


    //让图片旋转90度
    fun createPhotos(bitmap: Bitmap?): Bitmap {
        var bitmap = bitmap
        if (bitmap != null) {
            val m = Matrix()
            try {
                m.setRotate(
                    90F,
                    (bitmap.width / 2).toFloat(),
                    (bitmap.height / 2).toFloat()
                ) //90就是我们需要选择的90度
                val bmp2 = Bitmap.createBitmap(
                    bitmap,
                    0,
                    0,
                    bitmap.width,
                    bitmap.height,
                    m,
                    true
                )
                bitmap.recycle()
                bitmap = bmp2
            } catch (ex: Exception) {
                print("创建图片失败！$ex")
            }
        }
        return bitmap!!
    }
}


