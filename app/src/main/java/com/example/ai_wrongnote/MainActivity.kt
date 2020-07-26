package com.example.ai_wrongnote



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //绑定底部导航条
        //绑定底部导航栏和顶部title
        //获得BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        //获得Navigation的Control
        val navController = Navigation.findNavController(this, R.id.fragment_nav)
        //绑定
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        //获取顶部appBar
        val configuration =
            AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        //绑定顶部title
        //NavigationUI.setupActionBarWithNavController(this, navController, configuration)


        //启动连接数据库
       // Thread(Runnable { DBConnection.link() }).start()

    }
}


//wmq测试***********

//2020/7/26 12:15测试

//20200727 12:19 wmq测试一行。测试冲突合并。