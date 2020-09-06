package com.example.ai_wrongnote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_test.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast


class TestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        post_btn.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            var stringRequest = StringRequest(
                "http://47.102.140.185:33060/",
                Response.Listener {
                    val str = it.toString()
                    lable1.append(str)
                },
                Response.ErrorListener {
                    //lable1.text = it.message
                    toast("请求数据失败")
                }
            )

            queue.add(stringRequest)
        }

    }

    //val id = 1



/*
* object : Listener<String?>() {
            fun onResponse(response: String?) {
                Log.d("TAG", response)
            }
        }, object : ErrorListener() {
            fun onErrorResponse(error: VolleyError) {
                Log.e("TAG", error.message, error)
            }
        }
* */
}

