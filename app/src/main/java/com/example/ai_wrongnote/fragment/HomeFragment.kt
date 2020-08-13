package com.example.ai_wrongnote.fragment

import android.app.Activity
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
import com.example.ai_wrongnote.HomeViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.activity.GetQuestionActivity
import com.example.ai_wrongnote.adapter.CommendAdapter
import com.example.ai_wrongnote.adapter.NoteAdapter
import data.CommendListItem
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.note_fragment.*
import org.jetbrains.anko.toast
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
            val intent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,1)
        }

        init()
    }

    //以下是recyclerview的相关代码

    fun init() {
        //homeText.text="初始化函数起效"
        //下拉刷新的进度条
        swipeRefresh_home.apply{
            setColorSchemeResources(R.color.GreenDark)
            isRefreshing = true
            //设置监听器，当用户手动下拉刷新的时候也要刷新数据
            setOnRefreshListener {
                loadNotes()
            }
        }

        //初始化recycleview 绑定的第一步
        commend_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            //绑定第三步。做好adapter之后再回来绑定就可以
            adapter= CommendAdapter(context,commendListItems)
        }

        //present层加载数据
        loadNotes()

    }

    fun onLoadNotesSuccess(){
        //加载成功之后要刷新一下数据
        swipeRefresh_home.isRefreshing = false
        commend_recyclerview.adapter?.notifyDataSetChanged()
        context?.toast("加载推荐习题成功")
    }

    val commendListItems = mutableListOf<CommendListItem>()

    //presenter层的实现
    fun loadNotes(){
        commendListItems.clear()


        val note_point_texts = context?.resources?.getStringArray(R.array.know_point)
        //val note_photo_items =
        // 暂时没写图片的列表
        val commend_data_texts = context?.resources?.getStringArray(R.array.commend_data)

        //读取完数据之后需要做转换。上面定义的变量只是代表加载出来的数据。还需把加载出来的数据转换成UI界面需要看到的数据
        note_point_texts?.forEach {
            val commendListItem = CommendListItem(it,commend_data_texts.toString())
            //把创建好的加入到可变的数据集合当中
            commendListItems.add(commendListItem)
            //然后去通知view层。让adapter去维护数据集合
        }

        //成功的话通知view层.但是现在暂未判断成功与否。默认成功了
        onLoadNotesSuccess()

    }

    //以上是recyclerview的相关代码


    // 重载onActivityResult方法
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            val sdStatus = Environment.getExternalStorageState()

            if (sdStatus != Environment.MEDIA_MOUNTED){
                Toast.makeText(context,getString(R.string.insufficient_memory), Toast.LENGTH_SHORT).show()
                return
            }

            val filename = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA))
                .toString() + ".jpg"
            val bundle = data!!.extras
            var bitmap = bundle?.get("data") as Bitmap

            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val file = File(path,filename)
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fos)
            photoUri=Uri.fromFile(file)

            fos.flush()
            fos.close()

            bitmap = createPhotos(bitmap);

            imageView3.setImageBitmap(bitmap)


//            val intent=Intent(context,GetAnswerActivity::class.java)
//            startActivity(intent)

            val intent=Intent(context,
                GetQuestionActivity::class.java)
            intent.putExtra("photoUri",photoUri.toString())
            startActivity(intent)

        }
    }


    //让图片旋转90度
    fun createPhotos(bitmap: Bitmap?): Bitmap {
        var bitmap = bitmap
        if (bitmap != null) {
            val m = Matrix()
            try {
                m.setRotate(90F, (bitmap.width / 2).toFloat(), (bitmap.height / 2).toFloat()) //90就是我们需要选择的90度
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
