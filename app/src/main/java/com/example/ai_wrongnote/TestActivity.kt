package com.example.ai_wrongnote

import android.R.attr.password
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_test.*
import org.json.JSONObject


class TestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

       /* post_btn.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            var stringRequest = StringRequest(
                "http://47.102.140.185:33060/similar",
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
        }*/



        //尝试上传
        post_btn.setOnClickListener {
            val requestQueue = Volley.newRequestQueue(this)


            val hashMap = HashMap<String, String>()
            hashMap["username"] = "Tom"
            hashMap["password"] = ""

            val jsonObjectRequest = CustomRequest(
                Request.Method.POST,
                "http://47.102.140.185:33060/",
                hashMap,
                Response.Listener { jsonObject -> Log.d("TagJson", jsonObject.toString()) },
                Response.ErrorListener { })
            requestQueue.add(jsonObjectRequest)

            /*val map: MutableMap<String, String> = HashMap()
            map["name1"] = "value1"
            map["name2"] = "value2"
            val jsonObject = JSONObject(params)
            val jsonRequest: JsonRequest<JSONObject> =
                object : JsonObjectRequest(Method.POST, httpurl, jsonObject,
                    Response.Listener<JSONObject> { response -> Log.d(TAG, "response -> $response") },
                    Response.ErrorListener { error -> Log.e(TAG, error.message, error) }) {
                    //注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
                    //必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
                    //所以这个方法在此处是不需要的
                    //    @Override
                    //    protected Map<String, String> getParams() {
                    //          Map<String, String> map = new HashMap<String, String>();
                    //            map.put("name1", "value1");
                    //            map.put("name2", "value2");
                    //        return params;
                    //    }
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Accept"] = "application/json"
                        headers["Content-Type"] = "application/json; charset=UTF-8"
                        return headers
                    }
                }
            requestQueue.add(jsonRequest)*/

           /* JsonObjectRequest jsonObjectRequest
            JSONObject jsonObject=new JSONObject() ;

            val merchant:MutableMap<String, String> = HashMap()
            merchant.put("id", "id");
            merchant.put("ncode", "ncode");
            merchant.put("tradingName", "tradingName");

            JSONObject jsonObject = new JSONObject(merchant);

            Log.e(TAG, "getdata: " + jsonObject.toString());

            JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,
                "",
                jsonObject,
                new Response . Listener < JSONObject >() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                },
                new Response . ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                }) {

                @Override
                public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
            };
            requestQueue.add(jsonRequest);*/


        }


        }





    }





