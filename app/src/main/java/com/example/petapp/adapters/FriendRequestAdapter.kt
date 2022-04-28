package com.example.petapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.models.Friend
import com.example.petapp.models.FriendRequest
import com.parse.ParseUser
import kotlin.collections.ArrayList

class FriendRequestAdapter(val context: Context, val requests: ArrayList<FriendRequest>)
    : RecyclerView.Adapter<FriendRequestAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Specify the layout file to use for this item

        val view = LayoutInflater.from(context).inflate(R.layout.item_social, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
    fun addAll(postList: List<FriendRequest>) {
        requests.addAll(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
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

        fun onClick(request: FriendRequest) {
            buttonAccept.setOnClickListener {
                Log.i(TAG, "accepted friend request")

                //2. Create Object
                val friend = Friend().apply {
                    setFriend1(ParseUser.getCurrentUser())
                    setFriend2(request.getSender()!!)

                }

                //3. Send object to Parse
                friend.saveInBackground { exception ->
                    if (exception != null) {
                        //somthing went wrong
                        Log.e(TAG, "Error while saving post")
                        exception.printStackTrace()
                        //TODO: show a toast
                        //Toast.makeText(requireContext(), "Friend added successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.i(TAG, "Successfully save post")
                        //TODO: Reset edit text and image
                    }

                }
                request.setStatus("accepted")
                request.save()
            }




                buttonDecline.setOnClickListener {
                    Log.i(TAG, "declined friend request")
                    request.setStatus("declined")
                    request.save()
                }
            }
        }








    companion object {
        const val TAG = "SocialActivity"
    }
}
