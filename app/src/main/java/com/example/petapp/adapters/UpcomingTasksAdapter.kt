package com.example.petapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petapp.R
import com.example.petapp.models.Pet
import com.example.petapp.models.Task
import java.text.SimpleDateFormat

private const val TAG = "UpcomingTasksAdapter"

class UpcomingTasksAdapter(
    val context: Context,
    val tasks: MutableList<Task>
) : RecyclerView.Adapter<UpcomingTasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun removeItem(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val background: RelativeLayout
        val foreground: ConstraintLayout
        val tvTaskTitle: TextView
        val ivImage: ImageView
        val tvDeadlineTime: TextView
        val btMore: ImageButton
        val tvPetName: TextView

        fun bind(task: Task) {
            val dateFormat = SimpleDateFormat("MMMM dd hh:mm a")
            val formattedTime = dateFormat.format(task.getTime())

            tvTaskTitle.text = task.getTitle()
            tvDeadlineTime.text = formattedTime
            tvPetName.text = task.getPet()?.get(Pet.KEY_NAME).toString()

            Glide.with(itemView.context)
                .load(task.getPet()?.getPicture()?.url)
                .into(ivImage)

            btMore.setOnClickListener {
                Log.i(TAG, "clicked more btn")
            }
        }

        init {
            background = itemView.findViewById(R.id.background)
            foreground = itemView.findViewById(R.id.foreground)
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title)
            ivImage = itemView.findViewById(R.id.iv_image)
            tvDeadlineTime = itemView.findViewById(R.id.tv_deadline_time)
            btMore = itemView.findViewById(R.id.bt_more)
            tvPetName = itemView.findViewById(R.id.tv_pet_name)
        }
    }
}