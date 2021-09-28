package cn.zybwz.dotask.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * 后续拓展 定时当前任务
 */
@Entity(tableName = "task")
class TaskEntity constructor(
    @ColumnInfo var title:String,
    @ColumnInfo var createTime:Long,
    @ColumnInfo var type:Int,//type 0=网页 1=文档 后续有需要再拓展
    @ColumnInfo var url:String,
    @ColumnInfo var current:Int=0,//1为当前唯一做的事，0为不是，有且只有一个1
    @PrimaryKey(autoGenerate = true) val id_: Int = 0,
) {

}