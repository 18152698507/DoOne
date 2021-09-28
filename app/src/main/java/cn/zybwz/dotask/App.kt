package cn.zybwz.dotask

import android.app.Application
import cn.zybwz.dotask.db.AppDataBase
import cn.zybwz.dotask.db.entity.TaskEntity
import java.util.*

class App:Application() {
    companion object {
        lateinit var app:Application
    }

    override fun onCreate() {
        super.onCreate()
        app=this
        insertDefault()
    }



    fun insertDefault(){
        val taskDao = AppDataBase.dataBase.taskDao()
        val taskSize = taskDao.getTaskSize()
        val defaultUrl="https://book.flutterchina.club/chapter1/dart.html#_1-4-4-%E5%BC%82%E6%AD%A5%E6%94%AF%E6%8C%81"
        if (taskSize==0){
            val taskEntity = TaskEntity("欢迎使用DO", Date().time, 0, defaultUrl, 1)
            taskDao.insertTask(taskEntity)
        }
    }
}