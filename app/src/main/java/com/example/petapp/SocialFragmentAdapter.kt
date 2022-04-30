package com.example.petapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.fragments.CreateFriendRequest
import com.example.petapp.models.Friend
import com.example.petapp.models.Pet
import com.example.petapp.models.getPets
import com.parse.Parse
import com.parse.Parse.getApplicationContext
import com.parse.ParseUser
import java.util.*
import kotlin.collections.ArrayList

class SocialFragmentAdapter(val context: Context, val requests: ArrayList<Friend>)
    : RecyclerView.Adapter<SocialFragmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialFragmentAdapter.ViewHolder {
        // Specify the layout file to use for this item

        val view = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SocialFragmentAdapter.ViewHolder, position: Int) {
        val request = requests.get(position)
        holder.bind(request)
        holder.onClick(request)
    }

    override fun getItemCount(): Int {

        return requests.size
    }

    // Clean all elements of the recycler
    fun clear() {
        requests.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(postList: List<Friend>) {
        requests.addAll(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFriendName: TextView
        val buttonShare: Button
        val petName: EditText


        init {
            tvFriendName = itemView.findViewById(R.id.friendNameTV)
            buttonShare = itemView.findViewById(R.id.sharePetBtn)
            petName = itemView.findViewById(R.id.petNameEdit)

        }

        fun bind(request: Friend) {
            if (request.getFriend1() != ParseUser.getCurrentUser()) {
                tvFriendName.text = request.getFriend2()?.username
            } else {
                tvFriendName.text = request.getFriend1()?.username
            }
        }

        fun onClick(request: Friend) {
            buttonShare.setOnClickListener {
                Log.i(TAG, "clicked share button")

                val petlist = ParseUser.getCurrentUser().getPets()
                if (petlist != null) {
                    for (pet in petlist) {
                        if (pet.fetchIfNeeded<Pet>().getName() == petName.text.toString())
                        {
                            var array = request.getSharedPets()
                            if (array != null) {
                                array = array?.plus(pet)
                            }
                            else{
                                array = listOf(pet)
                            }
                            request.put("sharedpets", array)
                            request.save()
                            Log.i(TAG, "added shared pet")
                        }
                    }

               }
                Toast.makeText(getApplicationContext(), "Added shared pet!", Toast.LENGTH_SHORT).show()
                petName.text.clear()

            }
        }
    }









    companion object {
        const val TAG = "SocialActivity"
    }
}
