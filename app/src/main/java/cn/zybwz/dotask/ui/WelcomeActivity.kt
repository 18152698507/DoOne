package cn.zybwz.dotask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import cn.zybwz.dotask.R
import cn.zybwz.dotask.db.AppDataBase
import cn.zybwz.dotask.db.entity.TaskEntity
import cn.zybwz.dotask.ui.main.MainActivity
import java.util.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        insertDefault()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        },2000)
    }
    private fun insertDefault(){
        val taskDao = AppDataBase.dataBase.taskDao()
        val taskSize = taskDao.getTaskSize()
        val defaultUrl="https://book.flutterchina.club/chapter1/dart.html#_1-4-4-%E5%BC%82%E6%AD%A5%E6%94%AF%E6%8C%81"
        if (taskSize==0){
            val taskEntity = TaskEntity("欢迎使用DO", Date().time, 0, defaultUrl, 1)
            taskDao.insertTask(taskEntity)
        }
    }
}