package com.example.petapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.models.Task

private const val TAG = "UpcomingTasksAdapter"

class UpcomingTasksAdapter(
    val context: Context,
    val tasks: MutableList<Task>
) : RecyclerView.Adapter<UpcomingTasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingTasksAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingTasksAdapter.ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTaskTitle: TextView
        val ivImage: ImageView
        val tvDeadlineTime: TextView
        val btMore: ImageButton
        val tvPetName: TextView

        fun bind(task: Task) {
            tvTaskTitle.text = task.getName()
//            tvDeadlineTime = task. todo doesnt have this property
//            tvPetName = task.getPetName() todo doesn't have this property

//            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)

            btMore.setOnClickListener {
                Log.i(TAG, "clicked more btn")
            }
        }

        init {
            //todo findviewbyid for each view
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title)
            ivImage = itemView.findViewById(R.id.iv_image)
            tvDeadlineTime = itemView.findViewById(R.id.tv_deadline_time)
            btMore = itemView.findViewById(R.id.bt_more)
            tvPetName = itemView.findViewById(R.id.tv_pet_name)
        }
    }
}