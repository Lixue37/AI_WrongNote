package com.example.ai_wrongnote.activity

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ai_wrongnote.R
import kotlinx.android.synthetic.main.activity_commend.*


class CommendDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commend)
        init()
    }

    //NoteAdapter里在跳转的时候传递了数据，但是如果要显示出来数据，还需要在这里进行显示
    fun init() {
        initAllData()
    }

    fun initAllData() {
        //首先隐藏错题答案图片 以及掌握程度按钮
        commend_answer_text.visibility = View.INVISIBLE
        how_control_text.visibility = View.INVISIBLE
        control_list.visibility = View.INVISIBLE
        join_btn.visibility = View.INVISIBLE

        //获取该页面上的五个数据
        val commend_point_data = intent.getStringExtra("point_data")
        commend_point_data_actvt_text.text = commend_point_data
        //测试能否拿到数据
        //Toast.makeText(this,commend_point_data.toString(),Toast.LENGTH_SHORT).show()


        val commend_data = intent.getStringExtra("commend_data")
        commend_data_actvt_text.text = commend_data
        val how_hard = intent.getStringExtra("how_hard")
        commend_how_hard_data_text.text = how_hard
        val answer = intent.getStringExtra("answer")
        commend_answer_text.text = answer

        look_btn.setOnClickListener { commend_answer_text.visibility = View.VISIBLE }
        close_btn.setOnClickListener { commend_answer_text.visibility = View.INVISIBLE }
        yes_btn.setOnClickListener {
            how_control_text.visibility = View.VISIBLE
            control_list.visibility = View.VISIBLE

            join_btn.visibility = View.VISIBLE


           /* var control_select = findViewById<RadioGroup>(R.id.control_list)


            RadioButton radioButton1
            RadioButton radioButton2
            RadioButton radioButton3
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_radio_button2);
                radioGroup=findViewById(R.id.job_list);
                radioButton1=findViewById(R.id.radio_button1);
                radioButton2=findViewById(R.id.radio_button2);
                radioButton3=findViewById(R.id.radio_button3);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        getYourFavorite(checkedId);
                    }
                });
            }

            /**
             * 根据ID,执行相应的逻辑
             * @param buttonId
             */
            private void getYourFavorite(int buttonId){
                switch (buttonId){
                    case R.id.radio_button1:
                    if(radioButton1.isChecked()) {
                        Log.e(TAG, "你最爱的职业是: " + radioButton1.getText().toString());
                    }
                    break;
                    case R.id.radio_button2:
                    if(radioButton2.isChecked()) {
                        Log.e(TAG, "你最爱的职业是: " + radioButton2.getText().toString());
                    }
                    break;
                    case R.id.radio_button3:
                    if(radioButton3.isChecked()) {
                        Log.e(TAG, "你最爱的职业是: " + radioButton3.getText().toString());
                    }
                    break;
                }



                sexRadioGroup = findViewById(R.id.rgSex) as RadioGroup
            male = findViewById(R.id.male) as RadioButton
            female = findViewById(R.id.female) as RadioButton
            sexRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                var msg = ""
                if (male.getId() === checkedId) {
                    msg = "当前选中的性别为:" + male.getText().toString()
                }
                if (female.getId() === checkedId) {
                    msg = "当前选中的性别为:" + female.getText().toString()
                }
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            })


            //掌握程度，1代表完全不懂。5代表完全懂。
            val control_select=findViewById<RadioGroup>(R.id.control_list)

            }*/


        }
    }


}