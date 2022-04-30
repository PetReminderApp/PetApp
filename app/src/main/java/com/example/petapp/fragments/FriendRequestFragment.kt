package com.example.petapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.petapp.R
import com.example.petapp.adapters.FriendRequestAdapter
import com.example.petapp.models.FriendRequest
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser


class FriendRequestFragment : Fragment() {
    lateinit var socialRecyclerView: RecyclerView

    lateinit var adapter: FriendRequestAdapter

    lateinit var swipeContainer: SwipeRefreshLayout

    var allPosts: MutableList<FriendRequest> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friend_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This is where we set up our views and click listeners

        // Inflate the layout for this fragment
        swipeContainer = requireView().findViewById(R.id.swipeContainer)

        swipeContainer.setOnRefreshListener {
            Log.i(TAG, "Refreshing timeline")
            queryPosts()
        }

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)


        socialRecyclerView = view.findViewById(R.id.rv_social)

        // Steps to populate RecyclerView
        // 1. Create layout for each row in list
        // 2. Create data source for each row (this is the Post class)
        // 3. Create adapter that will bridge data and row layout (PostAdapter)
        // 4. Set adapter on RecyclerView
        adapter = FriendRequestAdapter(requireContext(), allPosts as ArrayList<FriendRequest>)
        socialRecyclerView.adapter = adapter

        // 5. Set layout manager on RecyclerView
        socialRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()

        activity?.findViewById<Button>(R.id.button_add_friend)?.setOnClickListener{
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container_view, CreateFriendRequest())
                ?.addToBackStack(null)
                ?.commit()
        }

    }


    // Query for all posts in our server
    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<FriendRequest> = ParseQuery.getQuery(FriendRequest::class.java)
        // Find all Post objects
        query.include(FriendRequest.KEY_RECEIVER)
        query.include(FriendRequest.KEY_SENDER)
        // Return posts in descending order: ie newer posts will appear first
        query.addDescendingOrder("createdAt")

        // Only return the most recent 20 posts
        query.limit = 20
        query.whereEqualTo(FriendRequest.KEY_RECEIVER, ParseUser.getCurrentUser())
        query.whereEqualTo(FriendRequest.KEY_STATUS, "pending")


        query.findInBackground(object: FindCallback<FriendRequest> {
            override fun done(posts: MutableList<FriendRequest>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if(posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Sender: " + post.getSender()?.username + ", user: " + post.getReceiver()?.username)
                        }
                        // Clear out our currently fetched posts
                        adapter.clear()
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()

                        // Now we call setRefreshing(false) to signal refresh has finished
                        swipeContainer.setRefreshing(false)
                    }
                }
            }

        })



    }

    companion object {
        const val TAG = "FriendRequestFragment"
    }
}