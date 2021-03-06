package com.example.ai_wrongnote.activity



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.android.volley.Response
import com.example.ai_wrongnote.R
import com.example.ai_wrongnote.data.CommendData
import kotlinx.android.synthetic.main.activity_test.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //绑定底部导航条
        //绑定底部导航栏和顶部title
        //获得BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        //获得Navigation的Control
        val navController = Navigation.findNavController(
            this,
            R.id.fragment_nav
        )
        //绑定
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        //获取顶部appBar
        val configuration =
            AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        //绑定顶部title
        NavigationUI.setupActionBarWithNavController(this, navController, configuration)


        //启动连接数据库
        // Thread(Runnable { DBConnection.link() }).start()


    }


}



