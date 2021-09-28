package cn.zybwz.dotask.db.dao

import androidx.room.*
import cn.zybwz.dotask.db.entity.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT COUNT(*) FROM task")
    fun getTaskSize():Int

    @Query("SELECT * FROM task")
    fun getAllTask():List<TaskEntity>

    @Insert
    fun insertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM task WHERE current = 1")
    fun getCurrentTask():TaskEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurrentTask(taskEntity: TaskEntity)
}