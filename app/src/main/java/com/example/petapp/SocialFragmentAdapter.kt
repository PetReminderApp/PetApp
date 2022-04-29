package com.example.petapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.models.Friend
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvFriendName: TextView
        val buttonShare: Button



        init {
            tvFriendName = itemView.findViewById(R.id.friendNameTV)
            buttonShare = itemView.findViewById(R.id.sharePetBtn)


        }

        fun bind(request: Friend) {
            if(request.getFriend1() != ParseUser.getCurrentUser()) {
                tvFriendName.text = request.getFriend2()?.username
            }
            else{
                tvFriendName.text = request.getFriend1()?.username
            }
        }

        fun onClick(request: Friend) {
            buttonShare.setOnClickListener {
                Log.i(TAG, "clicked button")
                //request.setSharedPets()

            }





        }
    }








    companion object {
        const val TAG = "SocialActivity"
    }
}
