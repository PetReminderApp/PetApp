package com.example.petapp.adapters





import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petapp.R
import com.example.petapp.models.Pet
import com.example.petapp.models.Task


class PetAdapter(val context : Context, private val pets: List<Pet>)
    : RecyclerView.Adapter<PetAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pet_list,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = pets[position]
        holder.bind(pet, context)

    }



    override fun getItemCount(): Int {
        return pets.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvPetName: TextView
        val ivPetPic: ImageView
        val rvTaskList: RecyclerView
        lateinit var tasks: List<Task>

        init {
            tvPetName = itemView.findViewById(R.id.tvPetName)
            ivPetPic = itemView.findViewById(R.id.ivPetPic)
            rvTaskList = itemView.findViewById(R.id.rvTaskList)

            //itemView.setOnClickListener(this)
        }

        fun bind(pet: Pet, context: Context) {
            tvPetName.text = pet.getName()

            tasks = pet.getTasks() ?: listOf()
            val taskTitles = tasks.map { it.fetchIfNeeded<Task>().getTitle() }
            Log.d(TAG, "taskTitles for ${pet.getName()}: $taskTitles")

            val adapter = PetTaskAdapter(context, taskTitles)
            val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            rvTaskList.adapter = adapter
            rvTaskList.layoutManager = layoutManager
            Log.d(TAG, "Checkpoint 2")


            Glide.with(itemView.context).load(pet.getPicture()?.url).into(ivPetPic)
            Log.d(TAG, "Checkpoint 3")

        }



    }

    companion object {
        const val TAG = "PetAdapter"
    }
}