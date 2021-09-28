package cn.zybwz.dotask.ui.main.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.zybwz.dotask.R
import cn.zybwz.dotask.db.entity.TaskEntity
import com.bumptech.glide.Glide
import java.util.zip.Inflater

class TaskAdapter:RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private val taskList= mutableListOf<TaskEntity>()
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var title:TextView=itemView.findViewById(R.id.tv_task_title)
        var content:TextView=itemView.findViewById(R.id.tv_content)
        var type:ImageView=itemView.findViewById(R.id.iv_task_type)
        var current:ImageView=itemView.findViewById(R.id.iv_current)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_overview, parent)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskEntity = taskList[position]
        holder.title.text=taskEntity.title
        holder.content.text=taskEntity.url
        when(taskEntity.type){
            0->{
                Glide.with(holder.itemView).load(R.drawable.ie).into(holder.type)
            }
            1->{
                Glide.with(holder.itemView).load(R.drawable.docs).into(holder.type)
            }
        }

        if (taskEntity.current==1){
            Glide.with(holder.itemView).load(R.drawable.current).into(holder.current)
        }else {
            Glide.with(holder.itemView).load(R.drawable.set_current).into(holder.current)
        }

        holder.current.setOnClickListener {
            if (taskEntity.current==1){
                taskEntity.current=0
                taskList.remove(taskEntity)
                taskList.add(taskEntity)
            }
        }

        holder.current

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}