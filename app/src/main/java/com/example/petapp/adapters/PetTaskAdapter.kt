package com.example.petapp.adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R


class PetTaskAdapter(val context: Context, private val tasks: List<String?> ) :
    RecyclerView.Adapter<PetTaskAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var petTask : TextView = itemView.findViewById(R.id.pet_task)

        fun bind(task : String?){
            petTask.text = task
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.pet_tasks, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("PetTaskAdapter", "onBind")
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

}