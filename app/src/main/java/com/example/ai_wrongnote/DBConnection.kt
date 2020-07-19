package com.example.ai_wrongnote

import java.sql.Connection
import java.sql.DriverManager


object DBConnection {
    fun link() {
        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        val url = "jdbc:mysql://47.102.140.185:3306/test"
        //连接数据库使用的用户名
        val userName = "root"
        //连接的数据库时使用的密码
        val password = "DaChuang2020"
        var connection: Connection? = null
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            println("驱动加载成功！！！")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password)
            println("连接数据库成功！！！")

            //3.sql语句
            val sql = "INSERT INTO test0719 (id, name) VALUES ( '24100413', 'wmq')"
            val sqlsql = "INSERT INTO test0719 (id, name) VALUES ( '606060', 'lx')"//测试写的，感觉很冗余，实际开发的时候注意

            //4.获取用于向数据库发送sql语句的ps
            val ps = connection.prepareStatement(sql)

            ps.execute(sql)
            ps.execute(sqlsql)//同上，测试写的。也许应该把sql语句做成数组，逐个执行
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (connection != null) {
                try {
                    connection.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}