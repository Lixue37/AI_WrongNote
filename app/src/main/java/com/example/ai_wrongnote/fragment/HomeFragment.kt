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
import com.example.ai_wrongnote.HomeViewModel
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.activity.GetQuestionActivity
import kotlinx.android.synthetic.main.home_fragment.*
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
    }

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
