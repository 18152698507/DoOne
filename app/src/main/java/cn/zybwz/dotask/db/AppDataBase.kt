package cn.zybwz.dotask.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.zybwz.dotask.App.Companion.app
import cn.zybwz.dotask.db.dao.TaskDao
import cn.zybwz.dotask.db.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class AppDataBase:RoomDatabase() {

    companion object {
        val dataBase: AppDataBase = Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "db_do_task"
        )
            //是否允许在主线程进行查询
            .allowMainThreadQueries()
            //数据库升级异常之后的回滚
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun taskDao():TaskDao
}