package com.example.petapp





import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petapp.models.Pet
import com.example.petapp.models.Task


class PetAdapter(val context : Context, val pets: List<Pet>)
    : RecyclerView.Adapter<PetAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pet_list ,parent,false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetAdapter.ViewHolder, position: Int) {
        val pet = pets[position]
        holder.bind(pet, context)
    }



    override fun getItemCount(): Int {
        return pets.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvPetName: TextView
        val ivPetPic: ImageView
        val lvTaskList: ListView
        lateinit var tasks: List<Task>

        init {
            tvPetName = itemView.findViewById(R.id.tvPetName)
            ivPetPic = itemView.findViewById(R.id.ivPetPic)
            lvTaskList = itemView.findViewById(R.id.lvTaskList)
        }

        fun bind(pet: Pet, context: Context) {
            tvPetName.text = pet.getName()

            tasks = pet.getTasks()!!
            lvTaskList.adapter = ArrayAdapter(
                context,
                R.layout.pet_tasks, tasks
            )


            Glide.with(itemView.context).load(pet.getPicture()?.url).into(ivPetPic)

        }


    }


}