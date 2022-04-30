package com.example.petapp.fragments

import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.petapp.adapters.FriendRequestAdapter
import com.example.petapp.R
import com.example.petapp.models.FriendRequest
import com.parse.*

class CreateFriendRequest : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_friend_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This is where we set up our views and click listeners

        activity?.findViewById<Button>(R.id.SubmitFR)?.setOnClickListener{
            var usernametext = activity?.findViewById<EditText>(R.id.UserNameET)?.text
            Log.i("Frie", usernametext.toString())

            val query: ParseQuery<ParseUser> = ParseQuery.getQuery(ParseUser::class.java)
            query.include("username")
            query.limit = 1
            query.whereEqualTo("username", usernametext.toString())
            query.findInBackground(object: FindCallback<ParseUser> {
                override fun done(posts: MutableList<ParseUser>?, e: ParseException?) {
                    if (e != null) {
                        // Something has went wrong
                        Log.e(FriendRequestFragment.TAG, "Error fetching posts")
                    } else {
                        if(posts != null) {
                            for (post in posts) {
                                var friendRequest = FriendRequest().apply {
                                    setSender(ParseUser.getCurrentUser())
                                    setReceiver(post)
                                    setStatus("pending")
                                }

                                friendRequest.saveInBackground { exception ->
                                    if (exception != null) {
                                        //somthing went wrong
                                        Log.e(FriendRequestAdapter.TAG, "Error while saving FR")
                                        exception.printStackTrace()
                                        //TODO: show a toast
                                        //Toast.makeText(requireContext(), "Friend added successfully!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Log.i(FriendRequestAdapter.TAG, "Successfully save FR")
                                        //TODO: Reset edit text and image
                                    }

                                }

                            }
                            activity?.supportFragmentManager?.popBackStack()

                        }
                    }
                }

            })

        }



    }
}