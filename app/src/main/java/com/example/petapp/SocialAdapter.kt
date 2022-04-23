package com.example.petapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.models.FriendRequest
import com.parse.ParseUser

class SocialAdapter(val context: Context, val requests: ArrayList<FriendRequest>)
    : RecyclerView.Adapter<SocialAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialAdapter.ViewHolder {
        // Specify the layout file to use for this item

        val view = LayoutInflater.from(context).inflate(R.layout.item_social, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SocialAdapter.ViewHolder, position: Int) {
        val request = requests.get(position)
        if (request.getReceiver()?.username  == ParseUser.getCurrentUser().username) {
            Log.i(TAG, request.getReceiver().toString())
            Log.i(TAG, ParseUser.getCurrentUser().username.toString())
            holder.bind(request)
        }
    }

    override fun getItemCount(): Int {
        /*
        var count = 0
        for (item in requests) {
            if (item.getReceiver()?.username  == ParseUser.getCurrentUser().username) {
                count++
            }
        }
        return count
        */
        return requests.size
    }

    // Clean all elements of the recycler
    fun clear() {
        requests.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(postList: List<FriendRequest>) {
        requests.addAll(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSender: TextView
        val buttonAccept: Button
        val buttonDecline: Button

        init {
            tvSender = itemView.findViewById(R.id.senderName)
            buttonAccept = itemView.findViewById(R.id.acceptButton)
            buttonDecline = itemView.findViewById(R.id.declineButton)
        }

        fun bind(request: FriendRequest) {
            tvSender.text = request.getSender()?.username
        }
    }

    companion object {
        const val TAG = "SocialActivity"
    }
}
